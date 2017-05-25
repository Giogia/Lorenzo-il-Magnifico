package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.RESOURCE.Resource;

public class Venture extends DevelopmentCard{
	public final ArrayList<Resource> cost;
	public final ArrayList<Resource> alternativeCost;
	
	public Venture(String name, ArrayList<Resource> cost, ArrayList<Resource> alternativeCost, int period, ArrayList<ImmediateBonus> immediateEffect, ArrayList<Bonus> secondaryEffect) {
		super(name, period, DevelopmentCardType.VENTURE, immediateEffect, secondaryEffect);
		this.cost=cost;
		this.alternativeCost=alternativeCost;
	}
}
