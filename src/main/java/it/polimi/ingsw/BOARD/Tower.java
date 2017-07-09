package it.polimi.ingsw.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Game;

//model class of a single tower
public class Tower extends ActionZone{
	private DevelopmentCardType developmentCardType;
	
	public Tower(DevelopmentCardType developmentCardType, Board board) {
		this(developmentCardType);
		setBoard(board);
		positions = new TowerFloor[4];
		for (int i = 0; i < positions.length; i++ ){
			ArrayList<ArrayList<ImmediateBonus>> boardBonus= board.getGame().getData().getTowerPositionBonus(developmentCardType);
			positions[i] = new TowerFloor(boardBonus.get(i), 2*i + 1);
		}
		this.developmentCardType = developmentCardType;
	}
	
	public Tower(DevelopmentCardType developmentCardType) {
		super("Tower");
		this.developmentCardType = developmentCardType;
	}
	
	public DevelopmentCardType getDevelopmentCardType(){
		return this.developmentCardType;
	}
	
	public void setDevelopmentCardType(DevelopmentCardType developmentCardType){
		this.developmentCardType = developmentCardType;
	}
	
	@Override
	//PAY ATTENTION: this method returns an array of Positions and not TowerFloors!!
	public Position[] getPositions() {
		return  positions;
	}
	
	@Override
	public String getDescription() {
		String description = "Tower " + developmentCardType.name();
		return description;
	}
}
