package it.polimi.ingsw.manager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.view.CliRmi;
import it.polimi.ingsw.manager.ActionSocket.action;

public class ConnectionManagerImpl extends UnicastRemoteObject implements ConnectionManager, Runnable {
	
	//ConnectionManagerImpl is a singleton called by Manager. It handles connection (Rmi and Socket) with clients
	//there is a difference between users and players. users are players that doesn't belong to any match started. When a match starts
	//users become players.
	static Timer timer;
	private static ConnectionManagerRmiServerImpl connectionManagerRmiServerImpl; //takes the incoming answer of rmi users
	private static ConnectionManagerImpl instance;//singleton
	
	//contains all rmiUsers. When a game starts, users are removed from here and moved to hashmap above
	private static List<CliRmi> rmiUsers = new ArrayList<>();
	
	//contains all socketUsers. When a game starts, users are removed from here and moved to hashmap above
	private static List<Socket> socketUsers = new ArrayList<>();
	private static List<ConnectionManagerSocketServer> socketListenersUsers = new ArrayList<>();//contains all listeners of users
	
	private static HashMap<Player, CliRmi> rmiPlayers = new HashMap<>(); //contains all rmi players of the server
	private static HashMap<Player, ObjectOutputStream> socketOutPlayers = new HashMap<>(); //contains all socket players of the server
	// contains all listeners of socket players of the server
	private static HashMap<Player, ConnectionManagerSocketServer> clientListener = new HashMap<>(); 
	
	//contains players disconnected from any game running on the server
	private static List<Player> playersDisconnected = new ArrayList<>(); 
	private ExecutorService executor = Executors.newCachedThreadPool();
	private static Game game;
	
	
	public static void setRmiServerImpl(
			ConnectionManagerRmiServerImpl connectionManagerRmiServerImpl) {
		ConnectionManagerImpl.connectionManagerRmiServerImpl = connectionManagerRmiServerImpl;
	}
	
	//singleton
	public static ConnectionManagerImpl getConnectionManager() throws RemoteException{
		if (instance == null){
			instance = new ConnectionManagerImpl();
		}
		return instance;
	}
	
	private ConnectionManagerImpl() throws RemoteException {
	}
	
