package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.RESOURCE.Resource;

public class PermanentLeaderCard extends LeaderCard{
	public final ArrayList<Bonus> permanentBonus;
	
	public PermanentLeaderCard(ArrayList<Bonus> permanentBonus, ArrayList<Resource> resourceActivationCondition, ArrayList<DevelopmentCard> cardActivationCondition) {
		super(resourceActivationCondition, cardActivationCondition);
		this.permanentBonus=permanentBonus;
	}
	
	public void activatePermanentBonus(){
		//TODO
	}
}
