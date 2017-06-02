package it.polimi.ingsw.GC_15;

import it.polimi.ingsw.BOARD.HarvestArea;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.ImmediateBonus;

public class PersonalBonusTile {
	private final ImmediateBonus harvestBonus;
	private final ImmediateBonus productionBonus;
	private final int harvestActivationCondition;
	private final int productionActivationCondition;
	
	public PersonalBonusTile() {
		harvestBonus = null;
		productionBonus = null;
		harvestActivationCondition = 0;
		productionActivationCondition = 0;
	}
	
	public PersonalBonusTile(ImmediateBonus harvestBonus, ImmediateBonus productionBonus, int harvestActivationCondition, int productionActivationCondition){
		this.harvestBonus= harvestBonus;
		this.productionBonus= productionBonus;
		this.harvestActivationCondition= harvestActivationCondition;
		this.productionActivationCondition=productionActivationCondition;
	}
	
	
	private int getHarvestActivationCondition() {
		return harvestActivationCondition;
	}
	
	private int getProductionActivationCondition() {
		return productionActivationCondition;
	}
	
	public int getCondition(Zone zone){
		if(zone instanceof HarvestArea){
			return getHarvestActivationCondition();
		}
		if(zone instanceof ProductionArea){
			return getProductionActivationCondition();
		}
		else
			return -1;//TODO dobbiamo gestire l'eccezione ;
	}
	
	public ImmediateBonus getImmediateBonus(Zone zone){
		if(zone instanceof HarvestArea){
			return harvestBonus;
		}
		if(zone instanceof ProductionArea){
			return productionBonus;
		}
		else
			return null;
	};
	
}