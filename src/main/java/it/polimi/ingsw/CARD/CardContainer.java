package it.polimi.ingsw.CARD;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class CardContainer implements Serializable{
	protected DevelopmentCardType type;
	private ArrayList<DevelopmentCard> developmentCards;
	
	public CardContainer() {
		type = null;
		developmentCards = new ArrayList<>();
	}
	
	public ArrayList<DevelopmentCard> getDevelopmentCards() {
		return developmentCards;
	}
	public DevelopmentCardType getType() {
		return type;
	}
	
	public void add(DevelopmentCard developmentCard){
		developmentCards.add(developmentCard);
	}
	
	public DevelopmentCard getDevelopmentCard(String name){
		for(DevelopmentCard developmentCard: developmentCards){
			if(developmentCard.getName().equals(name)){
				return developmentCard;
			}
		}
		return null;
	}
}
