package it.polimi.ingsw.CARD;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.RESOURCE.Resource;

public class OncePerRoundLeaderCard extends LeaderCard{

	public OncePerRoundLeaderCard(String name, ArrayList<Bonus> oncePerRoundBonus, ArrayList<Resource> resourceActivationCondition, HashMap<DevelopmentCardType, Integer> cardActivationCondition, int number) {
		super(name, resourceActivationCondition, cardActivationCondition, oncePerRoundBonus, number, "OncePerRoundLeaderCard");
	}
	
	public void activateOncePerRoundBonus(){
		//TODO PERMANENT
	}
}
