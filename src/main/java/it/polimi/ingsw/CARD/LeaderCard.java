package it.polimi.ingsw.CARD;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.RESOURCE.Resource;

public abstract class LeaderCard extends Card {
	
	public final ArrayList<Resource> resourceActivationCondition;
	public final HashMap<DevelopmentCardType, Integer> cardActivationCondition;
	public final ArrayList<Bonus> bonus;
	public final int numberOfSameTypeCards;
	private String type;
	
	public LeaderCard(String name, ArrayList<Resource> resourceActivationCondition, HashMap<DevelopmentCardType, Integer> cardActivationCondition, ArrayList<Bonus> bonus, int numberOfSameTypeCards, String type) {
		super(name);
		this.resourceActivationCondition=resourceActivationCondition;
		this.cardActivationCondition=cardActivationCondition;
		this.bonus=bonus;
		this.numberOfSameTypeCards = numberOfSameTypeCards;
		this.type = type;
	}
	
	public HashMap<DevelopmentCardType, Integer> getCardActivationCondition() {
		return cardActivationCondition;
	}
	
	public ArrayList<Resource> getResourceActivationCondition() {
		return resourceActivationCondition;
	}
	
	public ArrayList<Bonus> getBonus() {
		return bonus;
	}
	
	public int getNumberOfSameTypeCards() {
		return numberOfSameTypeCards;
	}
}
