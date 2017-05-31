package it.polimi.ingsw.BOARD;

public class ProductionArea extends ActionZone{
	
	public ProductionArea(){
	}	
	
	public ProductionArea(int numberOfPlayers) {
		super(numberOfPlayers>=3? 2 : 1);
	}
}
