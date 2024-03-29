package it.polimi.ingsw.GC_15;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.BONUS.CouncilPrivilegeBonus;
import it.polimi.ingsw.CARD.*;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.manager.User;

public class Player implements Serializable{
	private String name;
	private Color color;
	public enum Color {RED, BLUE, YELLOW, GREEN}
	private Board board;
	private PersonalBoard personalBoard;
	private ArrayList<LeaderCard> leaderCardInHand = new ArrayList<>();
	private ArrayList<FamilyMember> familyMembers = new ArrayList<>();
	
	public Player(String name, Color color) {
		this.name=name;
		this.color=color;
		personalBoard = new PersonalBoard();
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public Color getColor() {
		return color;
	}

	public Board getBoard() {
		return board;
	}
	
	public ArrayList<LeaderCard> getLeaderCardInHand() {
		return leaderCardInHand;
	}
	
	public void addLeadercard(LeaderCard leaderCard){
		leaderCardInHand.add(leaderCard);
	}
	
	public void setFamilyMember(ArrayList<FamilyMember> familyMembers) {
		this.familyMembers = familyMembers;
	}
	
	public void setLeaderCardInHand(ArrayList<LeaderCard> leaderCard){
		leaderCardInHand=leaderCard;
	}
	
	public void discardLeaderCard(LeaderCard leaderCard){
		//find the right leaderCard in hand
		for(int i=0; i < leaderCardInHand.size(); i++){
			if (leaderCardInHand.get(i).equals(leaderCard)){
				//remove leaderCard from arrayList
				leaderCardInHand.remove(i);
			}
		}
	}
	
	public boolean wantsToRestartFaithPoints(){
		boolean decision=true;//TODO PERMANENT
		return decision;
	}
	
	public void setFamilyMemberPosition(FamilyMember familyMember, Position position) throws MyException, IOException, TimeExpiredException{
		position.addFamilyMember(familyMember);
		if (!familyMember.getDice().getDiceColour().equals(DiceColour.Fake)){
			if (familyMembers != null){
				int i = 0;
				while (i < familyMembers.size()) {
					if (familyMember.getDice().equals(familyMembers.get(i).getDice())){
						familyMembers.remove(familyMembers.get(i));
					}
					else {
						i++;
					}
				}
			}
		}
	}
	
	public void useServants(int servantsNumber, FamilyMember familyMember){
		personalBoard.getResource(ResourceType.servants).addAmount(-servantsNumber);
		familyMember.addValue(servantsNumber);
	}
	
	public void activateLeaderCard(LeaderCard leaderCard){
		personalBoard.putLeaderCard(leaderCard);
		//delete leader card from the hand
		for (int i=0; i < leaderCardInHand.size(); i++){
			if (leaderCardInHand.get(i).equals(leaderCard)) leaderCardInHand.remove(i);
		}
	}
	
	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}
	
	public void choosePrivilegeCouncil(CouncilPrivilegeBonus councilPrivilegeBonus) throws IOException, MyException, TimeExpiredException{
		//find coins in arraylist
		int index= personalBoard.getResources().lastIndexOf(new Coins(0,1));
		//give a coin to the player
		personalBoard.getResources().get(index).addvalue(1);
		//give councilPrivilegeBonus
		councilPrivilegeBonus.getImmediateBonus(this);
	}
	
	public ArrayList<DevelopmentCard> getCardsToActivate(DevelopmentCardType developmentCardType) {
		ArrayList<CardContainer> cardContainers = personalBoard.getCardContainers();
		for (CardContainer cardContainer : cardContainers) {
			if (cardContainer.getType().equals(developmentCardType)){
				return cardContainer.getDevelopmentCards();
			}
		}
		return null;
	}
	
	public ArrayList<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}
	
	public String getName() {
		return name;
	}

}