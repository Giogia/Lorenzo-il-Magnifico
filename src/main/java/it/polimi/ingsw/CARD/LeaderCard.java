package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.RESOURCE.Resource;

public abstract class LeaderCard {
	private ArrayList<Resource> resourceActivationCondition;
	private ArrayList<DevelopmentCard> cardActivationCondition;
	public final ArrayList<Bonus> bonus;
	
	public LeaderCard(ArrayList<Resource> resourceActivationCondition, ArrayList<DevelopmentCard> cardActivationCondition, ArrayList<Bonus> bonus) {
		this.resourceActivationCondition=resourceActivationCondition;
		this.cardActivationCondition=cardActivationCondition;
		this.bonus=bonus;
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
