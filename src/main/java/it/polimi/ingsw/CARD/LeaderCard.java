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
	
	public String getDescription(){
		String description = this.getName() +" \n Activation conditions: \n";
		for(Resource resource : resourceActivationCondition){
			description = description + resource.getDescription();
		}
		for(DevelopmentCardType developmentCardType : cardActivationCondition.keySet()){
			description = description + cardActivationCondition.get(developmentCardType) +" "+developmentCardType +" \n";
		}
		description = description +"Bonus: \n";
		for(Bonus bonus : bonus){
			if(this instanceof OncePerRoundLeaderCard){
				description = description +"Every round you get ";
			}
			description = description + bonus.getDescription();
		}
		if(numberOfSameTypeCards!=0)
			description = description +"you need to have "+ numberOfSameTypeCards +"cards of the same color to activate this card \n"; 
		return description;
	}
}
