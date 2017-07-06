package it.polimi.ingsw.Cli;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.ConnectionManager;
import it.polimi.ingsw.manager.ConnectionManagerRmiServer;
import it.polimi.ingsw.manager.ConnectionManagerRmiServerImpl;
import it.polimi.ingsw.minigame.BoardProxy;
import it.polimi.ingsw.minigame.DevelopmentCardProxy;
import it.polimi.ingsw.minigame.GameProxy;
import it.polimi.ingsw.minigame.PositionProxy;
import it.polimi.ingsw.minigame.TowerFloorProxy;

public class CliRmiView implements CliRmi{
	static Scanner scanner;
	private final static int RMI_PORT = 52365;
	private static final String NAME = "connectionManager";
	private static ConnectionManagerRmiServerImpl connectionManagerRmiServerImpl;
	private static CliRmiView client;
	
	public static void main(String[] args) throws RemoteException, NotBoundException, MyException{
		Registry registry = LocateRegistry.getRegistry("localhost", RMI_PORT);
		System.out.println("preso referenza al registry");
		
		ConnectionManager connectionManager = (ConnectionManager) registry.lookup(NAME);
		System.out.println("connesso al connectionManager");
		
		client = new CliRmiView();
		
		UnicastRemoteObject.exportObject(client, 0);
		connectionManager.register(client);//client register himself on server
	}
	
	public void setConnectionManagerRmiServer(ConnectionManagerRmiServer connectionManagerRmiServer) throws RemoteException{
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(new CliRmiCallback(connectionManagerRmiServer, client));
	}
	
	@Override
	public void integerError() throws RemoteException {
		System.out.println("The integer doesn't match any possible choice");
	}
	
	@Override
	public void wrongInput() throws RemoteException {
		System.out.println("The input must be an integer! Try again!");
	}
	
	@Override
	public void isNotYourTurn() throws RemoteException {
		System.out.println("Please, wait your turn!");
	}
	
	@Override
	public void leftGame(String playerDisconnected) throws RemoteException {
		System.out.println(playerDisconnected + " left the game!");
	}
	
	@Override
	public void reconnectedToGame(String name) throws RemoteException {
		System.out.println(name + " has reconnected himself to the game!");
	}
	
	public void askName(){
		scanner= new Scanner(System.in);
		System.out.println("Please, insert your name: ");
	}
	
	public void askColor(String[] availableColors){
		System.out.println("What color do you want for your family members?");
		for (int i = 1; i < availableColors.length + 1; i++) {
			System.out.println(i + ") " + availableColors[i - 1].toLowerCase());
		}
	}

	@Override
	public void startTurn(String name) {
		System.out.println(name + ", it's your turn!");
	}

	@Override
	public void turnChoice() {
		System.out.println("What action do you want to do?\n");
		System.out.println("1) Place a family member \n2) See leader cards in your hand \n3) Activate "
				+ "effect of a leader card \n4) Stats \n5) Pass the turn\n");
	}

	@Override
	public void moveAlreadyDone() {
		System.out.println("You have already positioned a family member. Choose another action: ");
	}

	@Override
	public void chooseZone() {
		System.out.println("Choose the area you want to place the family member in:\n");
		System.out.println("1) Territories Tower \n2) Characters Tower \n3) Buildings Tower \n4) Ventures Tower \n" + 
			"5) Council Palace \n6) Harvest Area \n7) Production Area \n8) Market \n9) Go back");
	}

	@Override
	public void choosePosition(Position[] positions) {
		System.out.println("Choose the position you want to put the family member in: ");
		for (int counter = 1; counter <= positions.length; counter ++) {
			String message = counter + ") " + positions[counter - 1].getDescription();
			System.out.println(message);
		}
		int lastChoice = positions.length + 1;
		String lastMessage = lastChoice + ") Go back";
		System.out.println(lastMessage);
	}

	@Override
	public void chooseFamilyMember(ArrayList<FamilyMember> familyMembers) {
		System.out.println("Choose the family member you want to use for the action: ");
		for (int counter = 1; counter <= familyMembers.size(); counter++){
			String message = counter + ") " + familyMembers.get(counter - 1).getDescription();
			System.out.println(message);
		}
		int lastChoice = familyMembers.size() + 1;
		String lastMessage =  lastChoice + ") Go back";
		System.out.println(lastMessage);
	}

	@Override
	public void askForAlternativeCost(ArrayList<Resource> costs, ArrayList<Resource> alternativeCosts) {
		System.out.println("The card you have chosen has 2 costs. Choose one: ");
		System.out.println("1) First cost");
		for (Resource resource : costs) {
			System.out.println(resource.getDescription());
		}
		System.out.println("2) Secondary cost:");
		for (Resource alternativeResource : alternativeCosts ) {
			System.out.println(alternativeResource.getDescription());
		}
	}

