package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.GC_15.FamilyMember;

//model class for territory cards
public class Territory extends DevelopmentCard {
	int activationConditionHarvest;
	
	public Territory(String name, int period, int activationCondition, ArrayList<ImmediateBonus> immediateEffect, ArrayList<Bonus> secondaryEffect) {
		super(name, period, DevelopmentCardType.territory, immediateEffect, secondaryEffect);
		activationConditionHarvest=activationCondition;
	}
	
	public int getActivationConditionHarvest() {
		return activationConditionHarvest;
	}
	
	@Override
	public String getDescription() {
		String description = super.getDescription();
		description = description + "Harvest activation condition: " + activationConditionHarvest + "\n";
		return description;
	}
}
