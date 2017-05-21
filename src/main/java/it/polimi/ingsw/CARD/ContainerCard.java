package it.polimi.ingsw.CARD;

import java.util.ArrayList;

public abstract class ContainerCard {
	public DevelopmentCardType type;
	private ArrayList<DevelopmentCard> developmentCards;
	
	
	public ArrayList<DevelopmentCard> getDevelopmentCards() {
		return developmentCards;
	}
	public DevelopmentCardType getType() {
		return type;
	}
	
	public void add(DevelopmentCard developmentCard){
		developmentCards.add(developmentCard);
	}
}
