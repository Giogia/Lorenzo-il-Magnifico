package it.polimi.ingsw.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Game;

public class Tower extends ActionZone{
	private DevelopmentCardType developmentCardType;
	
	public Tower(DevelopmentCardType developmentCardType) {
		super("Tower");
		positions = new TowerFloor[4];
		for (int i = 0; i < positions.length; i++ ){
			ArrayList<ArrayList<ImmediateBonus>> boardBonus=Game.getData().getTowerPositionBonus(developmentCardType);
			positions[i] = new TowerFloor(boardBonus.get(i), 2*i + 1);
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
	//PAY ATTENTION: this method returns an array of Position!
	public Position[] getPositions() {
		return  positions;
	}
	
	@Override
	public String getDescription() {
		String description = "Torre " + developmentCardType.name();
		return description;
	}
}