	@Override
	public void run() {
		try{
			System.out.println("New socket user in the game");
			
			Scanner socketInClient = new Scanner(socketUsers.get(socketUsers.size() - 1).getInputStream());
			ObjectOutputStream socketOutClient = new ObjectOutputStream(socketUsers.get(socketUsers.size() - 1).getOutputStream() );
			
			//create now this listener of socket because in this way server can tell to user it's not time to talk 
			ConnectionManagerSocketServer connectionManagerSocketServer = new ConnectionManagerSocketServer(socketInClient, socketOutClient);

			socketListenersUsers.add(connectionManagerSocketServer);
			
			executor.submit(connectionManagerSocketServer);
			lobby();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void register(CliRmi client) throws RemoteException{
		rmiUsers.add(client);
		System.out.println("New rmi user in the game");
		try {
			lobby();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void lobby() throws ClassNotFoundException, IOException{
		List<CliRmi> temporaryRmiUsers = new ArrayList<>();
		List<ConnectionManagerSocketServer> temporarySocketUsers = new ArrayList<>();
		
		if (rmiUsers.size() + socketUsers.size() == 1){//the first player will create the game and he will run the game
			game = new Game();
		}else{
			//timer starts when there are 2 players
			if(rmiUsers.size() + socketUsers.size() == 2){//from now, the timer starts
				timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						fixingUsers(temporaryRmiUsers, temporarySocketUsers);
						try {
							startGame(temporaryRmiUsers, temporarySocketUsers);
						} catch (ClassNotFoundException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, 10000);//10 seconds, then the game starts
			}
			//if there are 4 users, the game must start
			if(rmiUsers.size() + socketUsers.size() == 4){
				fixingUsers(temporaryRmiUsers, temporarySocketUsers);
				timer.cancel();
				startGame(temporaryRmiUsers, temporarySocketUsers);
			}
		}	
	}
	
	private static void fixingUsers(List<CliRmi> temporaryRmi, List<ConnectionManagerSocketServer> temporarySocket){
		for (CliRmi c : rmiUsers) {
			temporaryRmi.add(c);
		}
		for (ConnectionManagerSocketServer s : socketListenersUsers) {
			temporarySocket.add(s);
		}
		rmiUsers.clear();
		socketListenersUsers.clear();
	}
	
	private void startGame(List<CliRmi> tempRmiUsers, List<ConnectionManagerSocketServer> tempSocketUsers) throws IOException, ClassNotFoundException{
		ArrayList<Color> colors = new ArrayList<>();
		int numberOfRmiUsers = tempRmiUsers.size();
		int numberOfSocketUsers = tempSocketUsers.size();
		
		System.out.println("number of rmi players: " + numberOfRmiUsers);
		System.out.println("number of socket players: "+ numberOfSocketUsers);
		
		Player[] players = new Player[numberOfRmiUsers + numberOfSocketUsers];
		for (Color color : Color.values()) {
			colors.add(color);
		}
		for (int i = 0; i < numberOfRmiUsers; i++) {
			tempRmiUsers.get(i).askName();
			connectionManagerRmiServerImpl.setCliRmi(tempRmiUsers.get(i));
			
			synchronized (connectionManagerRmiServerImpl) {
				while(!connectionManagerRmiServerImpl.getIsAvailable()){
					try {
						connectionManagerRmiServerImpl.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			//now the answer is available
			String nameChoosen = connectionManagerRmiServerImpl.getStringReceived();
			
			Color colorChoosen = askRmiColor(tempRmiUsers.get(i), colors);
			colors.remove(colorChoosen);
			
			players[i] = new Player(nameChoosen, colorChoosen);
			
			rmiPlayers.put(players[i], tempRmiUsers.get(i));
			System.out.println("added new rmi plyer");
		}
		for(int i=0; i < numberOfSocketUsers; i++){
			ConnectionManagerSocketServer listener = tempSocketUsers.get(i);
			ObjectOutputStream socketOut = listener.getSocketOutClient();
			
			ActionSocket name = new ActionSocket(action.chooseName);
			socketOut.writeObject(name);
			socketOut.flush();
			
			listener.setIsRightTurn(true);
			synchronized (listener) {
				while(!listener.getIsAvailable()){
					try {
						listener.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			String nameChoosen =  listener.getStringReceived();
			listener.setIsRightTurn(false);
			
			Color colorChoosen = askSocketColor(listener, colors);
			
			players[i + numberOfRmiUsers] = new Player(nameChoosen, colorChoosen);
			colors.remove(colorChoosen);
			
			clientListener.put(players[i + numberOfRmiUsers], listener);
			socketOutPlayers.put(players[i + numberOfRmiUsers], socketOut);
			System.out.println("Added new socket player");
		}
		game.setPlayers(players);
		executor.submit(game);
	}
	
	
	
	private Color askSocketColor(ConnectionManagerSocketServer listener, ArrayList<Color> availableColors) throws IOException {
		ObjectOutputStream socketOut = listener.getSocketOutClient();
		ActionSocket color = new ActionSocket(action.chooseColor);
		color.setAvailableColors(availableColors);
		while(true){//while the user doesn't answer well to the question	
			socketOut.writeObject(color);
			socketOut.flush();
			listener.setIsRightTurn(true);
			synchronized (listener) {
				while(!listener.getIsAvailable()){
					try {
						listener.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			listener.setIsRightTurn(false);
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
		}
	}

	private static Color askRmiColor(CliRmi client, ArrayList<Color> availableColors) throws RemoteException {
		while(true){//while user doesn't answer well
			String[] colors = new String[availableColors.size()];
			for(int counter = 0; counter < availableColors.size(); counter++){
				colors[counter] = availableColors.get(counter).toString().toLowerCase();
			}
			client.askColor(colors);
			
			connectionManagerRmiServerImpl.setCliRmi(client);
			
			synchronized (connectionManagerRmiServerImpl) {
				while(!connectionManagerRmiServerImpl.getIsAvailable()){
					try {
						connectionManagerRmiServerImpl.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			try{
				int colorChoiced = Integer.parseInt(connectionManagerRmiServerImpl.getStringReceived()) - 1;
				if(colorChoiced >= 0 && colorChoiced < availableColors.size()){
					return availableColors.get(colorChoiced);
				}else{
					client.integerError();
				}
			}catch(NumberFormatException e){
				client.wrongInput();
			}
		}
	}
	
	public static void addSocketUsers(Socket userSocket){
		socketUsers.add(userSocket);
	}
	
	public static ArrayList<Player> getRmiPlayers(){
		ArrayList<Player> rmiPlayersList = new ArrayList<Player>();
		Set<Player> players = rmiPlayers.keySet();
		for (Player player : players) {
			rmiPlayersList.add(player);
		}
		return rmiPlayersList;
	}
	
	public static ArrayList<Player> getSocketOutPlayers(){
		ArrayList<Player> socketPlayersList = new ArrayList<Player>();
		Set<Player> players = socketOutPlayers.keySet();
		for (Player player : players) {
			socketPlayersList.add(player);
		}
		return socketPlayersList;
	}
	
	public static void addPlayerView(Player player, CliRmi client){
		rmiPlayers.put(player, client);
	}
	
	public static CliRmi getRmiView(Player player){
		return rmiPlayers.get(player);
	}
	
	public static List<Player> getPlayersDisconnected() {
		return playersDisconnected;
	}
	
	public static ObjectOutputStream getSocketOutView(Player player){
		return socketOutPlayers.get(player);
	}
	
	public static void startTurn(Player player, ArrayList<Player> playersInGame) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			try{
				client.startTurn(player.getName());
			}catch(ConnectException e){ //client disconnects from the server
				System.out.println(player.getName() + " left the game!");
				playersDisconnected.add(player); //add player to arraylist. Manager after call turnChoice, see if player is disconnected
				notifyPlayers(player, playersInGame);
			}
		}else{ //player is a socket user
			ObjectOutputStream o = getSocketOutView(player);
			ActionSocket act = new ActionSocket(action.startTurn);
			act.setMessage(player.getName());
			try{
				o.writeObject(act);
				o.flush();
			}catch(SocketException e){
				System.out.println(player.getName() + " left the game!");
				playersDisconnected.add(player); //add player to arraylist. Manager after call turnChoice, see if player is disconnected
				notifyPlayers(player, playersInGame);
			}
		}
	}

	private static void notifyPlayers(Player playerDisconnected, ArrayList<Player> playersInGame) throws IOException{
		for (Player player : playersInGame) {
			ArrayList<Player> rmiPlayersList = getRmiPlayers();
			ArrayList<Player> socketPlayersList = getSocketOutPlayers();
			if (rmiPlayersList.contains(player)){ //if player is a rmi user
				CliRmi client = getRmiView(player);
				try{
					client.leftGame(playerDisconnected.getName());
				}catch(ConnectException e){ }//don't do nothing!
			}
			if(socketPlayersList.contains(player)){ //player is a socket user
				ObjectOutputStream out = getSocketOutView(player);
				
				ActionSocket act = new ActionSocket(action.leftGame); 
				act.setPlayerName(playerDisconnected.getName());
	
				try{
					out.reset();
					out.writeObject(act);
					out.flush();
				}catch(SocketException e){ } //don't do nothing!
			}
		}
	}

	private int getSocketAnswer(Player player) throws IOException{
		clientListener.get(player).setIsRightTurn(true);
		synchronized (clientListener.get(player)) {
			
			while(!clientListener.get(player).getIsAvailable()){
				try {
					clientListener.get(player).wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		clientListener.get(player).setIsRightTurn(false);
		try{
			int choice = Integer.parseInt(clientListener.get(player).getStringReceived());
			return choice;
		}catch (NumberFormatException e) {
			ObjectOutputStream out = getSocketOutView(player);
			ActionSocket act = new ActionSocket(action.wrongInput);
			out.writeObject(act);
			out.flush();
		}
		return 0;
	}
	
	private int getRmiAnswer(Player player) throws RemoteException{
		connectionManagerRmiServerImpl.setCliRmi(rmiPlayers.get(player));
		
		synchronized (connectionManagerRmiServerImpl) {
			while(!connectionManagerRmiServerImpl.getIsAvailable()){
				try {
					connectionManagerRmiServerImpl.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try{
			int choice = Integer.parseInt(connectionManagerRmiServerImpl.getStringReceived());
			return choice;
		}catch(NumberFormatException e){
			CliRmi client = getRmiView(player);
			client.wrongInput();
		}
		return 0;
	}
	
	public int turnChoice(Player player) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.turnChoice();
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			ActionSocket act = new ActionSocket(action.turnChoice);
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public static void moveAlreadyDone(Player player) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.moveAlreadyDone();
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.moveAlreadyDone);
			out.writeObject(act);
			out.flush();
		}
	}

	public int chooseZone(Player player) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.chooseZone();
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.chooseZone);
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int choosePosition(Player player, Position[] positions) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.choosePosition(positions);
			
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.choosePosition);
			act.setPositions(positions);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int chooseFamilyMember(Player player, ArrayList<FamilyMember> familyMembers) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.chooseFamilyMember(familyMembers);
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.chooseFamilyMember);
			act.setFamilyMembers(familyMembers);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int askForAlternativeCost(Player player, ArrayList<Resource> costs, ArrayList<Resource> alternativeCosts) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.askForAlternativeCost(costs, alternativeCosts);
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			
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

	public int askForCouncilPrivilege(Player player, ArrayList<ResourceBonus> councilPrivileges) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.askForCouncilPrivilege(councilPrivileges);
			
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.askForCouncilPrivilege);
			act.setBonus(councilPrivileges);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	
	}

	public int askForServants(Player player, int numberOfServants) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.askForServants(numberOfServants);
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.askForServants);
			act.setNumberOfServants(numberOfServants);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int askForInformation(Player player, String[] playersNames) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.askForInformation(playersNames);
			
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			
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
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.showPersonalBoard(personalBoard);
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.showPersonalBoard);
			act.setPersonalBoard(personalBoard);
			out.reset();
			out.writeObject(act);
			out.flush();
		}
	}
	
	public static void cantPassTurn(Player player) throws IOException{
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.cantPassTurn();
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.cantPassTurn);
			out.writeObject(act);
			out.flush();
		}
	}
	
	public static void roundBegins(Player[] players) throws IOException{
		for (Player player : players){
			ArrayList<Player> rmiPlayersList = getRmiPlayers();
			ArrayList<Player> socketPlayersList = getSocketOutPlayers();
			if (rmiPlayersList.contains(player)){ //if player is a rmi user
				CliRmi client = getRmiView(player);
				client.roundBegins();
			}
			if(socketPlayersList.contains(player)){ //player is a socket user
				ObjectOutputStream out = getSocketOutView(player);
				
				ActionSocket act = new ActionSocket(action.roundBegins);
				out.writeObject(act);
				out.flush();
			}
		}
	}
	
	public static void hasWon(String winner, Player[] players) throws IOException{
		for (Player player : players){
			ArrayList<Player> rmiPlayersList = getRmiPlayers();
			if (rmiPlayersList.contains(player)){ //if player is a rmi user
				CliRmi client = getRmiView(player);
				client.hasWon(winner);
			}else{ //player is a socket user
				ObjectOutputStream out = getSocketOutView(player);
				
				ActionSocket act = new ActionSocket(action.hasWon);
				act.setMessage(winner);
				out.writeObject(act);
				out.flush();
			}
		}
	}

	public int askForZone(ArrayList<ActionZone> zones, Player player) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.askForAction(zones);
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.askForAction);
			act.setZones(zones);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int chooseActionPosition(Player player, Position[] zonePositionsDescriptions) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.askForActionPosition(zonePositionsDescriptions);
			int choice = getRmiAnswer(player);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			
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
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.catchException(message);
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.catchException);
			act.setMessage(message);
			out.reset();
			out.writeObject(act);
			out.flush();
		}
	}
	
	public static void showDices(Player[] players, ArrayList<Dice> dices) throws IOException{
		for (Player player: players){
			ArrayList<Player> rmiPlayersList = getRmiPlayers();
			if (rmiPlayersList.contains(player)){ //if player is a rmi user
				CliRmi client = getRmiView(player);
				client.showDices(dices);
			}else{ //player is a socket user
				ObjectOutputStream out = getSocketOutView(player);
			
				ActionSocket act = new ActionSocket(action.showDices);
				act.setDices(dices);
				out.reset();
				out.writeObject(act);
				out.flush();
			}
		}
	}

	public int askForExcommunication(Player player, ExcommunicationTile excommunicationTile) throws IOException {
		ArrayList<Player> rmiPlayerList = getRmiPlayers();
		if (rmiPlayerList.contains(player)){
			CliRmi client = getRmiView(player);
			client.askForExcommunication(excommunicationTile);
			int choice = getRmiAnswer(player);
			return choice;
		}
		else {
			ObjectOutputStream out = getSocketOutView(player);
			
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

	public int LeaderCardActionChoice(Player player) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.askForLeaderCardAction();
			int choice = getRmiAnswer(player);
			return choice;
		}
		else{
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.askForLeaderCardAction);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int chooseLeaderCard(Player player, ArrayList<LeaderCard> leaderCards) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.askForLeaderCard(leaderCards);
			int choice = getRmiAnswer(player);
			return choice;
		}
		else{
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.askForLeaderCards);
			act.setLeaderCards(leaderCards);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int choosePersonalBonusTile(Player player, ArrayList<PersonalBonusTile> personalBonusTiles) throws  IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.askForPersonalBonusTile(personalBonusTiles);
			int choice = getRmiAnswer(player);
			return choice;
		}
		else{
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.askForPersonalBonusTile);
			act.setPersonalBonusTiles(personalBonusTiles);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int draftLeaderCard(Player player, ArrayList<LeaderCard> leaderCards) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.draftLeaderCard(leaderCards);
			int choice = getRmiAnswer(player);
			return choice;
		}
		else{
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.draftLeaderCards);
			act.setLeaderCards(leaderCards);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			int choice = getSocketAnswer(player);
			return choice;
		}
	}

	public int chooseEffect(Player player, DevelopmentCard developmentCard)throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.askForCardEffect(developmentCard);
			int choice = getRmiAnswer(player);
			return choice;
		}
		else{
			ObjectOutputStream out = getSocketOutView(player);
			
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
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			CliRmi client = getRmiView(player);
			client.integerError();
		}
		else{
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.integerError);
			out.writeObject(act);
			out.flush();
		}
	}
	
	
}
