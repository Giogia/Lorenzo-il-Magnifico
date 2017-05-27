package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.RESOURCE.Resource;

public class Building extends DevelopmentCard{
	public final int activationConditionProduction;
	public final ArrayList<Resource> costs;
	
	public Building(String name, int activationCondition, ArrayList<Resource> costs, int period, ArrayList<ImmediateBonus> immediateEffect, ArrayList<Bonus> secondaryEffect) {
		super(name, period, DevelopmentCardType.BUILDING, immediateEffect, secondaryEffect);
		activationConditionProduction=activationCondition;
		this.costs=costs;
	}
	
	public void getProductionBonus(FamilyMember familyMember){
		for(int i=0; i < secondaryEffect.size(); i++){
			//TODO
		}
	}
}
