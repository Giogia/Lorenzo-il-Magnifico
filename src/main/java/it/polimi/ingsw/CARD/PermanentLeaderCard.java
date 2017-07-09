package it.polimi.ingsw.CARD;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.RESOURCE.Resource;

//class for the leader cards whose effects are permanent
public class PermanentLeaderCard extends LeaderCard{
	
	public PermanentLeaderCard(String name, ArrayList<Resource> resourceActivationCondition, HashMap<DevelopmentCardType, Integer> cardActivationCondition, ArrayList<Bonus> bonus, int numberOfSameTypeCards) {
		super(name, resourceActivationCondition, cardActivationCondition, bonus, numberOfSameTypeCards, "PermanentLeaderCard");
	}
	
	public void activatePermanentBonus(){
		//TODO PERMANENT
	}
}
