package it.polimi.ingsw.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;

public class Tower extends ActionZone{
	private DevelopmentCardType developmentCardType;
	
	public Tower(DevelopmentCardType developmentCardType) {
		super("Tower");
		positions = new TowerFloor[4];
		for (int i = 0; i < positions.length; i++ ){
			//TODO: dare il giusto bonus alla posizione
			positions[i] = new TowerFloor(null, 2*i + 1);
		}
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
	
	@Override
	public String getDescription() {
		String description = "Torre " + developmentCardType.name();
		return description;
	}
}
