package it.polimi.ingsw.GC_15;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
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

//model class of a player's personal board
public class PersonalBoard implements Serializable {
	private PersonalBonusTile personalBonusTile;
	private boolean[] excommunicationCubes;
	private ArrayList<CardContainer> cardContainers;
	private ArrayList<Resource> resources;
	private ArrayList<LeaderCard> activatedLeaderCards;
	private ArrayList<LeaderCard> oncePerRoundBonusLeaderCard;
	private ArrayList<PermanentBonus> permanentBonus;
	
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
		//TODO Bisogna aggiungere l'effetto della leader card che si attiva subito
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
	
	
	public ArrayList<PermanentBonus> getPermanentBonus() {
		return permanentBonus;
	}
	
	public void addPermanentBonus(PermanentBonus bonus){
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
		StringBuilder description = new StringBuilder();
		description.append("--Resources:\n");
		for (Resource resource : resources) {
			description.append(resource.getDescription() + "\n");
		}
		description.append("\n--Cards:\n");
		for (CardContainer container : cardContainers) {
			description.append(container.getType().toString().toUpperCase() + ":\n");
			if (container.getDevelopmentCards().isEmpty()){
				description.append("This player doesn't own development card of this type\n");
			}else{
				for (DevelopmentCard card : container.getDevelopmentCards()) {
					description.append(card.getDescription());
				}
			}
		}
		return description.toString();
	}
	
	//return the right card container when development card type is given
	public CardContainer getCardContainer(DevelopmentCardType developmentCardType){
		for(CardContainer cardContainer: cardContainers){
			if(cardContainer.getType().equals(developmentCardType)){
				return cardContainer;
			}
		}
		return null;
	}
}
