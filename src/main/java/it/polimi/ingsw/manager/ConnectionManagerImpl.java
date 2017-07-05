package it.polimi.ingsw.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.AlreadyBoundException;
import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.security.auth.login.Configuration;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.HANDLER.GAME.ConfigurationFileHandler;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.view.CliRmi;
import it.polimi.ingsw.manager.ActionSocket.action;
import it.polimi.ingsw.minigame.BoardProxy;
import it.polimi.ingsw.minigame.GameProxy;
import it.polimi.ingsw.minigame.PlayerProxy;
import it.polimi.ingsw.minigame.Update;

public class ConnectionManagerImpl extends UnicastRemoteObject implements ConnectionManager, Runnable {
	
	//ConnectionManagerImpl is a singleton called by Manager. It handles connection (Rmi and Socket) with clients
	//there is a difference between users and players. users contains players and own also socket and rmi stuff.
	static Timer timer;
	//private static ConnectionManagerRmiServerImpl connectionManagerRmiServerImpl; //takes the incoming answer of rmi users
	private static ConnectionManagerImpl instance;//singleton
	
	//there are only socket users because the socket is setted from Server.
	private static List<User> users = new ArrayList<>();
	//are the socket users that have insert their username and all rmi users
	private static List<User> usersReady = new ArrayList<>();  
	private static List<User> usersInGame = new ArrayList<>();
	//contains players disconnected from any game running on the server
	private static List<User> usersDisconnected = new ArrayList<>();
	private ExecutorService executor = Executors.newCachedThreadPool();
	private static Game game;
	private final static Logger LOGGER = Logger.getLogger(ConnectionManagerImpl.class.getName());
	private static int timerTurn;
	private static int timerBeforeStartGame;
	
	
	//singleton
	public static ConnectionManagerImpl getConnectionManager() throws RemoteException{
		if (instance == null){
			instance = new ConnectionManagerImpl();
		}
		return instance;
	}
	
