package it.polimi.ingsw.CARD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
	
	public String getDescription(){		
		StringBuilder description = new StringBuilder();
		description.append(getName());
		description.append(" \nActivation conditions: \n");
		if(resourceActivationCondition!=null){
			for(Resource resource : resourceActivationCondition){
				description.append(resource.getDescription());
			}
		}
		if(cardActivationCondition!=null){
			for(Entry<DevelopmentCardType, Integer> entry : cardActivationCondition.entrySet()){
				description.append(entry.getValue()+" "+entry.getKey() +" \n");
			}
		}
		if(bonus!=null){
			description.append("Bonus: \n");
			for(Bonus thisBonus : bonus){
				if(this instanceof OncePerRoundLeaderCard){
					description.append("Every round you get ");
				}
				description.append(thisBonus.getDescription());
			}
		}
		if(numberOfSameTypeCards!=0)
			description.append("you need to have "+ numberOfSameTypeCards +"cards of the same color to activate this card \n"); 
		return description.toString();
	}
}
