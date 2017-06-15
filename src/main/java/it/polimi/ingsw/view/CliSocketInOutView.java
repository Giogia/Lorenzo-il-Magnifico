package it.polimi.ingsw.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.ActionSocket;

public class CliSocketInOutView implements Runnable{
	private ObjectInputStream socketIn;
	private Scanner scanner = new Scanner(System.in);
	private PrintWriter socketOut;
	
	public CliSocketInOutView(ObjectInputStream socketIn, PrintWriter socketOut) {
		this.socketIn = socketIn;
		this.socketOut = socketOut;
	}
	
	private int checkInputError(int min, int max){
		while (true){
			scanner = new Scanner(System.in);
			try{
				int risp = scanner.nextInt();
				if ( risp < min || risp > max){
					System.out.println("Incorrect input. Try again: ");
				}else{
					return risp;
				}
			}catch(InputMismatchException e){
				System.out.println("The input must be an integer between "+ min + " and "+ max + ". Try again: ");
			}
		}
	}
	
	@Override
	public void run() {
		while(true){
			// handles input messages coming from the server, just showing them to the user
			try {
				ActionSocket action =(ActionSocket) socketIn.readObject();
				ActionSocket.action question = action.getAction();
				
				switch (question) {
					case chooseName:
						System.out.println("Please, insert your name: ");
						String nameChoosen = scanner.nextLine();
						
						socketOut.println(nameChoosen);//the answer
						socketOut.flush();
						break;
						
					case chooseColor:
						System.out.println("What color do you want for your family members?");
						ArrayList<Color> availableColors = action.getAvailableColors();
						for (int i = 1; i < availableColors.size() + 1; i++) {
							System.out.println(i + ") " + availableColors.get(i - 1).toString().toLowerCase());
						}
						int choice = checkInputError(1, availableColors.size());
						
						socketOut.println(choice);
						socketOut.flush();
						break;
						
					case startTurn:
						String name = action.getMessage();
						System.out.println(name + ", it's your turn!");
						break;
						
					case turnChoice:
						System.out.println("What action do you want to do?\n");
						System.out.println("1) Place a family member \n2) See leader cards in your hand \n3) Activate "
								+ "effect of a leader card \n4) Stats \n5) Pass the turn\n");
						
						choice = checkInputError(1, 5); //the answer
						socketOut.println(choice);
						socketOut.flush();
						break;
						
					case moveAlreadyDone:
						System.out.println("You have already positioned a family member. Choose another action: ");
						break;
						
					case chooseZone:
						System.out.println("Choose the area you want to place the family member in:\n");
						System.out.println("1) Territories Tower \n2) Characters Tower \n3) Buildings Tower \n4) Ventures Tower \n" + 
							"5) Council Palace \n6) Harvest Area \n7) Production Area \n8) Market \n9) Go back");
						
						choice = checkInputError(1, 9);
						socketOut.println(choice);
						socketOut.flush();
						break;
						
					case choosePosition:
						System.out.println("Choose the position you want to put the family member in: ");
						for (int counter = 1; counter <= action.getPositions().length; counter ++) {
							String message = counter + ") " + action.getPositions()[counter - 1].getDescription();
							System.out.println(message);
						}
						int lastChoice = action.getPositions().length + 1;
						String lastMessage = lastChoice + ") Go back";
						System.out.println(lastMessage);
						
						choice = checkInputError(1, lastChoice);
						socketOut.println(choice);
						socketOut.flush();
						break;
						
					case askForAlternativeCost:
						System.out.println("The card you have chosen has 2 costs. Choose one: ");
						System.out.println("1) First cost");
						for (Resource resource : action.getCosts()) {
							System.out.println(resource.getDescription());
						}
						System.out.println("2) Secondary cost:");
						for (Resource alternativeResource : action.getAlternativeCosts()) {
							System.out.println(alternativeResource.getDescription());
						}
						
						choice = checkInputError(1, 2);
						socketOut.println(choice);
						socketOut.flush();
						break;
						
					case askForCouncilPrivilege:
						System.out.println("Choose the bonus of the Council Privilege: ");
						for (int counter = 1; counter <= action.getBonus().size(); counter++){
							String message = counter + ") " + action.getBonus().get(counter - 1).getDescription();
							System.out.println(message);
						}
						
						choice = checkInputError(1, action.getBonus().size());
						socketOut.println(choice);
						socketOut.flush();
						break;
						
					case askForServants:
						int numberOfServants = action.getNumberOfServants();
						System.out.println("You have " + numberOfServants + " servants. How many of them do you want to use?");
						
						choice = checkInputError(0, numberOfServants);
						socketOut.println(choice);
						socketOut.flush();
						break;
						
					case showDices:
						ArrayList<Dice> dices = action.getDices();
						for (Dice dice : dices) {
							System.out.println(dice.getDescription());
						}
						break;
						
					case hasWon:
						System.out.println("Congrats to "+action.getMessage()+". He's the winner!");
						break;
					
					case roundBegins:
						System.out.println("New round!");
						break;
						
					case askForInformation:
						System.out.println("Whose player's statistics do you want to see?");
						String[] playersNames = action.getPlayersName();
						for (int counter = 1; counter <= playersNames.length; counter++){
							String message = counter + ") " + playersNames[counter - 1];
							System.out.println(message);
						}
						lastChoice = playersNames.length + 1;
						lastMessage = lastChoice + ") Go back";
						System.out.println(lastMessage);
						
						choice = checkInputError(1, lastChoice);
						socketOut.println(choice);
						socketOut.flush();
						break;
						
					case showPersonalBoard:
						System.out.println(action.getPersonalBoard().getDescription());
						break;
						
					case cantPassTurn:
						System.out.println("You can't pass the turn, you have to place at least one family member.\n");
						break;
						
					case askForAction:
						System.out.println("Choose the zone you want to activate the action bonus in: ");
						ArrayList<ActionZone> zones = action.getZones();
						for (int i = 1; i <= zones.size(); i++) {
							System.out.println(i + ") " + zones.get(i-1).getDescription());
						}
						
						choice = checkInputError(1, zones.size());
						socketOut.println(choice);
						socketOut.flush();
						break;
						
					case askForActionPosition:
						Position[] positions = action.getPositions();
						System.out.println("Choose where you want to place your family member: ");
						for (int counter = 1; counter <= positions.length; counter ++) {
							String message = counter + ") " + positions[counter - 1].getDescription();
							System.out.println(message);
						}
						
						choice = checkInputError(1, positions.length);
						socketOut.println(choice);
						socketOut.flush();
						break;
						
					case catchException:
						System.out.println(action.getMessage() + "\n");
						break;
						
					case chooseFamilyMember:
						ArrayList<FamilyMember> familyMembers = action.getFamilyMembers();
						System.out.println("Choose the family member you want to use for the action: ");
						for (int counter = 1; counter <= familyMembers.size(); counter++){
							String message = counter + ") " + familyMembers.get(counter - 1).getDescription();
							System.out.println(message);
						}
						lastChoice = familyMembers.size() + 1;
						lastMessage =  lastChoice + ") Go back";
						System.out.println(lastMessage);
						
						choice = checkInputError(1, lastChoice);
						socketOut.println(choice);
						socketOut.flush();
						break;
						
					case askForLeaderCards:
						ArrayList<LeaderCard> leaderCards = action.getLeaderCards();
						System.out.println("Choose the leader card you want \n");
						for(int i=1;i<leaderCards.size()+1;i++){
							System.out.println(i+")"+leaderCards.get(i-1).getDescription()+" \n");
						}
						System.out.println(leaderCards.size()+1+") come back \n");
						
						choice = checkInputError(1, leaderCards.size()+1);
						socketOut.println(choice);
						socketOut.flush();
						break;
						
					case askForPersonalBonusTile:
						ArrayList<PersonalBonusTile> personalBonusTiles = action.getPersonalBonusTiles();
						System.out.println("Choose the personal bonus tile you want \n");
						for(int i=1;i<personalBonusTiles.size();i++){
							System.out.println(i+")"+personalBonusTiles.get(i).getDescription()+" \n");
						}
						choice = checkInputError(1, personalBonusTiles.size()-1);
						socketOut.println(choice);
						socketOut.flush();
						break;
						
					case askForLeaderCardAction:
						System.out.println("Choose the action you want to do with this Leader Card : \n");
						System.out.println("1) activate this leader Card \n2) Discard this leader card");
						System.out.println("3) come back \n");
						choice = checkInputError(1,3);
						socketOut.println(choice);
						socketOut.flush();
						break;
						
					case draftLeaderCards:
						ArrayList<LeaderCard> draftleaderCards = action.getLeaderCards();
						System.out.println("Choose the leader card you want \n");
						for(int i=1;i<draftleaderCards.size()+1;i++){
							System.out.println(i+")"+draftleaderCards.get(i-1).getDescription()+" \n");
						}
						choice = checkInputError(1, draftleaderCards.size());
						socketOut.println(choice);
						socketOut.flush();
						break;
						
					case askForCardEffect:
						DevelopmentCard developmentCard = action.getDevelopmentCard();
						System.out.println("Choose which effect of the card you want to acctivate : \n 1) First Effect: \n");
						int i =1;
						for(Bonus bonus : developmentCard.secondaryEffect){
							System.out.println(bonus.getDescription());
						}
						if(developmentCard instanceof Building){
							Building building = (Building) developmentCard;
							i++;
							System.out.println("2) Second Effect: \n");
							for(Bonus bonus : building.tertiaryEffect){
								System.out.println(bonus.getDescription());
							}
						}
						i++;
						System.out.println(i+") Don't activate this card's Effect \n");
						choice = checkInputError(1, i);
						socketOut.println(choice);
						socketOut.flush();
						break;
					
					case askForExcommunication:
						ArrayList<ExcommunicationTile> excommunicationTiles = action.getExcommunicationTiles();
						ExcommunicationTile excommunicationTile = excommunicationTiles.get(0);
						System.out.println(excommunicationTile.getDescription());
						System.out.println("Do you want to be excommunicated? \n1) No \n2) Yes");
						
						choice = checkInputError(1, 2);
						socketOut.println(choice);
						socketOut.flush();
						
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
