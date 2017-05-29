package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.CARD.DevelopmentCardType;

public class Tower extends ActionZone{
	private int occupiedCost;
	private boolean occupiedYet;
	private DevelopmentCardType developmentCardType;
	
	public Tower(DevelopmentCardType developmentCardType) { 
		positions = new TowerFloor[4];
		occupiedCost = 0;
		occupiedYet = false;
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
		// TODO Auto-generated method stub
		return (TowerFloor[]) super.getPositions();
	}
}