	@Override
	public void askForCouncilPrivilege(ArrayList<ResourceBonus> councilPrivileges) {
		System.out.println("Choose the bonus of the Council Privilege: ");
		for (int counter = 1; counter <= councilPrivileges.size(); counter++){
			String message = counter + ") " + councilPrivileges.get(counter - 1).getDescription();
			System.out.println(message);
		}
	}

	@Override
	public void askForServants(int numberOfServants) {
		System.out.println("You have " + numberOfServants + " servants. How many of them do you want to use?");
	}

	@Override
	public void askForInformation(String[] playersNames) {
		System.out.println("Whose player's statistics do you want to see?");
		for (int counter = 1; counter <= playersNames.length; counter++){
			String message = counter + ") " + playersNames[counter - 1];
			System.out.println(message);
		}
		int lastChoice = playersNames.length + 1;
		String lastMessage = lastChoice + ") Go back";
		System.out.println(lastMessage);
	}

	@Override
	public void showPersonalBoard(PersonalBoard personalBoard) {
		System.out.println(personalBoard.getDescription());
	}
	
	@Override
	public void cantPassTurn() {
		System.out.println("You can't pass the turn, you have to place at least one family member.\n");
		
	}
	
	public void roundBegins(Board board){
		System.out.println("New round!");
	}
	
	public void hasWon(String winnerName){
		System.out.println("Congrats to "+winnerName+". He's the winner!");
	}

	@Override
	public void askForAction(ArrayList<ActionZone> zones) {
		System.out.println("Choose the zone you want to activate the action bonus in: ");
		for (int i = 1; i <= zones.size(); i++) {
			System.out.println(i + ") " + zones.get(i-1).getDescription());
		}
	}

	@Override
	public void askForActionPosition(Position[] positions) {
		System.out.println("Choose where you want to place your family member: ");
		for (int counter = 1; counter <= positions.length; counter ++) {
			String message = counter + ") " + positions[counter - 1].getDescription();
			System.out.println(message);
		}
	}

	@Override
	public void catchException(String message) {
		System.out.println(message + "\n");
	}
	
	public void showDices(ArrayList<Dice> dices){
		for (Dice dice : dices) {
			System.out.println(dice.getDescription());
		}
	}


	@Override
	public void askForLeaderCardAction() throws RemoteException {
		System.out.println("Choose the action you want to do with this Leader Card : \n");
		System.out.println("1) activate this leader Card \n2) Discard this leader card");
		System.out.println("3) come back \n");
	}


	@Override
	public void askForLeaderCard(ArrayList<LeaderCard> leaderCards) throws RemoteException{
		System.out.println("Choose the leader card you want \n");
		for(int i=1;i<leaderCards.size()+1;i++){
			System.out.println(i+")"+leaderCards.get(i-1).getDescription()+" \n");
		}
		System.out.println(leaderCards.size()+1+") come back \n");
	}


	@Override
	public void askForPersonalBonusTile(ArrayList<PersonalBonusTile> personalBonusTiles) throws RemoteException {
		System.out.println("Choose the personal bonus tile you want \n");
		for(int i=1;i<personalBonusTiles.size();i++){
			System.out.println(i+")"+personalBonusTiles.get(i).getDescription()+" \n");
		}
	}


	@Override
	public void draftLeaderCard(ArrayList<LeaderCard> leaderCards) throws RemoteException{
		System.out.println("Choose the leader card you want \n");
		for(int i=1;i<leaderCards.size()+1;i++){
			System.out.println(i+")"+leaderCards.get(i-1).getDescription()+" \n");
		}
	}


	@Override
	public void askForExcommunication(ExcommunicationTile excommunicationTile) throws RemoteException {
		System.out.println(excommunicationTile.getDescription());
		System.out.println("Do you want to be excommunicated? \n1) No \n2)Yes");
	}
		
	@Override
	public void askForCardEffect(DevelopmentCard developmentCard) throws RemoteException {
		System.out.println("Choose which effect of the card you want to acctivate : \n 1) First Effect: \n");
		int i =1;
		for(Bonus bonus : developmentCard.secondaryEffect){
			if(bonus instanceof ResourceBonus){
				System.out.println("\n"+i+")"+bonus.getDescription());
				i++;
			}
			else
				System.out.println(bonus.getDescription());
		}
		System.out.println(i+") Don't activate this card's Effect \n");
	}
	
	@Override
	public void askForUsername() throws RemoteException {
		System.out.println("Please, insert your username: ");
	}
	
	@Override
	public void usernameHasAlreadyChosen() throws RemoteException {
		System.out.println("The username choosen has already chosen. Please, choose another one:");	
	}

	@Override
	public void timeExpired() throws RemoteException {
		System.out.println("TIME IS EXPIRED! \n");
	}
	

	public void startGame(Game game) throws RemoteException {
		System.out.println("The game starts!");
	}

	@Override
	public void startGame(GameProxy game) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDueTowerFloorOccupied(TowerFloorProxy towerFloorProxy, DevelopmentCardProxy card) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void roundBegins(GameProxy game) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDuePositionOccupied(PositionProxy positionProxy) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}

