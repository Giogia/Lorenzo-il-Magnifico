package it.polimi.ingsw.GC_15;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.LeaderCard;

public class Player {
	private String name;
	private Color color;
	public enum Color {RED, BLUE, YELLOW, GREEN};
	private Board board;
	private PersonalBoard personalBoard=new PersonalBoard();
	private LeaderCard leaderCardInHand[];
	private FamilyMember familyMember[];
	
	public Player(String name, Color color, Board board) {
		this.name=name;
		this.color=color;
		this.board=board;
	}
	
	public void setFamilyMember(FamilyMember[] familyMember) {
		this.familyMember = familyMember;
	}
	
	public void setLeaderCardInHand(LeaderCard leaderCard[]){
		leaderCardInHand=leaderCard;
	}
	
	private void discardLeaderCard(LeaderCard leaderCard){
		for(int i=0; i < leaderCardInHand.length; i++){
			if (leaderCardInHand[i].equals(leaderCard)){
				leaderCardInHand[i]=null;
			}
		}
	}
	
	public boolean wantsToRestartFaithPoints(){
		boolean decision=true;//TODO: Da vedere come fare per tale assegnamento
		return decision;
	}
	
	public void setFamilyMemberPosition(FamilyMember familyMember,Position position) {
		position.addFamilyMember(familyMember);
	}
	
	public boolean setFamilyMemberPosition(FamilyMember familyMember,Position position, int index){
		if(position.addFamilyMember(familyMember, index)) {
			return true;
		}
		return false;
	}
	
	public void useServants(int value, FamilyMember familyMember){
		//TODO: diminuisci il valore dei serventi nella personal board
		familyMember.addValue(value);
	}
	
	public void activateLeaderCard(LeaderCard leaderCard){
		personalBoard.putLeaderCard(leaderCard);
		//delete leader card from the hand
		for (int i=0; i < leaderCardInHand.length; i++){
			if (leaderCardInHand[i].equals(leaderCard)) leaderCardInHand[i]=null;
		}
	}
	
	public void activateLeaderEffect(LeaderCard leaderCard){
		personalBoard.activateLeaderEffect(leaderCard);
	}
	
	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}
	
	public void choosePrivilegeCouncil(ResourceBonus resourceBonus){
		//TODO: aumenta il valore di uno delle monete nella personal board
		//resourceBonus.immediateBonus(); 
	}
	
	public DevelopmentCard[] getCardsToActivate(DevelopmentCardType developmentCardType) {
		//TODO
	}
	
	public void chooseCard(DevelopmentCard developmentCard) {
		//TODO
	}
	
	public void chooseCardToCopy(){
		//TODO
	}
}