package it.polimi.ingsw.manager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
import it.polimi.ingsw.BONUS.Bonus;
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
import it.polimi.ingsw.view.ClientRMICallbackRemote;
import it.polimi.ingsw.manager.ActionSocket.action;

public class ConnectionManagerImpl extends UnicastRemoteObject implements ConnectionManager, Runnable {
	static Timer timer;
	private static ConnectionManagerImpl instance;
	private static List<ClientRMICallbackRemote> rmiUsers = new ArrayList<>();
	private static List<Socket> socketUsers = new ArrayList<>();
	private static HashMap<Player, ClientRMICallbackRemote> rmiPlayers = new HashMap<>();
	private static HashMap<Player, Scanner> socketInPlayers = new HashMap<>();
	private static HashMap<Player, ObjectOutputStream> socketOutPlayers = new HashMap<>();
	private static Game game;
	
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
		System.out.println("New user in the game");
		try {
			lobby();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void register(ClientRMICallbackRemote client) throws RemoteException{
		rmiUsers.add(client);
		System.out.println("New user in the game");
		try {
			lobby();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void lobby() throws ClassNotFoundException, IOException{
		List<ClientRMICallbackRemote> temporaryRmiUsers = new ArrayList<>();
		List<Socket> temporarySocketUsers = new ArrayList<>();
		if (rmiUsers.size() + socketUsers.size() == 1){//the first player will create the game and he will run the game
			game = new Game();
		}else{
			//timer starts when there are 2 players
			if(rmiUsers.size() + socketUsers.size() == 2){//from now, the timer starts
				timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						try {
							for (ClientRMICallbackRemote c : rmiUsers) {
								temporaryRmiUsers.add(c);
							}
							for (Socket s : socketUsers) {
								temporarySocketUsers.add(s);
							}
							rmiUsers.clear();
							socketUsers.clear();
							startGame(temporaryRmiUsers, temporarySocketUsers);
						} catch (IOException | ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}, 10000);//10 seconds, then the game starts
			}
			//if there are 4 users, the game must start
			if(rmiUsers.size() + socketUsers.size() == 4){
				for (ClientRMICallbackRemote c : rmiUsers) {
					temporaryRmiUsers.add(c);
				}
				for (Socket s : socketUsers) {
					temporarySocketUsers.add(s);
				}
				rmiUsers.clear();
				socketUsers.clear();
				timer.cancel();
				startGame(temporaryRmiUsers, temporarySocketUsers);
			}
		}	
	}
	
	private static void startGame(List<ClientRMICallbackRemote> tempRmiUsers, List<Socket> tempSocketUsers) throws IOException, ClassNotFoundException{
		ExecutorService executor = Executors.newCachedThreadPool();
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
			String nameChoosen = tempRmiUsers.get(i).askName();
			Color colorChoosen = askColor(tempRmiUsers.get(i), colors);
			colors.remove(colorChoosen);
			
			players[i] = new Player(nameChoosen, colorChoosen);
			
			rmiPlayers.put(players[i], tempRmiUsers.get(i));
			System.out.println("added new rmi plyer");
		}
		for(int i=0; i < numberOfSocketUsers; i++){
			ObjectOutputStream socketOut = new ObjectOutputStream(tempSocketUsers.get(i).getOutputStream());
			//ObjectInputStream socketIn = new ObjectInputStream(tempSocketUsers.get(i).getInputStream());
			Scanner socketIn = new Scanner(tempSocketUsers.get(i).getInputStream());
			
			ActionSocket name = new ActionSocket(action.chooseName);
			socketOut.writeObject(name);
			socketOut.flush();
			String nameChoosen = (String) socketIn.nextLine();
			
			ActionSocket color = new ActionSocket(action.chooseColor);
			color.setAvailableColors(colors);
			socketOut.writeObject(color);
			socketOut.flush();
			int colorChoosen = socketIn.nextInt() - 1;
			
			players[i + numberOfRmiUsers] = new Player(nameChoosen, colors.get(colorChoosen));
			colors.remove(colorChoosen);
			
			socketInPlayers.put(players[i + numberOfRmiUsers], socketIn);
			socketOutPlayers.put(players[i + numberOfRmiUsers], socketOut);
			System.out.println("Added new socket player");
		}
		game.setPlayers(players);
		executor.submit(game);
	}
	
	private static Color askColor(ClientRMICallbackRemote client, ArrayList<Color> availableColors) throws RemoteException {
		String[] colors = new String[availableColors.size()];
		for(int counter = 0; counter < availableColors.size(); counter++){
			colors[counter] = availableColors.get(counter).toString().toLowerCase();
		}
		int colorChoiced = client.askColor(colors) -1;
		return availableColors.get(colorChoiced);
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
	
	public static ArrayList<Player> getSocketInPlayers(){
		ArrayList<Player> socketPlayersList = new ArrayList<Player>();
		Set<Player> players = socketInPlayers.keySet();
		for (Player player : players) {
			socketPlayersList.add(player);
		}
		return socketPlayersList;
	}
	
	public static ArrayList<Player> getSocketOutPlayers(){
		ArrayList<Player> socketPlayersList = new ArrayList<Player>();
		Set<Player> players = socketOutPlayers.keySet();
		for (Player player : players) {
			socketPlayersList.add(player);
		}
		return socketPlayersList;
	}
	
	public static void addPlayerView(Player player, ClientRMICallbackRemote client){
		rmiPlayers.put(player, client);
	}
	
	public static ClientRMICallbackRemote getRmiView(Player player){
		return rmiPlayers.get(player);
	}
	
	public static Scanner getSocketInView(Player player){
		return socketInPlayers.get(player);
	}
	
	public static ObjectOutputStream getSocketOutView(Player player){
		return socketOutPlayers.get(player);
	}

	public static void startTurn(Player player) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			client.startTurn(player.getName());
		}else{ //player is a socket user
			ObjectOutputStream o = getSocketOutView(player);
			ActionSocket act = new ActionSocket(action.startTurn);
			act.setMessage(player.getName());
			o.writeObject(act);
			o.flush();
		}
	}

