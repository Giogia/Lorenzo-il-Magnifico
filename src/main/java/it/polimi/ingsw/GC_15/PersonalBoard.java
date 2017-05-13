package it.polimi.ingsw.GC_15;

import java.util.ArrayList;

import javax.management.InstanceAlreadyExistsException;

public class PersonalBoard {
	private PersonalBonusTile personalBonusTile;
	private boolean excommunicationCubes[];
	private ArrayList<ContainerCard> developmentCards;
	private ArrayList<Resource> resources;
	private ArrayList<LeaderCard> activatedLeaderCards;
	private ArrayList<LeaderCard> oncePerRoundBonusLeaderCard;
	private ArrayList<Bonus> permanentBonus;
	
	public PersonalBoard() {
		personalBonusTile= new PersonalBonusTile();
		activatedLeaderCards= new ArrayList<>();
		oncePerRoundBonusLeaderCard= new ArrayList<>();
		permanentBonus= new ArrayList<>();
		excommunicationCubes= new boolean[3];
		
		developmentCards.add(new ContainerTerritoryCard());
		developmentCards.add(new ContainerBuildingCard());
		developmentCards.add(new ContainerVentureCard());
		developmentCards.add(new ContainerCharacterCard());
		
		resources.add(new Coins());
		resources.add(new Wood());
		resources.add(new Stones());
		resources.add(new Servants());
		resources.add(new FaithPoints());
		resources.add(new MilitaryPoints());
		resources.add(new VictoryPoints());
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
	
	//TODO: da gestire il caso in cui la leader card non è nè l'una nè l'altra
	public void activateLeaderEffect(LeaderCard leaderCard){
		if(leaderCard.checkActivationCondition()){
			if (leaderCard instanceof OncePerRoundLeaderCard){
				leaderCard.activateOncePerRoundBonus();
			}
			if(leaderCard instanceof PermanentLeaderCard){
				leaderCard.activatePermanentBonus();
			}
		}
	}
	
	public void putDevelopmentCard(DevelopmentCard developmentCard){
		for(int i=0; i < developmentCards.size(); i++){
			if(developmentCards.get(i).developmentCardType==developmentCard.
		}
	}
	
	public void activateDevelopmentCard(DevelopmentCard developmentCard){
		//TODO
	}
	
	public void activateBonusTiles(DevelopmentCardType developmentCardType){
		//TODO
	}
	
	public void addPermanentBonus(Bonus bonus){
		//TODO
	}
}
