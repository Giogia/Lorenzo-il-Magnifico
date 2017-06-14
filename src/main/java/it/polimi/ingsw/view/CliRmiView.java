package it.polimi.ingsw.view;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.ConnectionManager;

public class CliRmiView implements ClientRMICallbackRemote{
	static Scanner scanner;
	private final static int RMI_PORT = 52365;
	private static final String NAME = "connectionManager";
	
	public static void main(String[] args) throws RemoteException, NotBoundException, MyException{
		Registry registry = LocateRegistry.getRegistry("localhost", RMI_PORT);
		System.out.println("preso referenza al registry");
		
		ConnectionManager connectionManager = (ConnectionManager) registry.lookup(NAME);
		System.out.println("connesso al connectionManager");
		
		CliRmiView client = new CliRmiView();

		UnicastRemoteObject.exportObject(client, 0);

		connectionManager.register(client);
	}
	
	
	public String askName(){
		scanner= new Scanner(System.in);
		System.out.println("Please, insert your name: ");
		return scanner.nextLine();
	}
	
	public int askColor(String[] availableColors){
		System.out.println("What color do you want for your family members?");
		for (int i = 1; i < availableColors.length + 1; i++) {
			System.out.println(i + ") " + availableColors[i - 1].toLowerCase());
		}
		return checkInputError(1, availableColors.length);
	}

	@Override
	public void startTurn(String name) {
		System.out.println(name + ", it's your turn!");
	}

	@Override
	public int turnChoice() {
		System.out.println("What action do you want to do?\n");
		System.out.println("1) Place a family member \n2) See leader cards in your hand \n3) Activate "
				+ "effect of a leader card \n4) Stats \n5) Pass the turn\n");
		return checkInputError(1, 5);
	}

	@Override
	public void moveAlreadyDone() {
		System.out.println("You have already positioned a family member. Choose another action: ");
	}

	@Override
	public int chooseZone() {
		System.out.println("Choose the area you want to place the family member in:\n");
		System.out.println("1) Territories Tower \n2) Characters Tower \n3) Buildings Tower \n4) Ventures Tower \n" + 
			"5) Council Palace \n6) Harvest Area \n7) Production Area \n8) Market \n9) Go back");
		return checkInputError(1, 9);
	}

	@Override
	public int choosePosition(Position[] positions) {
		System.out.println("Choose the position you want to put the family member in: ");
		for (int counter = 1; counter <= positions.length; counter ++) {
			String message = counter + ") " + positions[counter - 1].getDescription();
			System.out.println(message);
		}
		int lastChoice = positions.length + 1;
		String lastMessage = lastChoice + ") Go back";
		System.out.println(lastMessage);
		return checkInputError(1, lastChoice);
	}

	@Override
	public int chooseFamilyMember(ArrayList<FamilyMember> familyMembers) {
		System.out.println("Choose the family member you want to use for the action: ");
		for (int counter = 1; counter <= familyMembers.size(); counter++){
			String message = counter + ") " + familyMembers.get(counter - 1).getDescription();
			System.out.println(message);
		}
		int lastChoice = familyMembers.size() + 1;
		String lastMessage =  lastChoice + ") Go back";
		System.out.println(lastMessage);
		return checkInputError(1, lastChoice);
	}

	@Override
	public int askForAlternativeCost(ArrayList<Resource> costs, ArrayList<Resource> alternativeCosts) {
		System.out.println("The card you have chosen has 2 costs. Choose one: ");
		System.out.println("1) First cost");
		for (Resource resource : costs) {
			System.out.println(resource.getDescription());
		}
		System.out.println("2) Secondary cost:");
		for (Resource alternativeResource : alternativeCosts ) {
			System.out.println(alternativeResource.getDescription());
		}
		return checkInputError(1, 2);
	}

	@Override
	public int askForCouncilPrivilege(ArrayList<ResourceBonus> councilPrivileges) {
		System.out.println("Choose the bonus of the Council Privilege: ");
		for (int counter = 1; counter <= councilPrivileges.size(); counter++){
			String message = counter + ") " + councilPrivileges.get(counter - 1).getDescription();
			System.out.println(message);
		}
		return checkInputError(1, councilPrivileges.size());
	}

	@Override
	public int askForServants(int numberOfServants) {
		System.out.println("You have " + numberOfServants + " servants. How many of them do you want to use?");
		return checkInputError(0, numberOfServants);	
	}

	@Override
	public int askForInformation(String[] playersNames) {
		System.out.println("Whose player's statistics do you want to see?");
		for (int counter = 1; counter <= playersNames.length; counter++){
			String message = counter + ") " + playersNames[counter - 1];
			System.out.println(message);
		}
		int lastChoice = playersNames.length + 1;
		String lastMessage = lastChoice + ") Go back";
		System.out.println(lastMessage);
		return checkInputError(1, lastChoice);
	}

	@Override
	public void showPersonalBoard(PersonalBoard personalBoard) {
		System.out.println(personalBoard.getDescription());
	}
	
	@Override
	public void cantPassTurn() {
		System.out.println("You can't pass the turn, you have to place at least one family member.\n");
		
	}

	private int checkInputError(int min, int max){
		while (true){
			scanner = new Scanner(System.in);
			try{
				int choice = scanner.nextInt();
				if ( choice < min || choice > max){
					System.out.println("Incorrect input. Try again: ");
				}else{
					return choice;
				}
			}catch(InputMismatchException e){
				System.out.println("The input must be an integer between "+ min + " and "+ max + ". Try again: ");
			}
		}
	}
	
	public void roundBegins(){
		System.out.println("New round!");
	}
	
	public void hasWon(String winnerName){
		System.out.println("Congrats to "+winnerName+". He's the winner!");
	}

	@Override
	public int askForAction(ArrayList<ActionZone> zones) {
		System.out.println("Choose the zone you want to activate the action bonus in: ");
		for (int i = 1; i <= zones.size(); i++) {
			System.out.println(i + ") " + zones.get(i-1).getDescription());
		}
		return checkInputError(1, zones.size());
	}

	@Override
	public int askForActionPosition(Position[] positions) {
		System.out.println("Choose where you want to place your family member: ");
		for (int counter = 1; counter <= positions.length; counter ++) {
			String message = counter + ") " + positions[counter - 1].getDescription();
			System.out.println(message);
		}
		return checkInputError(1, positions.length);
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
	public int askForLeaderCardAction() throws RemoteException {
		System.out.println("Choose the action you want to do with this Leader Card : \n");
		System.out.println("1) activate this leader Card \n2) Discard this leader card");
		System.out.println("3) come back \n");
		return checkInputError(1, 3);
	}


	@Override
	public int askForLeaderCard(ArrayList<LeaderCard> leaderCards) throws RemoteException{
		System.out.println("Choose the leader card you want \n");
		for(int i=1;i<leaderCards.size()+1;i++){
			System.out.println(i+")"+leaderCards.get(i-1).getDescription()+" \n");
		}
		System.out.println(leaderCards.size()+1+") come back \n");
		return checkInputError(1, leaderCards.size());
	}


	@Override
	public int askForPersonalBonusTile(ArrayList<PersonalBonusTile> personalBonusTiles) throws RemoteException {
		System.out.println("Choose the personal bonus tile you want \n");
		for(int i=1;i<personalBonusTiles.size();i++){
			System.out.println(i+")"+personalBonusTiles.get(i).getDescription()+" \n");
		}
		return checkInputError(1, personalBonusTiles.size()-1);
	}
}