	public static int turnChoice(Player player) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.turnChoice();
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			ActionSocket act = new ActionSocket(action.turnChoice);
			out.writeObject(act);
			out.flush();
			
			int choice = in.nextInt();
			return choice;
		}
	}

	public static void moveAlreadyDone(Player player) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			client.moveAlreadyDone();
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			
			ActionSocket act = new ActionSocket(action.moveAlreadyDone);
			out.writeObject(act);
			out.flush();
		}
	}

	public static int chooseZone(Player player) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.chooseZone();
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			
			ActionSocket act = new ActionSocket(action.chooseZone);
			out.writeObject(act);
			out.flush();
			
			return in.nextInt();
		}
	}

	public static int choosePosition(Player player, Position[] positions) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.choosePosition(positions);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			
			ActionSocket act = new ActionSocket(action.choosePosition);
			act.setPositions(positions);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			return in.nextInt();
		}
	}

	public static int chooseFamilyMember(Player player, ArrayList<FamilyMember> familyMembers) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.chooseFamilyMember(familyMembers);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			
			ActionSocket act = new ActionSocket(action.chooseFamilyMember);
			act.setFamilyMembers(familyMembers);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			return in.nextInt();
		}
	}

	public static int askForAlternativeCost(Player player, ArrayList<Resource> costs, ArrayList<Resource> alternativeCosts) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.askForAlternativeCost(costs, alternativeCosts);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			
			ActionSocket act = new ActionSocket(action.askForAlternativeCost);
			act.setCosts(costs);
			act.setAlternativeCosts(alternativeCosts);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			return in.nextInt();
		}
	}

	public static int askForCouncilPrivilege(Player player, ArrayList<ResourceBonus> councilPrivileges) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.askForCouncilPrivilege(councilPrivileges);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			
			ActionSocket act = new ActionSocket(action.askForCouncilPrivilege);
			act.setBonus(councilPrivileges);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			return in.nextInt();
		}
	
	}

	public static int askForServants(Player player, int numberOfServants) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.askForServants(numberOfServants);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			
			ActionSocket act = new ActionSocket(action.askForServants);
			act.setNumberOfServants(numberOfServants);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			return in.nextInt();
		}
	}

	public static int askForInformation(Player player, String[] playersNames) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.askForInformation(playersNames);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			
			ActionSocket act = new ActionSocket(action.askForInformation);
			act.setPlayersName(playersNames);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			return in.nextInt();
		}
	}

	public static void showPersonalBoard(Player player, PersonalBoard personalBoard) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
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
			ClientRMICallbackRemote client = getRmiView(player);
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
				ClientRMICallbackRemote client = getRmiView(player);
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
				ClientRMICallbackRemote client = getRmiView(player);
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

	public static int askForZone(ArrayList<ActionZone> zones, Player player) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.askForAction(zones);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			
			ActionSocket act = new ActionSocket(action.askForAction);
			act.setZones(zones);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			return in.nextInt();
		}
	}

	public static int chooseActionPosition(Player player, Position[] zonePositionsDescriptions) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.askForActionPosition(zonePositionsDescriptions);
			return choice;
		}else{ //player is a socket user
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			
			ActionSocket act = new ActionSocket(action.askForActionPosition);
			act.setPositions(zonePositionsDescriptions);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			return in.nextInt();
		}
	}

	public static void catchException(String message, Player player) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
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
				ClientRMICallbackRemote client = getRmiView(player);
				client.showDices(dices);
			}else{ //player is a socket user
				ObjectOutputStream out = getSocketOutView(player);
				System.out.println(player);
			
				ActionSocket act = new ActionSocket(action.showDices);
				act.setDices(dices);
				out.reset();
				out.writeObject(act);
				out.flush();
			}
		}
	}

	public static int askForExcommunication(Player player, ExcommunicationTile excommunicationTile) throws IOException {
		ArrayList<Player> rmiPlayerList = getRmiPlayers();
		if (rmiPlayerList.contains(player)){
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.askForExcommunication(excommunicationTile);
			return choice;
		}
		else {
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			
			ArrayList<ExcommunicationTile> excommunicationTiles = new ArrayList<>();
			excommunicationTiles.add(excommunicationTile);
			ActionSocket act = new ActionSocket(action.askForExcommunication);
			act.setExcommunicationTiles(excommunicationTiles);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			return in.nextInt();
		}
	}

	public static int LeaderCardActionChoice(Player player) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.askForLeaderCardAction();
			return choice;
		}
		else{
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			
			ActionSocket act = new ActionSocket(action.askForLeaderCardAction);
			out.writeObject(act);
			out.flush();
			
			return in.nextInt();
		}
	}

	public static int chooseLeaderCard(Player player, ArrayList<LeaderCard> leaderCards) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.askForLeaderCard(leaderCards);
			return choice;
		}
		else{
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			
			ActionSocket act = new ActionSocket(action.askForLeaderCards);
			act.setLeaderCards(leaderCards);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			return in.nextInt();
		}
	}

	public static int choosePersonalBonusTile(Player player, ArrayList<PersonalBonusTile> personalBonusTiles) throws  IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.askForPersonalBonusTile(personalBonusTiles);
			return choice;
		}
		else{
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			
			ActionSocket act = new ActionSocket(action.askForPersonalBonusTile);
			act.setPersonalBonusTiles(personalBonusTiles);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			return in.nextInt();
		}
	}

	public static int draftLeaderCard(Player player, ArrayList<LeaderCard> leaderCards) throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.draftLeaderCard(leaderCards);
			return choice;
		}
		else{
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			
			ActionSocket act = new ActionSocket(action.draftLeaderCards);
			act.setLeaderCards(leaderCards);
			out.reset();
			out.writeObject(act);
			out.flush();
			
			return in.nextInt();
		}
	}

	public static int chooseEffect(Player player, DevelopmentCard developmentCard)throws IOException {
		ArrayList<Player> rmiPlayersList = getRmiPlayers();
		if (rmiPlayersList.contains(player)){ //if player is a rmi user
			ClientRMICallbackRemote client = getRmiView(player);
			int choice = client.askForCardEffect(developmentCard);
			return choice;
		}
		else{
			ObjectOutputStream out = getSocketOutView(player);
			Scanner in = getSocketInView(player);
			
			ActionSocket act = new ActionSocket(action.askForCardEffect);
			act.setDevelopmentCardEffect(developmentCard);
			out.writeObject(act);
			out.flush();
			return in.nextInt();
		}
	}
}
