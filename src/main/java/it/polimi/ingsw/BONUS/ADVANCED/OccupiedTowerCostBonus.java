package it.polimi.ingsw.BONUS.ADVANCED;

public class OccupiedTowerCostBonus extends PermanentBonus {
	private int addOccupiedCost;
	private int multOccupiedCost;
	
	public OccupiedTowerCostBonus(int addOccupiedCost, int multOccupiedCost){
		super("OccupiedTowerCostBonus");
		this.addOccupiedCost = addOccupiedCost;
		this.multOccupiedCost = multOccupiedCost;
	}
	
	public int getAddOccupiedCost() {
		return addOccupiedCost;
	}
	
	public int getMultOccupiedCost() {
		return multOccupiedCost;
	}
	
}
