package it.polimi.ingsw.GC_15;

import it.polimi.ingsw.BONUS.ImmediateBonus;

public class PersonalBonusTile {
	public final ImmediateBonus harvestBonus;
	public final ImmediateBonus productionBonus;
	public final int harvestActivationCondition;
	public final int productionActivationCondition;
	
	public PersonalBonusTile(ImmediateBonus harvestBonus, ImmediateBonus productionBonus, int harvestActivationCondition,int productionActivationCondition) {
		this.harvestBonus = harvestBonus;
		this.productionBonus = productionBonus;
		this.harvestActivationCondition = harvestActivationCondition;
		this.productionActivationCondition = productionActivationCondition;
	}
	
	
	public int getHarvestActivationCondition() {
		return harvestActivationCondition;
	}
	
	public int getProductionActivationCondition() {
		return productionActivationCondition;
	}
}