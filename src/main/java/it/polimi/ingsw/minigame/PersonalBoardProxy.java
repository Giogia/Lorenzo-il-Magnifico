package it.polimi.ingsw.minigame;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class PersonalBoardProxy {

	private PersonalBonusTileProxy personalBonusTileProxy;
	private ArrayList<CardContainer> cardContainers = new ArrayList<>();
	private ArrayList<Resource> resources = new ArrayList<>();
	private ArrayList<LeaderCardProxy> leaderCardProxies = new ArrayList<>();
	private ArrayList<LeaderCardProxy> leaderCardInHandProxies = new ArrayList<>();
	
	public PersonalBonusTileProxy getPersonalBonusTileProxy() {
		return personalBonusTileProxy;
	}
	
	public ArrayList<CardContainer> getCardContainers() {
		return cardContainers;
	}
	
	public ArrayList<Resource> getResources() {
		return resources;
	}
	
	public CardContainer getCardContainer(DevelopmentCardType developmentCardType){
		for(CardContainer cardContainer: cardContainers){
			if(cardContainer.getType().equals(developmentCardType)){
				return cardContainer;
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
	
	public ArrayList<LeaderCardProxy> getLeaderCardInHandProxies() {
		return leaderCardInHandProxies;
	}
	
	public void setPersonalBonusTileProxy(PersonalBonusTileProxy personalBonusTileProxy) {
		this.personalBonusTileProxy = personalBonusTileProxy;
	}
}
