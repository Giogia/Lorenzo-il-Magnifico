package it.polimi.ingsw.minigame;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;

public class CardContainerProxy implements Serializable{

	protected DevelopmentCardType developmentCardType;
	private ArrayList<DevelopmentCardProxy> developmentCardProxies = new ArrayList<>();
	
	public CardContainerProxy(CardContainer cardContainer) {
		for(int i = 0; i < 6; i++){ //for all type of cards
			developmentCardProxies.add(new DevelopmentCardProxy(null));
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
