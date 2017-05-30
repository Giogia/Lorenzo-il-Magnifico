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
import it.polimi.ingsw.RESOURCE.*;

public class PersonalBoard {
	private PersonalBonusTile personalBonusTile;
	private boolean[] excommunicationCubes;
	private ArrayList<CardContainer> cardContainers;
	private ArrayList<Resource> resources;
	private ArrayList<LeaderCard> activatedLeaderCards;
	private ArrayList<LeaderCard> oncePerRoundBonusLeaderCard;
	private ArrayList<Bonus> permanentBonus;
	
	public PersonalBoard() {
		personalBonusTile = new PersonalBonusTile();
		excommunicationCubes= new boolean[3];
		cardContainers = new ArrayList<>();
		resources= new ArrayList<>();
		activatedLeaderCards= new ArrayList<>();
		oncePerRoundBonusLeaderCard= new ArrayList<>();
		permanentBonus= new ArrayList<>();
		
		cardContainers.add(new TerritoryCardContainer());
		cardContainers.add(new BuildingCardContainer());
		cardContainers.add(new VentureCardContainer());
		cardContainers.add(new CharacterCardContainer());
		
		resources.add(new Coins(0,1));
		resources.add(new Wood(0,1));
		resources.add(new Stones(0,1));
		resources.add(new Servants(0,1));
		resources.add(new FaithPoints(0,1));
		resources.add(new MilitaryPoints(0,1));
		resources.add(new VictoryPoints(0,1));
	}
	

	public PersonalBonusTile getPersonalBonusTile() {
		return personalBonusTile;
	}
	
	public ArrayList<CardContainer> getCardContainers() {
		return cardContainers;
	}
	
	public ArrayList<LeaderCard> getActivatedLeaderCards() {
		return activatedLeaderCards;
	}

	public ArrayList<Resource> getResources() {
		return resources;
	}
	
	public Resource getResource(ResourceType resourceType) {
		for(Resource resource : resources){
			if(resource.getResourceType().equals(resourceType)){
				return resource;
			}
		}
		return null;
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
		for(CardContainer cardContainer: cardContainers){
			if(cardContainer.getType().equals(developmentCard.developmentCardType)){
				cardContainer.add(developmentCard);
			}
		}
	}
	
	public void activateDevelopmentCard(DevelopmentCard developmentCard){
		for(int i = 0; i < developmentCard.secondaryEffect.size(); i++){
			//TODO PERMANENT
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


	public String getDescription() {
		String description = "";
		for (Resource resource : resources) {
			description = description + resource.getDescription() + "\n";
		}
		return description;
	}
	
	public CardContainer getCardContainer(DevelopmentCardType developmentCardType){
		for(CardContainer cardContainer: cardContainers){
			if(cardContainer.getType().equals(developmentCardType)){
				return cardContainer;
			}
		}
		return null;
	}
	
}//TODO metodo per dare il permanent bonus della carta che riceve
