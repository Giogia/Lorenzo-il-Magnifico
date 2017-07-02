package it.polimi.ingsw.minigame;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;

public class CardContainerProxy implements Serializable{

	protected DevelopmentCardType developmentCardType;
	private ArrayList<DevelopmentCardProxy> developmentCardProxies;
	
	public CardContainerProxy(CardContainer cardContainer) {
		for(DevelopmentCard developmentCard : cardContainer.getDevelopmentCards()){
			developmentCardProxies.add(new DevelopmentCardProxy());		
		}
		developmentCardType = cardContainer.getType();
	}
	
	public DevelopmentCardType getDevelopmentCardType() {
		return developmentCardType;
	}
	
	public ArrayList<DevelopmentCardProxy> getDevelopmentCardProxies() {
		return developmentCardProxies;
	}
}
