package it.polimi.ingsw.manager;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.view.CLIView;
import it.polimi.ingsw.view.ClientRMICallbackRemote;
import it.polimi.ingsw.view.View;

public class ConnectionManagerImpl extends UnicastRemoteObject implements ConnectionManager {
	private static ConnectionManagerImpl instance;
	private static ArrayList<ClientRMICallbackRemote> temporaryView = new ArrayList<>();
	private static HashMap<Player, ClientRMICallbackRemote> playersView = new HashMap<>();
	
	public static ConnectionManagerImpl getConnectionManager() throws RemoteException{
		if (instance == null){
			instance = new ConnectionManagerImpl();
		}
		return instance;
	}
	
	private ConnectionManagerImpl() throws RemoteException {
	}

	public void register(ClientRMICallbackRemote client) throws RemoteException, NotBoundException{
		temporaryView.add(client);
		System.out.println("New user in the game");
		System.out.println("Per ora vi sono " + temporaryView.size() + " giocatori");
		System.out.println("in attesa di altri giocatori");
		while(temporaryView.size()<2){ } //waiting for other players
		if(temporaryView.size()==2){
			System.out.println("The game starts!");
			Game.start(temporaryView.size());
		}
	}
	
	public static HashMap<Player, ClientRMICallbackRemote> getPlayersView() {
		return playersView;
	}
	
	public static void addPlayerView(Player player, ClientRMICallbackRemote client){
		playersView.put(player, client);
	}
	
	public static ClientRMICallbackRemote getView(Player player){
		return playersView.get(player);
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

	public static int choosePosition(Player player, ArrayList<String> descriptions) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.choosePosition(descriptions);
		return choice;
	}

	public static int chooseFamilyMember(Player player, ArrayList<String> descriptions) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.chooseFamilyMember(descriptions);
		return choice;
	}

	public static int askForAlternativeCost(Player player, ArrayList<String> cost, ArrayList<String> alternativeCost) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.askForAlternativeCost(cost, alternativeCost);
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

	public static void showPersonalBoard(Player player, String personalBoardDescription) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		client.showPersonalBoard(personalBoardDescription);
	}

	public static void addPlayers(){
		Player[] players = Game.getPlayers();
		int i = 0;
		for (ClientRMICallbackRemote client : temporaryView) {
			playersView.put(players[i], client);
			i++;
		}
	}
	
	public static void cantPassTurn(Player player) throws RemoteException{
		ClientRMICallbackRemote client = getView(player);
		client.cantPassTurn();
	}
	
	public static void roundBegins() throws RemoteException{
		for (Player player : playersView.keySet()){
			ClientRMICallbackRemote client = getView(player);
			client.roundBegins();
		}
	}
	
	public static void hasWon(String winner) throws RemoteException{
		for (Player player : playersView.keySet()){
			ClientRMICallbackRemote client = getView(player);
			client.hasWon(winner);
		}
	}

	public static int askForZone(ArrayList<String> zones, Player player) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.askForAction(zones);
		return choice;
	}

	public static int chooseActionPosition(Player player, String[] zonePositionsDescriptions) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		int choice = client.askForActionPosition(zonePositionsDescriptions);
		return choice;
	}

	public static void catchException(String message, Player player) throws RemoteException {
		ClientRMICallbackRemote client = getView(player);
		client.catchException(message);
	}

}
