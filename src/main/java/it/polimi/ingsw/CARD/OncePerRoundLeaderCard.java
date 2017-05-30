package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.RESOURCE.Resource;

public class OncePerRoundLeaderCard extends LeaderCard{

	public OncePerRoundLeaderCard(String name, ArrayList<Bonus> oncePerRoundBonus, ArrayList<Resource> resourceActivationCondition, ArrayList<DevelopmentCard> cardActivationCondition) {
		super(name, resourceActivationCondition, cardActivationCondition, oncePerRoundBonus);
	}
	
	public void activateOncePerRoundBonus(){
		//TODO PERMANENT
	}
}
