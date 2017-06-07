package it.polimi.ingsw.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class ClientRMICallbackImpl implements View, ClientRMICallbackRemote {
	static Scanner scanner;
	
	public String askName(){
		System.out.println("Please, insert your name: ");
		return scanner.nextLine();
	}
	
	public int askColor(){
		System.out.println("What color do you want for your family members?\n1)Red\n2)Blue\n3)Yellow\n4)Green\n");
		return checkInputError(1, 4);
	}

	@Override
	public void startTurn(String name) {
		System.out.println(name + ", it's your turn!");
	}

	@Override
	public int turnChoice() {
		System.out.println("What action do you want to do?\n");
		System.out.println("1) Place a family member \n2) Activate leader card \n3) Discard leader card \n4) Activate "
				+ "effect of a leader card \n5) Stats \n6) Pass the turn\n");
		return checkInputError(1, 6);
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
	public int choosePosition(ArrayList<String> descriptions) {
		System.out.println("Choose the position you want to put the family member in: ");
		for (int counter = 1; counter <= descriptions.size(); counter ++) {
			String message = counter + ") " + descriptions.get(counter - 1);
			System.out.println(message);
		}
		int lastChoice = descriptions.size() + 1;
		String lastMessage = lastChoice + ") Go back";
		System.out.println(lastMessage);
		return checkInputError(1, lastChoice);
	}

	@Override
	public int chooseFamilyMember(ArrayList<String> descriptions) {
		System.out.println("Choose the family member you want to use for the action: ");
		for (int counter = 1; counter <= descriptions.size(); counter++){
			String message = counter + ") " + descriptions.get(counter - 1);
			System.out.println(message);
		}
		int lastChoice = descriptions.size() + 1;
		String lastMessage =  lastChoice + ") Go back";
		System.out.println(lastMessage);
		return checkInputError(1, lastChoice);
	}

	@Override
	public int askForAlternativeCost(ArrayList<String> costDescriptions, ArrayList<String> alternativeCostDescriptions) {
		System.out.println("The card you have chosen has 2 costs. Choose one: ");
		System.out.println("1) First cost");
		for (String description : costDescriptions) {
			System.out.println(description);
		}
		System.out.println("2) Secondary cost:");
		for (String description : alternativeCostDescriptions ) {
			System.out.println(description);
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
	public void showPersonalBoard(String personalBoardDescription) {
		System.out.println(personalBoardDescription);
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
	public int askForAction(ArrayList<String> zonesDescriptions) {
		System.out.println("Choose the zone you want to activate the action bonus in: ");
		for (int i = 1; i <= zonesDescriptions.size(); i++) {
			System.out.println(i + ") " + zonesDescriptions.get(i-1));
		}
		return checkInputError(1, zonesDescriptions.size());
	}

	@Override
	public int askForActionPosition(String[] zonePositionsDescriptions) {
		System.out.println("Choose where you want to place your family member: ");
		for (int counter = 1; counter <= zonePositionsDescriptions.length; counter ++) {
			String message = counter + ") " + zonePositionsDescriptions[counter - 1];
			System.out.println(message);
		}
		return checkInputError(1, zonePositionsDescriptions.length);
	}

	@Override
	public void catchException(String message) {
		System.out.println(message + "\n");
	}
}
