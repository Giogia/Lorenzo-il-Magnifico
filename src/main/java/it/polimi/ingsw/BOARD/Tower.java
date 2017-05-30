package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.CARD.DevelopmentCardType;

public class Tower extends ActionZone{
	private DevelopmentCardType developmentCardType;
	
	public Tower(DevelopmentCardType developmentCardType) { 
		positions = new TowerFloor[4];
		this.developmentCardType = developmentCardType;
	}
	
	public DevelopmentCardType getDevelopmentCardType(){
		return this.developmentCardType;
	}
	
	public void setDevelopmentCardType(DevelopmentCardType developmentCardType){
		this.developmentCardType = developmentCardType;
	}
	
	@Override
	public TowerFloor[] getPositions() {
		return (TowerFloor[]) super.getPositions();
	}
}
