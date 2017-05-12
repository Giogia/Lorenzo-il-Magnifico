package it.polimi.ingsw.GC_15;

import java.util.ArrayList;

public class PersonalBoard {
	public final int NumberOfResources=7;
	private ArrayList<DevelopmentCard> cards;
	private ArrayList<LeaderCard> activatedLeaderCards;
	private PersonalBonusTile personalBonusTile;
	private ArrayList<LeaderCard> oncePerRoundBonusLeaderCard;
	private boolean excommunicationCubes[];
	private ArrayList<Bonus> permanentBonus;
	private Resource resource[];
	
	public PersonalBoard() {
		personalBonusTile= new PersonalBonusTile();
		cards= new ArrayList<>();
		activatedLeaderCards= new ArrayList<>();
		oncePerRoundBonusLeaderCard= new ArrayList<>();
		excommunicationCubes= new boolean[3];
		permanentBonus= new ArrayList<>();
		resource= new Resource[NumberOfResources];
	}
	
	public void putLeaderCard(LeaderCard leaderCard) {
		//find first free poition
		if (activatedLeaderCards.isEmpty()){
			activatedLeaderCards.add(leaderCard);
			return;
		}
		for (int i=0; i < activatedLeaderCards.size(); i++) {
			if(activatedLeaderCards.get(i)==null) {
				activatedLeaderCards.set(i, leaderCard);
				return; 
			}
		}
	}
	
	public void activateLeaderEffect(LeaderCard leaderCard){
		//TODO
	}
	
	public void putDevelopmentCard(DevelopmentCard developmentCard){
		//TODO
	}
	
	public void activateDevelopmentCard(DevelopmentCard developmentCard){
		//TODO
	}
	
	public void activateBonusTiles(DevelopmentCardType developmentCardType){
		//TODO
	}
}
