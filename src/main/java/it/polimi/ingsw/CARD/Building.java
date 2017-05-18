package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.RESOURCE.Resource;

public class Building extends DevelopmentCard{
	public final int activationCondition;
	public final ArrayList<Resource> cost;
	public final DevelopmentCardType type= DevelopmentCardType.BUILDING;
	
	public Building(int activationCondition, ArrayList<Resource> cost, int period, ArrayList<ImmediateBonus> immediateEffect, ArrayList<Bonus> secondaryEffect) {
		super(period, immediateEffect, secondaryEffect);
		this.activationCondition=activationCondition;
		this.cost=cost;
	}
	
	public void getProductionBonus(FamilyMember familyMember){
		for(int i=0; i < secondaryEffect.size(); i++){
			secondaryEffect.get(i)
		}
	}
}
