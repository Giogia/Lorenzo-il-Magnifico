package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player;

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
	
	public void setAddOccupiedCost(int addOccupiedCost) {
		this.addOccupiedCost = addOccupiedCost;
	}
	
	public void setMultOccupiedCost(int multOccupiedCost) {
		this.multOccupiedCost = multOccupiedCost;
	}
	
	@Override
	public void getPermanentBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		for (PermanentBonus permanentBonus : playerBonus) {
			if (permanentBonus instanceof OccupiedTowerCostBonus){
				int addOccupiedCost = ((OccupiedTowerCostBonus) permanentBonus).getAddOccupiedCost();
				int multOccupiedCost = ((OccupiedTowerCostBonus) permanentBonus).getMultOccupiedCost();
				addOccupiedCost = addOccupiedCost + this.addOccupiedCost;
				multOccupiedCost = multOccupiedCost * this.multOccupiedCost;
				((OccupiedTowerCostBonus) permanentBonus).setAddOccupiedCost(addOccupiedCost);
				((OccupiedTowerCostBonus) permanentBonus).setMultOccupiedCost(multOccupiedCost);
				return;
			}
		}
		super.getPermanentBonus(player);
	}
	
	public String getDescription(){
		String description = "Everytime a tower is occupied you have to pay : \n";
		if(addOccupiedCost!=0)
			description = description + addOccupiedCost +"more coins \n";
		if(multOccupiedCost!=1)
			description = description + multOccupiedCost +"times the coins \n";
		return description;
	}
}
