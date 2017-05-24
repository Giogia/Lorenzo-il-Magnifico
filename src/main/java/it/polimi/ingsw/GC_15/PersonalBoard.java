package it.polimi.ingsw.GC_15;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.CARD.BuildingCardContainer;
import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.CharacterCardContainer;
import it.polimi.ingsw.CARD.TerritoryCardContainer;
import it.polimi.ingsw.CARD.VentureCardContainer;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.CARD.OncePerRoundLeaderCard;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.FaithPoints;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.Servants;
import it.polimi.ingsw.RESOURCE.Stones;
import it.polimi.ingsw.RESOURCE.VictoryPoints;
import it.polimi.ingsw.RESOURCE.Wood;

public class PersonalBoard {
	private PersonalBonusTile personalBonusTile;
	private boolean excommunicationCubes[];
	private ArrayList<CardContainer> cardContainers;
	private ArrayList<Resource> resources;
	private ArrayList<LeaderCard> activatedLeaderCards;
	private ArrayList<LeaderCard> oncePerRoundBonusLeaderCard;
	private ArrayList<Bonus> permanentBonus;
	
	public PersonalBoard() {
		activatedLeaderCards= new ArrayList<>();
		oncePerRoundBonusLeaderCard= new ArrayList<>();
		permanentBonus= new ArrayList<>();
		excommunicationCubes= new boolean[3];
		
		cardContainers.add(new TerritoryCardContainer());
		cardContainers.add(new BuildingCardContainer());
		cardContainers.add(new VentureCardContainer());
		cardContainers.add(new CharacterCardContainer());
		
		resources.add(new Coins());
		resources.add(new Wood());
		resources.add(new Stones());
		resources.add(new Servants());
		resources.add(new FaithPoints());
		resources.add(new MilitaryPoints());
		resources.add(new VictoryPoints());
	}
	

	public PersonalBonusTile getPersonalBonusTile() {
		return personalBonusTile;
	}
	
	public ArrayList<CardContainer> getContainerCards() {
		return cardContainers;
	}
	
	public ArrayList<LeaderCard> getActivatedLeaderCards() {
		return activatedLeaderCards;
	}

	public ArrayList<Resource> getResources() {
		return resources;
	}
	
	public ArrayList<LeaderCard> getOncePerRoundBonusLeaderCard() {
		return oncePerRoundBonusLeaderCard;
	}
	
	public boolean[] getExcommunicationCubes() {
		return excommunicationCubes;
	}
	
	public void putLeaderCard(LeaderCard leaderCard) {
		activatedLeaderCards.add(leaderCard);
	}
	
	//attivo l'effetto onePerRound della carta leader
	public void activateLeaderEffect(LeaderCard leaderCard){
		if(leaderCard.checkActivationCondition()){
			((OncePerRoundLeaderCard) leaderCard).activateOncePerRoundBonus();
		}
	}
	
	public void putDevelopmentCard(DevelopmentCard developmentCard){
		for(int i=0; i < cardContainers.size(); i++){
			if(cardContainers.get(i).getType() == developmentCard.developmentCardType){
				cardContainers.get(i).add(developmentCard);
			}
		}
	}
	
	public void activateDevelopmentCard(DevelopmentCard developmentCard){
		for(int i = 0; i < developmentCard.secondaryEffect.size(); i++){
			//TODO
		}
	}
	
	
	public ArrayList<Bonus> getPermanentBonus() {
		return permanentBonus;
	}
	
	//ADD methods -> similar to setters
	public void addPermanentBonus(Bonus bonus){
		permanentBonus.add(bonus);
	}
	
	public void addOncePerRoundBonusLeaderCard(LeaderCard leaderCard){
		oncePerRoundBonusLeaderCard.add(leaderCard);
	}
	
	public void addActivatedLeaderCards(LeaderCard leaderCard){
		activatedLeaderCards.add(leaderCard);
	}
	
	public void setEcxommunicationCube(int period){
		excommunicationCubes[period]=true;
	}

	public void setPersonalBonusTile(PersonalBonusTile personalBonusTile) {
		this.personalBonusTile = personalBonusTile;
	}
	
	
}//TODO metodo per dare il permanent bonus della carta che riceve
