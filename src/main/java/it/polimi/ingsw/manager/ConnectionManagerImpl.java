package it.polimi.ingsw.manager;

import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
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

public class ConnectionManagerImpl extends UnicastRemoteObject implements ConnectionManager {
	private static Timer timer = new Timer();
	private static ConnectionManagerImpl instance;
	private static List<ClientRMICallbackRemote> rmiUsers = new ArrayList<>();
	private static List<Socket> socketUsers = new ArrayList<>();
	private static HashMap<Player, ClientRMICallbackRemote> rmiPlayers = new HashMap<>();
	private static HashMap<Player, Socket> socketPlayers = new HashMap<>();
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

	public void register(ClientRMICallbackRemote client) throws RemoteException{
		rmiUsers.add(client);
		System.out.println("New user in the game");
		List<ClientRMICallbackRemote> temporaryRmiUsers = new ArrayList<>();
		List<Socket> temporarySocketUsers = new ArrayList<>();
		if (rmiUsers.size() + socketUsers.size() == 1){//the first player will create the game and he will run the game
			game = new Game();
		}else{
			//timer starts when there are 2 players
			if(rmiUsers.size() + socketUsers.size() == 2){
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
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
				}, 15000);
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
	
	private static void startGame(List<ClientRMICallbackRemote> tempRmiUsers, List<Socket> tempSocketUsers) throws RemoteException{
		ExecutorService executor = Executors.newCachedThreadPool();
		ArrayList<Color> colors = new ArrayList<>();
		int numberOfPlayers = tempRmiUsers.size() + tempSocketUsers.size();
		Player[] players = new Player[numberOfPlayers];
		for (Color color : Color.values()) {
			colors.add(color);
		}
		for (int i = 0; i < numberOfPlayers; i++) {
			String nameChoosen = tempRmiUsers.get(i).askName();
			Color colorChoosen = askColor(tempRmiUsers.get(i), colors);
			colors.remove(colorChoosen);
			players[i] = new Player(nameChoosen, colorChoosen);
		}
		game.setPlayers(players);
		for (int i = 0; i < players.length; i++) {
			rmiPlayers.put(players[i], tempRmiUsers.get(i));
		}
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
	
	public static HashMap<Player, ClientRMICallbackRemote> getPlayersView() {
		return rmiPlayers;
	}
	
	public static void addPlayerView(Player player, ClientRMICallbackRemote client){
		rmiPlayers.put(player, client);
	}
	
	public static ClientRMICallbackRemote getView(Player player){
		return rmiPlayers.get(player);
	}

	public static void startTurn(Player player) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		client.startTurn(player.getName());
	}

	public static int turnChoice(Player player) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.turnChoice();
		return choice;
	}

	public static void moveAlreadyDone(Player player) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		client.moveAlreadyDone();
	}

	public static int chooseZone(Player player, Board board) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.chooseZone();
		return choice;
	}

	public static int choosePosition(Player player, Position[] positions) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.choosePosition(positions);
		return choice;
	}

	public static int chooseFamilyMember(Player player, ArrayList<FamilyMember> familyMembers) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.chooseFamilyMember(familyMembers);
		return choice;
	}

	public static int askForAlternativeCost(Player player, ArrayList<Resource> costs, ArrayList<Resource> alternativeCosts) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.askForAlternativeCost(costs, alternativeCosts);
		return choice;
	}

	public static int askForCouncilPrivilege(Player player, ArrayList<ResourceBonus> councilPrivileges) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.askForCouncilPrivilege(councilPrivileges);
		return choice;
	}

	public static int askForServants(Player player, int numberOfServants) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.askForServants(numberOfServants);
		return choice;
	}

	public static int askForInformation(Player player, String[] playersNames) throws RemoteException {
		ClientRMICallbackRemote client  = getView(player);
		int choice = client.askForInformation(playersNames);
		return choice;
	}

	public static void showPersonalBoard(Player player, PersonalBoard personalBoard) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		client.showPersonalBoard(personalBoard);
	}
	
	public static void cantPassTurn(Player player) throws RemoteException{
		ClientRMICallbackRemote client = getView(player);
		client.cantPassTurn();
	}
	
	public static void roundBegins(Player[] players) throws RemoteException{
		for (Player player : players){
			ClientRMICallbackRemote client = getView(player);
			client.roundBegins();
		}
	}
	
	public static void hasWon(String winner, Player[] players) throws RemoteException{
		for (Player player : players){
			ClientRMICallbackRemote client = getView(player);
			client.hasWon(winner);
		}
	}

	public static int askForZone(ArrayList<ActionZone> zones, Player player) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.askForAction(zones);
		return choice;
	}

	public static int chooseActionPosition(Player player, Position[] zonePositionsDescriptions) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.askForActionPosition(zonePositionsDescriptions);
		return choice;
	}

	public static void catchException(String message, Player player) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		client.catchException(message);
	}
	
	public static void showDices(Player[] players, ArrayList<Dice> dices) throws RemoteException{
		for (Player player: players){
			ClientRMICallbackRemote client = getView(player);
			client.showDices(dices);
		}
	}

	public static int askForExcommunication(Player player, ExcommunicationTile excommunicationTile) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int LeaderCardActionChoice(Player player) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.askForLeaderCardAction();
		return choice;
	}

	public static int chooseLeaderCard(Player player, ArrayList<LeaderCard> leaderCards) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.askForLeaderCard(leaderCards);
		return choice;
	}

	public static int choosePersonalBonusTile(Player player,
			ArrayList<PersonalBonusTile> personalBonusTiles) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.askForPersonalBonusTile(personalBonusTiles);
		return choice;
	}

}
