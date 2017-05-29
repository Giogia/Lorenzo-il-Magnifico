package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;

public class Venture extends DevelopmentCard{
	public final int militaryPointRequirement; //if = 0 this requirement doesn't exists 
	public final ArrayList<Resource> cost;
	public final MilitaryPoints alternativeCost; //IT'S ONLY MILITARYPOINTS
	
	public Venture(String name,int requirement, ArrayList<Resource> cost, MilitaryPoints alternativeCost, int period, ArrayList<ImmediateBonus> immediateEffect, ArrayList<Bonus> secondaryEffect) {
		super(name, period, DevelopmentCardType.venture, immediateEffect, secondaryEffect);
		militaryPointRequirement=requirement;
		this.cost=cost;
		this.alternativeCost=alternativeCost;
	}
}
