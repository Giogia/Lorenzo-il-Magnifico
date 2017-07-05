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
		super(name, period, DevelopmentCardType.building, immediateEffect, secondaryEffect);
		activationConditionProduction=activationCondition;
		this.costs=costs;
	}
	
	public int getActivationConditionProduction() {
		return activationConditionProduction;
	}
	
	public ArrayList<Resource> getCosts() {
		return costs;
	}
	
	@Override
	public String getDescription() {
		StringBuilder description = new StringBuilder();
		description.append("Cost: \n");
		for (Resource resource : costs) {
			description.append(resource.getDescription() + "\n");
		}
		description.append(super.getDescription());
		description.append("Production activation condition: " + activationConditionProduction + "\n");
		return description.toString();
	}
}
