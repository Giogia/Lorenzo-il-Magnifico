package it.polimi.ingsw.GC_15;

import it.polimi.ingsw.BONUS.Bonus;

public class PersonalBonusTile {
	public final Bonus harvestBonus;
	public final Bonus productionBonus;
	public final int harvestActivationCondition;
	public final int productionActivationCondition;
	
	public PersonalBonusTile(Bonus harvestBonus, Bonus productionBonus, int harvestActivationCondition,int productionActivationCondition) {
		this.harvestBonus = harvestBonus;
		this.productionBonus = productionBonus;
		this.harvestActivationCondition = harvestActivationCondition;
		this.productionActivationCondition = productionActivationCondition;
	}
	}
