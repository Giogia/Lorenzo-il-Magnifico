package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.RESOURCE.Resource;

public abstract class LeaderCard {
	private ArrayList<Resource> resourceActivationCondition;
	private ArrayList<DevelopmentCard> cardActivationCondition;
	
	public LeaderCard(ArrayList<Resource> resourceActivationCondition, ArrayList<DevelopmentCard> cardActivationCondition) {
		this.resourceActivationCondition=resourceActivationCondition;
		this.cardActivationCondition=cardActivationCondition;
	}
	
	public ArrayList<DevelopmentCard> getCardActivationCondition() {
		return cardActivationCondition;
	}
	
	public ArrayList<Resource> getResourceActivationCondition() {
		return resourceActivationCondition;
	}
	
	public boolean checkActivationCondition(){
		//TODO
		return true;
	}
}
