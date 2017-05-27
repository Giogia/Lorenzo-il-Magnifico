package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.CardType;
import it.polimi.ingsw.RESOURCE.Resource;

public abstract class CardCostBonus extends PermanentBonus {
	protected CardType cardType;
	protected ArrayList<Resource> resources;
	
	public CardCostBonus(CardType cardType, ArrayList<Resource> resources){
		super("CardCostBonus");
		this.cardType = cardType;
		this.resources = new ArrayList<>();
		this.resources.addAll(resources);
	}

	public CardType getCardType() {
		return cardType;
	}
	
	public ArrayList<Resource> getResources() {
		return resources;
	}
	
}
