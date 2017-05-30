package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.RESOURCE.Resource;

public class PermanentLeaderCard extends LeaderCard{
	
	public PermanentLeaderCard(String name, ArrayList<Bonus> permanentBonus, ArrayList<Resource> resourceActivationCondition, ArrayList<DevelopmentCard> cardActivationCondition) {
		super(name, resourceActivationCondition, cardActivationCondition, permanentBonus);
	}
	
	public void activatePermanentBonus(){
		//TODO PERMANENT
	}
}
