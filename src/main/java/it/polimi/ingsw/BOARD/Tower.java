package it.polimi.ingsw.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;

public class Tower extends ActionZone{
	private DevelopmentCardType developmentCardType;
	
	public Tower(DevelopmentCardType developmentCardType) { 
		positions = new TowerFloor[4];
		this.developmentCardType = developmentCardType;
		
		ArrayList<ImmediateBonus> boardBonus = new ArrayList<>(); 
		for(int i=0;i<4;i++){
			positions[i]= new TowerFloor(boardBonus, 0);
		}
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
