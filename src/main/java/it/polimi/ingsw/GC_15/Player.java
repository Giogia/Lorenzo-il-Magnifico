package it.polimi.ingsw.GC_15;

import java.util.ArrayList;

import org.omg.PortableServer.Servant;

import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.BONUS.CouncilPrivilegeBonus;
import it.polimi.ingsw.CARD.*;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.Servants;

public class Player {
	private String name;
	private Color color;
	public enum Color {RED, BLUE, YELLOW, GREEN};
	private Board board;
	private PersonalBoard personalBoard=new PersonalBoard();
	private ArrayList<LeaderCard> leaderCardInHand;
	private ArrayList<FamilyMember> familyMembers;
	
	public Player(String name, Color color, Board board) {
		this.name=name;
		this.color=color;
		this.board=board;
	}
	
	public void setFamilyMember(ArrayList<FamilyMember> familyMembers) {
		this.familyMembers = familyMembers;
	}
	
	public void setLeaderCardInHand(ArrayList<LeaderCard> leaderCard){
		leaderCardInHand=leaderCard;
	}
	
	public void discardLeaderCard(LeaderCard leaderCard){
		//trovo la posizione dove si trova la leaderCard
		for(int i=0; i < leaderCardInHand.size(); i++){
			if (leaderCardInHand.get(i).equals(leaderCard)){
				//rimuovo la leaderCard dall'ArrayList
				leaderCardInHand.remove(i);
			}
		}
	}
	
	public boolean wantsToRestartFaithPoints(){
		boolean decision=true;//TODO
		return decision;
	}
	
	public void setFamilyMemberPosition(FamilyMember familyMember, Position position) {
		position.addFamilyMember(familyMember);
	}
	
	public void useServants(int value, FamilyMember familyMember){
		//trovo l'indice nell'arrayList dove vi sono i serventi
		int index= personalBoard.getResources().lastIndexOf(new Servants(0,1));
		//decremento il valore dei serventi di - value
		personalBoard.getResources().get(index).addvalue(-value);
		familyMember.addValue(value);
	}
	
	public void activateLeaderCard(LeaderCard leaderCard){
		personalBoard.putLeaderCard(leaderCard);
		//delete leader card from the hand
		for (int i=0; i < leaderCardInHand.size(); i++){
			if (leaderCardInHand.get(i).equals(leaderCard)) leaderCardInHand.remove(i);
		}
	}
	
	public void activateLeaderEffect(LeaderCard leaderCard){
		personalBoard.activateLeaderEffect(leaderCard);
	}
	
	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}
	
	public void choosePrivilegeCouncil(CouncilPrivilegeBonus councilPrivilegeBonus){
		//trovo l'indice nell'arrayList dove vi sono le monete
		int index= personalBoard.getResources().lastIndexOf(new Coins(0,1));
		//do al player una moneta
		personalBoard.getResources().get(index).addvalue(1);
		//TODO: do il councilPrivilegeBonus
		councilPrivilegeBonus.getImmediateBonus(this);
	}
	
	public ArrayList<DevelopmentCard> getCardsToActivate(DevelopmentCardType developmentCardType) {
		ArrayList<CardContainer> cardContainers = personalBoard.getCardContainers();
		for (CardContainer cardContainer : cardContainers) {
			if (cardContainer.type.equals(developmentCardType)){
				return cardContainer.getDevelopmentCards();
			}
		}
		return null;
	}
	
	public void chooseCard(DevelopmentCard developmentCard) {
		//TODO
	}
	
	public void chooseCardToCopy(){
		//TODO
	}
	
	public ArrayList<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}

}