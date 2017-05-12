package it.polimi.ingsw.GC_15;

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
	
	public void setFamilyMemberPosition(FamilyMember familyMember,Zone zone,Position position) {
		//TODO: serve il controller
	}
	
}
