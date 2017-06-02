package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.GC_15.Game;

public class ProductionArea extends ActionZone{
	
	public ProductionArea() {
		super("productionArea");
		int numberOfPlayers = Game.getPlayers().length;
		int numberOfPositions = (numberOfPlayers>=3? 2 : 1);
		positions = new Position[numberOfPositions];
		
	//	ArrayList<ArrayList<ImmediateBonus>> boardBonus = Game.getData()
		for(int i = 0; i < numberOfPositions; i++){
			positions[i]= new Position(null, 1);
		}

	}
	
	@Override
	public String getDescription() {
		return "Area Produzione";
	}
}
