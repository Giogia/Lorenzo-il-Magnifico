package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.RESOURCE.Resource;

public class OncePerRoundLeaderCard extends LeaderCard{
	public final ArrayList<Bonus> oncePerRoundBonus;
	
	public OncePerRoundLeaderCard(ArrayList<Bonus> oncePerRoundBonus, ArrayList<Resource> resourceActivationCondition, ArrayList<DevelopmentCard> cardActivationCondition) {
		super(resourceActivationCondition, cardActivationCondition);
		this.oncePerRoundBonus=oncePerRoundBonus;
	}
	
	public void activateOncePerRoundBonus(){
		//TODO
	}
}
