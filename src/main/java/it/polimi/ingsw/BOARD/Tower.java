package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.CARD.DevelopmentCardType;

public class Tower extends ActionZone{
	private int occupiedCost;
	private boolean occupiedYet;
	private DevelopmentCardType developmentCardType;
	
	public Tower(int occupiedCost, boolean occupiedYet, DevelopmentCardType developmentCardType) { 
		//TODO MANCA IL COSTRUTTORE DI ACTION ZONE
		this.occupiedCost = occupiedCost;
		this.occupiedYet = occupiedYet;
		this.developmentCardType = developmentCardType;
	}
	
	public int getOccupiedCost(){
		return this.occupiedCost;
	}
	
	public boolean getOccupiedYet(){
		return this.occupiedYet;
	}
	
	public DevelopmentCardType getDevelopmentCardType(){
		return this.developmentCardType;
	}
	
	public void setOccupiedCost(int occupiedCost){
		this.occupiedCost = occupiedCost;
	}
	
	public void setOccupiedYet(boolean occupiedYet){
		this.occupiedYet = occupiedYet;
	}
	
	public void setDevelopmentCardType(DevelopmentCardType developmentCardType){
		this.developmentCardType = developmentCardType;
	}
	
	@Override
	public TowerFloor[] getPositions() {
		return (TowerFloor[]) super.getPositions();
	}
}