	private ConnectionManagerImpl() throws RemoteException {
		try {
			//in file the timer is in seconds
			timerTurn = ConfigurationFileHandler.getData().getTimerTurn() * 1000; 
			timerBeforeStartGame = ConfigurationFileHandler.getData().getTimerBeforeStartGame() * 1000;
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(),e);
		}
	}
	
	@Override
	public void run() {
		try{
			System.out.println("New socket user in the game");
			
			User thisUser =  users.get(users.size() - 1);
			Scanner socketInClient = new Scanner(thisUser.getSocket().getInputStream());
			ObjectOutputStream socketOutClient = new ObjectOutputStream(thisUser.getSocket().getOutputStream());
			
			//create now this listener of socket because in this way server can tell to user it's not time to talk 
			ConnectionManagerSocketServer connectionManagerSocketServer = new ConnectionManagerSocketServer(socketInClient, socketOutClient);
			
			thisUser.setConnectionManagerSocketServer(connectionManagerSocketServer);
			
			//creating the thread that listen only this client
			executor.submit(connectionManagerSocketServer);
			
			askSocketUsername(thisUser);
		} catch (IOException | ClassNotFoundException | MyException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(),e);
		}
	}
	
	private void askSocketUsername(User thisUser) throws IOException, ClassNotFoundException, MyException{
		ConnectionManagerSocketServer connectionManagerSocketServer = thisUser.getConnectionManagerSocketServer();
		ObjectOutputStream socketOutClient = connectionManagerSocketServer.getSocketOutClient(); 
		
		ActionSocket act = new ActionSocket(action.askForUsername);
		socketOutClient.writeObject(act);
		socketOutClient.flush();
		
		boolean usernameChoosenHasAlreadyChoosen = true;//ask the username while user doesn't choice a good username
		while(usernameChoosenHasAlreadyChoosen){
			
			//asking for his username
			connectionManagerSocketServer.setIsRightTurn(true);
			synchronized (connectionManagerSocketServer) {
				while(!connectionManagerSocketServer.getIsAvailable()){
					try {
						connectionManagerSocketServer.wait();
					} catch (InterruptedException e) {
						LOGGER.log(Level.SEVERE, e.getMessage(),e);
						Thread.currentThread().interrupt();
					}
				}
			}
			String usernameChoosen =  connectionManagerSocketServer.getStringReceived();
			connectionManagerSocketServer.setIsRightTurn(false);
			
			//if username has already choosen
			if(usernameHasAlreadyChoosen(usernameChoosen)){
				//if user isn't reconnecting himself but he his a new user
				if(!usersDisconnected.contains(findUserDisconnectedByUsername(usernameChoosen))){
					//tell to player to choice another username
					
					ActionSocket a = new ActionSocket(action.usernameHasAlreadyChoosen);
					socketOutClient.writeObject(a);
					socketOutClient.flush();
					
				}else{//user is reconnecting himself
					usernameChoosenHasAlreadyChoosen = false; //end of the while
					thisUser.setUsername(usernameChoosen);
					reconnectionManager(thisUser);
				}
			}else{//username hasn't already choosen
				usernameChoosenHasAlreadyChoosen = false; //end of the while
				thisUser.setUsername(usernameChoosen);
				usersReady.add(thisUser);
				users.remove(thisUser);
				lobby();
			}
		}
	}
		
	public void register(CliRmi client) throws RemoteException{
		try{
			System.out.println("New rmi user in the game");
			User thisUser = new User(client);
			ConnectionManagerRmiServerImpl connectionManagerRmiServerImpl = new ConnectionManagerRmiServerImpl();
			
			
			
			thisUser.setConnectionManagerRmiServerImpl(connectionManagerRmiServerImpl);
			
			client.setConnectionManagerRmiServer(connectionManagerRmiServerImpl);
			
			client.askForUsername();
			connectionManagerRmiServerImpl.setIsRightTurn(true);
			synchronized (connectionManagerRmiServerImpl) {
				while(!connectionManagerRmiServerImpl.getIsAvailable()){
					try {
						connectionManagerRmiServerImpl.wait();
					} catch (InterruptedException e) {
						LOGGER.log(Level.SEVERE, e.getMessage(),e);
						Thread.currentThread().interrupt();
					}
				}
			}
			connectionManagerRmiServerImpl.setIsRightTurn(false);//user can't talk anymore
			//now the answer is available
	
			boolean usernameChoosenHasAlreadyChoosen = true;//ask the username while user doesn't choice a good username
			while(usernameChoosenHasAlreadyChoosen){
				
				//asking for his username
				connectionManagerRmiServerImpl.setIsRightTurn(true);
				synchronized (connectionManagerRmiServerImpl) {
					while(!connectionManagerRmiServerImpl.getIsAvailable()){
						try {
							connectionManagerRmiServerImpl.wait();
						} catch (InterruptedException e) {
							LOGGER.log(Level.SEVERE, e.getMessage(),e);
							Thread.currentThread().interrupt();
						}
					}
				}
				String usernameChoosen =  connectionManagerRmiServerImpl.getStringReceived();
				connectionManagerRmiServerImpl.setIsRightTurn(false);
				
				//if username has already choosen
				if(usernameHasAlreadyChoosen(usernameChoosen)){
					//if user isn't reconnecting himself but he his a new user
					if(!usersDisconnected.contains(findUserDisconnectedByUsername(usernameChoosen))){
						//tell to player to choice another username
						client.usernameHasAlreadyChoosen();
					}else{//user is reconnecting himself
						usernameChoosenHasAlreadyChoosen = false; //end of the while
						thisUser.setUsername(usernameChoosen);
						reconnectionManager(thisUser);
					}
				}else{//username hasn't already choosen
					usernameChoosenHasAlreadyChoosen = false; //end of the while
					thisUser.setUsername(usernameChoosen);
					usersReady.add(thisUser);
					users.remove(thisUser);
					lobby();
				}
			}
		}catch (IOException| ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(),e);
		}
	}
	
	private void lobby() throws ClassNotFoundException, IOException{	
		List<User> temporaryUsers = new ArrayList<>();
		
		if (usersReady.size() == 1){//the first player will create the game and he will run the game
			game = new Game();
		}else{
			//timer starts when there are 2 players
			if(usersReady.size() == 2){//from now, the timer starts
				timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						fixingUsers(temporaryUsers);
						try {
							startGame(temporaryUsers);
						} catch (ClassNotFoundException | IOException e) {
							LOGGER.log(Level.SEVERE, e.getMessage(),e);
						}
					}
				}, timerBeforeStartGame);//timer then the game starts
			}
			//if there are 4 users, the game must start
			if(usersReady.size() == 4){
				fixingUsers(temporaryUsers);
				timer.cancel();
				startGame(temporaryUsers);
			}
		}	
	}
	
	private static void fixingUsers(List<User> temporaryUsers){
		for (User user : usersReady) {
			temporaryUsers.add(user);
		}
		usersReady.clear();
	}
	
	private void startGame(List<User> tempUsers) throws IOException, ClassNotFoundException{
		String nameChoosen = null;
		ArrayList<Color> colors = new ArrayList<>();
		int numberOfUsers = tempUsers.size();
		
		Player[] players = new Player[numberOfUsers];
		for (Color color : Color.values()) {
			colors.add(color);
		}
		for(int i = 0; i < numberOfUsers; i++){
			User tempUser = tempUsers.get(i);
			
			if(tempUser.getConnectionType()==true){//this user is a rmi user
				CliRmi clientRmi = tempUser.getCliRmi();
				ConnectionManagerRmiServerImpl listener = tempUser.getConnectionManagerRmiServerImpl(); 
				try{
					clientRmi.askName();
				}catch(ConnectException e){
					usersDisconnected.add(tempUser);// user is disconnected
					LOGGER.log(Level.INFO, e.getMessage(),e);
				}
				if(!usersDisconnected.contains(tempUser)){ //user is connected, go normal
					listener.setIsRightTurn(true);
					synchronized (listener) {
						listener.startTurn();
						while(!listener.getIsAvailable() && !listener.getTimeExpired()){
							try {
								listener.wait();
							} catch (InterruptedException e) {
								LOGGER.log(Level.SEVERE, e.getMessage(),e);
								Thread.currentThread().interrupt();
							}
						}
					}
					listener.setIsRightTurn(false);
					listener.cancelTimer();
					//now the answer is available	
					if (listener.getTimeExpired()){
						nameChoosen = "Guest" + i; 
						listener.setTimeExpired(false);
						try{
							clientRmi.timeExpired();
						} catch (ConnectException e){
							usersDisconnected.add(tempUser);
							LOGGER.log(Level.INFO, e.getMessage(),e);
						}
					}
					else {
						nameChoosen = listener.getStringReceived();
					}
				}else{ //user is disconnected, set a standard name
					nameChoosen = "Guest" + i;
					usersDisconnected.remove(tempUser);//remove in user disconnected so the user has the opportunity of reconnect
				}
				Color colorChoosen = askRmiColor(tempUser, colors);
				colors.remove(colorChoosen);
				
				players[i] = new Player(nameChoosen, colorChoosen);
				tempUser.setPlayer(players[i]);
				usersInGame.add(tempUser);
				System.out.println("added new rmi plyer");
			}else{//this user is a socket user
				
				ConnectionManagerSocketServer listener = tempUser.getConnectionManagerSocketServer();
				ObjectOutputStream socketOut = listener.getSocketOutClient();
				
				ActionSocket name = new ActionSocket(action.chooseName);
				try{
					socketOut.writeObject(name);
					socketOut.flush();
				}catch(SocketException e){
					usersDisconnected.add(tempUser);
					LOGGER.log(Level.INFO, e.getMessage(),e);
				}
				if(!usersDisconnected.contains(tempUser)){//user is connected
					listener.setIsRightTurn(true);
					synchronized (listener) {
						listener.startTurn();
						while(!listener.getIsAvailable() && !listener.getTimeExpired()){
							try {
								listener.wait();
							} catch (InterruptedException e) {
								LOGGER.log(Level.SEVERE, e.getMessage(),e);
								Thread.currentThread().interrupt();
							}
						}
					}
					listener.cancelTimer();
					if (listener.getTimeExpired()){
						nameChoosen = "Guest" + i;
						listener.setTimeExpired(false);
						try {
							ActionSocket act = new ActionSocket(action.timeExpired);
							socketOut.writeObject(act);
							socketOut.flush();
						} catch (SocketException e) {
							usersDisconnected.add(tempUser);
							LOGGER.log(Level.INFO, e.getMessage(),e);
						}
					}
					else{
						nameChoosen =  listener.getStringReceived();
					}
					listener.setIsRightTurn(false);
				}else{
					nameChoosen = "Guest" + i;
					usersDisconnected.remove(tempUser);//remove in user disconnected so the user has the opportunity of reconnect
				}
				Color colorChosen = askSocketColor(tempUser, colors);
				
				players[i] = new Player(nameChoosen, colorChosen);
				colors.remove(colorChosen);
				
				tempUser.setPlayer(players[i]);
				usersInGame.add(tempUser);
				System.out.println("Added new socket player");
			}
		}//end for
		Update.getInstance().addUsers(tempUsers);
		game.setPlayers(players);
		executor.submit(game);
	}
	
	
	
	private Color askSocketColor(User user, ArrayList<Color> availableColors) throws IOException {
		
		String[] colors = new String[availableColors.size()];
		for(int counter = 0; counter < availableColors.size(); counter++){
			colors[counter] = availableColors.get(counter).toString().toLowerCase();
		}
		
		ConnectionManagerSocketServer listener = user.getConnectionManagerSocketServer();
		ObjectOutputStream socketOut = listener.getSocketOutClient();
		ActionSocket color = new ActionSocket(action.chooseColor);
		color.setAvailableColors(colors);
		while(true){//while the user doesn't answer well to the question	
			try{
				socketOut.writeObject(color);
				socketOut.flush();
			}catch(SocketException e){
				usersDisconnected.add(user);
				LOGGER.log(Level.INFO, e.getMessage(),e);
			}
			if(!usersDisconnected.contains(user)){
				listener.setIsRightTurn(true);
				synchronized (listener) {
					listener.startTurn();
					while(!listener.getIsAvailable() && !listener.getTimeExpired()){
						try {
							listener.wait();
						} catch (InterruptedException e) {
							LOGGER.log(Level.SEVERE, e.getMessage(),e);
							Thread.currentThread().interrupt();
						}
					}
				}
				listener.setIsRightTurn(false);
				listener.cancelTimer();
				if (listener.getTimeExpired()){
					listener.setTimeExpired(false);
					try{
						ActionSocket act = new ActionSocket(action.timeExpired);
						socketOut.writeObject(act);
						socketOut.flush();
					} catch(SocketException e){
						usersDisconnected.add(user);
						LOGGER.log(Level.INFO, e.getMessage(),e);
					}
					return availableColors.get(0);
				}
				try{
					int colorChoiced = Integer.parseInt(listener.getStringReceived()) - 1;
					if(colorChoiced >= 0 && colorChoiced < availableColors.size()){
						return availableColors.get(colorChoiced);
					}else{
						ActionSocket act = new ActionSocket(action.integerError);
						socketOut.writeObject(act);
						socketOut.flush();
					}
				}catch(NumberFormatException e){
					ActionSocket act = new ActionSocket(action.wrongInput);
					socketOut.writeObject(act);
					socketOut.flush();
				}
			}else{
				usersDisconnected.remove(user);//remove in user disconnected so the user has the opportunity of reconnect
				return availableColors.get(0);
			}
		}
	}

	private static Color askRmiColor(User user, ArrayList<Color> availableColors) throws RemoteException {
		CliRmi client = user.getCliRmi();
		ConnectionManagerRmiServerImpl listener = user.getConnectionManagerRmiServerImpl();
		while(true){//while user doesn't answer well
			String[] colors = new String[availableColors.size()];
			for(int counter = 0; counter < availableColors.size(); counter++){
				colors[counter] = availableColors.get(counter).toString().toLowerCase();
			}
			try{
				client.askColor(colors);
			}catch (ConnectException e) {
				usersDisconnected.add(user);
				LOGGER.log(Level.INFO, e.getMessage(),e);
			}
			if(!usersDisconnected.contains(user)){
				listener.setIsRightTurn(true);
				synchronized (listener) {
					listener.startTurn();
					while(!listener.getIsAvailable() && !listener.getTimeExpired()){
						try {
							listener.wait();
						} catch (InterruptedException e) {
							LOGGER.log(Level.SEVERE, e.getMessage(),e);
							Thread.currentThread().interrupt();
						}
					}
				}
				listener.setIsRightTurn(false);
				listener.cancelTimer();
				if (listener.getTimeExpired()){
					listener.setTimeExpired(false);
					try{
						user.getCliRmi().timeExpired();
					} catch (ConnectException e){
						usersDisconnected.add(user);
						LOGGER.log(Level.INFO, e.getMessage(),e);
					}
					return availableColors.get(0);
				}
				try{
					int colorChoiced = Integer.parseInt(listener.getStringReceived()) - 1;
					System.out.println(colorChoiced);
					if(colorChoiced >= 0 && colorChoiced < availableColors.size()){
						return availableColors.get(colorChoiced);
					}else{
						client.integerError();
					}
				}catch(NumberFormatException e){
					client.wrongInput();
					LOGGER.log(Level.INFO, e.getMessage(),e);
				}
			}else{//user is disconnected
				usersDisconnected.remove(user);//remove in user disconnected so the user has the opportunity of reconnect
				return availableColors.get(0);
			}
		}
	}
	
	public static int getTimerTurn() {
		return timerTurn;
	}
	
	public static List<User> getUsers() {
		return users;
	}
	
	public static List<User> getUsersInGame() {
		return usersInGame;
	}
	
	public static void startTurn(Player player, ArrayList<Player> playersInGame) throws IOException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			System.out.println("arrivo qui");
			CliRmi client = user.getCliRmi();
			try{
				client.startTurn(player.getName());
				ConnectionManagerRmiServerImpl con = user.getConnectionManagerRmiServerImpl();
				synchronized (con) {
					con.startTurn();
				}
			}catch(ConnectException e){ //client disconnects from the server
				disconnectionManager(player, playersInGame);
				LOGGER.log(Level.INFO, e.getMessage(),e);
			}
		}else{ //player is a socket user
			ConnectionManagerSocketServer con = user.getConnectionManagerSocketServer();
			ObjectOutputStream o = con.getSocketOutClient();
			ActionSocket act = new ActionSocket(action.startTurn);
			act.setMessage(player.getName());
			try{
				o.writeObject(act);
				o.flush();
				synchronized (con) {
					con.startTurn();
				}
			}catch(SocketException e){
				disconnectionManager(player, playersInGame);
				LOGGER.log(Level.INFO, e.getMessage(),e);
			}
		}
	}
	
	private static void disconnectionManager(Player player, ArrayList<Player> playersInGame) throws IOException{
		System.out.println(player.getName() + " left the game!");
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		usersDisconnected.add(user); //add player to arraylist. Manager after call turnChoice, see if player is disconnected
		notifyOfDisconnectionToPlayers(player, playersInGame);
	}
	
	private static void reconnectionManager(User userReconnected) throws IOException{
		//find the userInGame by username of userReconnected
		User userInGame = findUserDisconnectedByUsername(userReconnected.getUsername());
		if(userInGame==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		//giving to user the new connection!
		if(userInGame.getConnectionType()==true){ //user in game was a rmi user
			userInGame.setCliRmi(userReconnected.getCliRmi());
		}else{//user in game was a socket user
			userInGame.setConnectionManagerSocketServer(userReconnected.getConnectionManagerSocketServer());
		}
		
		Player player = userInGame.getPlayer();
		System.out.println(player.getName() + " reconnected himself!");
		
		usersDisconnected.remove(userInGame); //add player to arraylist. Manager after call turnChoice, see if player is disconnected
		//swipe from array of players into arraylist!
		ArrayList<Player> playersInGame = userInGame.getPlayer().getBoard().getGame().getRoundOrder();
		notifyOfReconnectionToPlayers(player, playersInGame);
	}

	private static void notifyOfReconnectionToPlayers(Player playerReconnected, ArrayList<Player> playersInGame) throws IOException {
		for (Player player : playersInGame) {
			User user = findUserByPlayer(player);
			if(user==null){
				try {
					throw new MyException("user not found");
				} catch (MyException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
				}
			}
			if (user.getConnectionType()==true){//player is a rmi user
				CliRmi client = user.getCliRmi();
				try{
					client.reconnectedToGame(playerReconnected.getName());
				}catch(ConnectException e){
					LOGGER.log(Level.INFO, e.getMessage(),e); 
					}//don't do nothing! This means that also this player is disconnected
				
			}else{ //player is a socket user
				ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
				ActionSocket act = new ActionSocket(action.reconnectedToGame); 
				act.setPlayerName(playerReconnected.getName());
	
				try{
					out.reset();
					out.writeObject(act);
					out.flush();
				}catch(SocketException e){ 
					LOGGER.log(Level.INFO, e.getMessage(),e); 
					//don't do nothing! This means that also this player is disconnected
				} 
			}
		}
	}

	private static void notifyOfDisconnectionToPlayers(Player playerDisconnected, ArrayList<Player> playersInGame) throws IOException{
		for (Player player : playersInGame) {
			User user = findUserByPlayer(player);
			if(user==null){
				try {
					throw new MyException("user not found");
				} catch (MyException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
				}
			}
			if (user.getConnectionType()==true){//player is a rmi user
				CliRmi client = user.getCliRmi();
				try{
					client.leftGame(playerDisconnected.getName());
				}catch(ConnectException e){ 
					LOGGER.log(Level.INFO, e.getMessage(),e);
				}//don't do nothing! This means that also this player is disconnected
				
			}else{ //player is a socket user
				ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
				ActionSocket act = new ActionSocket(action.leftGame); 
				act.setPlayerName(playerDisconnected.getName());
	
				try{
					out.reset();
					out.writeObject(act);
					out.flush();
				}catch(SocketException e){ 
					LOGGER.log(Level.INFO, e.getMessage(),e); 
					//don't do nothing! This means that also this player is disconnected
				} 
			}
		}
	}

	//if username is a username of a user in game return true, else return false
	public static boolean usernameHasAlreadyChoosen(String username){
		for (int i = 0; i < usersInGame.size(); i++) {
			if(usersInGame.get(i).getUsername().equals(username)){ //if username is already choosen and it belongs to users in game
				return true;
			}
		}
		for(int i = 0; i < usersReady.size(); i++){
			if(usersReady.get(i).getUsername().equals(username)){ //if username is already choosen and it belongs to users ready to play
				return true;
			}
		}
		return false; //arrive here only if the username hasn't already choosen
	}
	
	//if username is a username of a user disconnected return the reference to userDisconnected, else return null
	public static User findUserDisconnectedByUsername(String username){
		for (int i = 0; i < usersDisconnected.size(); i++) {
			if(usersDisconnected.get(i).getUsername().equals(username)){
				return usersDisconnected.get(i);
			}
		}
		return null; //arrive here only if the user is a new user
	}
	
	public static User findUserByPlayer(Player player) {
		for (int i = 0; i < usersInGame.size(); i++) {
			if(usersInGame.get(i).getPlayer().equals(player)){
				return usersInGame.get(i);
			}
		}
		return null;//never arrived here
	}

	private int getSocketAnswer(Player player) throws IOException, TimeExpiredException{
		ConnectionManagerSocketServer clientListener = findUserByPlayer(player).getConnectionManagerSocketServer();
		clientListener.setIsRightTurn(true);
		synchronized (clientListener) {
			while(!clientListener.getIsAvailable()){
				try {
					clientListener.wait();
				} catch (InterruptedException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
					Thread.currentThread().interrupt();
				}
				if (clientListener.getTimeExpired()){
					clientListener.setTimeExpired(false);
					throw new TimeExpiredException();
				}
			}
		}
		clientListener.setIsRightTurn(false);
		try{
			int choice = Integer.parseInt(clientListener.getStringReceived());
			return choice;
		}catch (NumberFormatException e) {
			ObjectOutputStream out = clientListener.getSocketOutClient();
			ActionSocket act = new ActionSocket(action.wrongInput);
			out.writeObject(act);
			out.flush();
		}
		return 0;
	}
	
	private int getRmiAnswer(Player player) throws RemoteException, TimeExpiredException{
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		ConnectionManagerRmiServerImpl listener = user.getConnectionManagerRmiServerImpl();
		listener.setIsRightTurn(true);
		
		synchronized (listener) {
			while(!listener.getIsAvailable()){
				try {
					listener.wait();
				} catch (InterruptedException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
					Thread.currentThread().interrupt();
				}
				if (listener.getTimeExpired()){
					listener.setTimeExpired(false);
					throw new TimeExpiredException();
				}
			}
		}
		listener.setIsRightTurn(false);
		try{
			int choice = Integer.parseInt(listener.getStringReceived());
			return choice;
		}catch(NumberFormatException e){
			CliRmi clientRmi = user.getCliRmi();
			clientRmi.wrongInput();
			LOGGER.log(Level.INFO, e.getMessage(),e);
		}
		return 0;
	}
	
	public int turnChoice(Player player) throws IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.turnChoice();
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			ActionSocket act = new ActionSocket(action.turnChoice);
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public static void moveAlreadyDone(Player player) throws IOException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.moveAlreadyDone();
		}else{ //player is a socket user
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.moveAlreadyDone);
			out.writeObject(act);
			out.flush();
		}
	}

	public int chooseZone(Player player) throws IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.chooseZone();
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.chooseZone);
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int choosePosition(Player player, Position[] positions) throws IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.choosePosition(positions);
			
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.choosePosition);
			act.setPositions(positions);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int chooseFamilyMember(Player player, ArrayList<FamilyMember> familyMembers) throws IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.chooseFamilyMember(familyMembers);
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.chooseFamilyMember);
			act.setFamilyMembers(familyMembers);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int askForAlternativeCost(Player player, ArrayList<Resource> costs, ArrayList<Resource> alternativeCosts) throws IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.askForAlternativeCost(costs, alternativeCosts);
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.askForAlternativeCost);
			act.setCosts(costs);
			act.setAlternativeCosts(alternativeCosts);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int askForCouncilPrivilege(Player player, ArrayList<ResourceBonus> councilPrivileges) throws IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.askForCouncilPrivilege(councilPrivileges);
			
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.askForCouncilPrivilege);
			act.setBonus(councilPrivileges);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	
	}

	public int askForServants(Player player, int numberOfServants) throws IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.askForServants(numberOfServants);
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.askForServants);
			act.setNumberOfServants(numberOfServants);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int askForInformation(Player player, String[] playersNames) throws IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.askForInformation(playersNames);
			
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.askForInformation);
			act.setPlayersName(playersNames);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public static void showPersonalBoard(Player player, PersonalBoard personalBoard) throws IOException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.showPersonalBoard(personalBoard);
		}else{ //player is a socket user
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.showPersonalBoard);
			act.setPersonalBoard(personalBoard);
			out.reset();
			out.writeObject(act);
			out.flush();
		}
	}
	
	public static void cantPassTurn(Player player) throws IOException{
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.cantPassTurn();
		}else{ //player is a socket user
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.cantPassTurn);
			out.writeObject(act);
			out.flush();
		}
	}
	
	public static void roundBegins(Player[] players) throws IOException{
		for (Player player : players){
			User user = findUserByPlayer(player);
			if(user==null){
				try {
					throw new MyException("user not found");
				} catch (MyException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
				}
			}
			GameProxy gameProxy = new GameProxy(player.getBoard().getGame());
		
			gameProxy.setDiceProxies(player.getBoard().getDices());
			gameProxy.setcurrentPlayer(player.getColor());
			
			if (user.getConnectionType()==true){//player is a rmi user
				CliRmi client = user.getCliRmi();
				try{
					client.roundBegins(gameProxy);
				}catch(ConnectException e){
					//user disconnected
					LOGGER.log(Level.INFO, e.getMessage(),e);
				}
			}else{ //player is a socket user
				ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
				
				ActionSocket act = new ActionSocket(action.roundBegins);
				try{
					out.writeObject(act);
					out.flush();
				}catch(SocketException e){
					LOGGER.log(Level.INFO, e.getMessage(),e); 
					//user disconnected
				}
			}
		}
	}
	
	public static void hasWon(String winner, Player[] players) throws IOException{
		for (Player player : players){
			User user = findUserByPlayer(player);
			if(user==null){
				try {
					throw new MyException("user not found");
				} catch (MyException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
				}
			}
			if (user.getConnectionType()==true){//player is a rmi user
				CliRmi client = user.getCliRmi();
				client.hasWon(winner);
			}else{ //player is a socket user
				ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
				
				ActionSocket act = new ActionSocket(action.hasWon);
				act.setMessage(winner);
				out.writeObject(act);
				out.flush();
			}
		}
		for (Player player : players) {
			User user = findUserByPlayer(player);
			usersInGame.remove(user);
		}
	}

	public int askForZone(ArrayList<ActionZone> zones, Player player) throws IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.askForAction(zones);
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.askForAction);
			act.setZones(zones);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int chooseActionPosition(Player player, Position[] zonePositionsDescriptions) throws IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.askForActionPosition(zonePositionsDescriptions);
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.askForActionPosition);
			act.setPositions(zonePositionsDescriptions);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public static void catchException(String message, Player player) throws IOException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.catchException(message);
		}else{ //player is a socket user
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.catchException);
			act.setMessage(message);
			out.reset();
			out.writeObject(act);
			out.flush();
		}
	}
	
	public static void showDices(Player[] players, ArrayList<Dice> dices) throws IOException{
		for (Player player: players){
			User user = findUserByPlayer(player);
			if(user==null){
				try {
					throw new MyException("user not found");
				} catch (MyException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
				}
			}
			if (user.getConnectionType()==true){//player is a rmi user
				CliRmi client = user.getCliRmi();
				try{
					client.showDices(dices);
				}catch(ConnectException e){
					//user disconnected
					LOGGER.log(Level.INFO, e.getMessage(),e);
				}
			}else{ //player is a socket user
				ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
				ActionSocket act = new ActionSocket(action.showDices);
				act.setDices(dices);
				try{
					out.reset();
					out.writeObject(act);
					out.flush();
				}catch (SocketException e) {
					//user disconnected
					LOGGER.log(Level.INFO, e.getMessage(),e);
				}
			}
		}
	}

	public int askForExcommunication(Player player, ExcommunicationTile excommunicationTile) throws IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.askForExcommunication(excommunicationTile);
			int choice = getRmiAnswer(player);
			return choice;
		}
		else {
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ArrayList<ExcommunicationTile> excommunicationTiles = new ArrayList<>();
			excommunicationTiles.add(excommunicationTile);
			ActionSocket act = new ActionSocket(action.askForExcommunication);
			act.setExcommunicationTiles(excommunicationTiles);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int LeaderCardActionChoice(Player player) throws IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.askForLeaderCardAction();
			int choice = getRmiAnswer(player);
			return choice;
		}
		else{
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.askForLeaderCardAction);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int chooseLeaderCard(Player player, ArrayList<LeaderCard> leaderCards) throws IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.askForLeaderCard(leaderCards);
			int choice = getRmiAnswer(player);
			return choice;
		}
		else{
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.askForLeaderCards);
			act.setLeaderCards(leaderCards);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int choosePersonalBonusTile(Player player, ArrayList<PersonalBonusTile> personalBonusTiles) throws  IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.askForPersonalBonusTile(personalBonusTiles);
			int choice = getRmiAnswer(player);
			return choice;
		}
		else{
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.askForPersonalBonusTile);
			act.setPersonalBonusTiles(personalBonusTiles);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int draftLeaderCard(Player player, ArrayList<LeaderCard> leaderCards) throws IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.draftLeaderCard(leaderCards);
			int choice = getRmiAnswer(player);
			return choice;
		}
		else{
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.draftLeaderCards);
			act.setLeaderCards(leaderCards);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int chooseEffect(Player player, DevelopmentCard developmentCard)throws IOException, TimeExpiredException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.askForCardEffect(developmentCard);
			int choice = getRmiAnswer(player);
			return choice;
		}
		else{
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.askForCardEffect);
			act.setDevelopmentCardEffect(developmentCard);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public static void integerError(Player player) throws IOException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			client.integerError();
		}
		else{
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			ActionSocket act = new ActionSocket(action.integerError);
			out.writeObject(act);
			out.flush();
		}
	}

	public static List<User> getUsersDisconnected() {
		return usersDisconnected;
	}

	public static void cancelTimer(Player player) {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			user.getConnectionManagerRmiServerImpl().cancelTimer();
		}
		else{
			user.getConnectionManagerSocketServer().cancelTimer();
		}
	}

	public static void timeExpired(Player player) throws IOException {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			CliRmi client = user.getCliRmi();
			try{
				client.timeExpired();
			} catch (ConnectException e) {
				disconnectionManager(player, player.getBoard().getGame().getRoundOrder());
				LOGGER.log(Level.INFO, e.getMessage(),e);
			}
		}
		else{
			ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
			
			try{
				ActionSocket act = new ActionSocket(action.timeExpired);
				out.writeObject(act);
				out.flush();
			} catch(SocketException e){
				disconnectionManager(player, player.getBoard().getGame().getRoundOrder());
				LOGGER.log(Level.INFO, e.getMessage(),e);
			}
		}
	}

	public static void startTimer(Player player) {
		User user = findUserByPlayer(player);
		if(user==null){
			try {
				throw new MyException("user not found");
			} catch (MyException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}
		}
		if (user.getConnectionType()==true){//player is a rmi user
			user.getConnectionManagerRmiServerImpl().startTurn();
		}
		else{
			user.getConnectionManagerSocketServer().startTurn();
		}
	}

	public void startGame(Game thisGame, GameProxy gameProxy) throws IOException{
		for (Player player : thisGame.getPlayers()) {
			User user = findUserByPlayer(player);
			if(user==null){
				try {
					throw new MyException("user not found");
				} catch (MyException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(),e);
				}
			}
			if (user.getConnectionType()==true){//player is a rmi user
				user.getCliRmi().startGame(gameProxy);
			}
			else{
				ObjectOutputStream out = user.getConnectionManagerSocketServer().getSocketOutClient();
				
				ActionSocket act = new ActionSocket(action.startGame);
				act.setGame(thisGame);
				out.writeObject(act);
				out.flush();
			}
		}
	}
}
