package it.polimi.ingsw.minigame;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class PersonalBoardProxy implements Serializable {

	private PersonalBonusTileProxy personalBonusTileProxy;
	private ArrayList<CardContainerProxy> cardContainerProxies = new ArrayList<>();
	private ArrayList<Resource> resources = new ArrayList<>();
	private ArrayList<LeaderCardProxy> leaderCardProxies = new ArrayList<>();
	
	public PersonalBoardProxy(PersonalBoard personalBoard) {
		personalBonusTileProxy = new PersonalBonusTileProxy(personalBoard.getPersonalBonusTile());
		for(CardContainer cardContainer : personalBoard.getCardContainers()){
			cardContainerProxies.add(new CardContainerProxy(cardContainer));
		}
		resources = personalBoard.getResources();
		for(LeaderCard leaderCard : personalBoard.getActivatedLeaderCards()){
			leaderCardProxies.add(new LeaderCardProxy(leaderCard));
		}
	}
	
	public PersonalBonusTileProxy getPersonalBonusTileProxy() {
		return personalBonusTileProxy;
	}
	
	public ArrayList<CardContainerProxy> getCardContainers() {
		return cardContainerProxies;
	}
	
	public ArrayList<Resource> getResources() {
		return resources;
	}
	
	public CardContainerProxy getCardContainer(DevelopmentCardType developmentCardType){
		for(CardContainerProxy cardContainerProxy: cardContainerProxies){
			if(cardContainerProxy.getDevelopmentCardType().equals(developmentCardType)){
				return cardContainerProxy;
			}
		}
		return null;
	}
	
	public Resource getResource(ResourceType resourceType) {
		for(Resource resource : resources){
			if(resource.getResourceType().equals(resourceType)){
				return resource;
			}
		}
		return null;
	}
	
	public ArrayList<LeaderCardProxy> getLeaderCardProxies() {
		return leaderCardProxies;
	}
	
	
	public void setPersonalBonusTileProxy(PersonalBonusTileProxy personalBonusTileProxy) {
		this.personalBonusTileProxy = personalBonusTileProxy;
	}
}
