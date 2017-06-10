package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.GC_15.Game;

public class HarvestArea extends ActionZone {
	
	public HarvestArea(Board board){
		this();
		setBoard(board);
		int numberOfPlayers = board.getGame().getPlayers().length;
		int numberOfPositions = (numberOfPlayers>=3? 2 : 1);
		positions = new Position[numberOfPositions];
				
		//create HarvestArea's positions
		for(int i=0;i<numberOfPositions;i++){
			positions[i]= new Position(null, 1);
		}
	}
	
	public HarvestArea() {
		super("harvestArea");
		/*int numberOfPlayers = getGame().getPlayers().length;
		int numberOfPositions = (numberOfPlayers>=3? 2 : 1);
		positions = new Position[numberOfPositions];
				
		//create HarvestArea's positions
		for(int i=0;i<numberOfPositions;i++){
			positions[i]= new Position(null, 1);
		}*/
	}
	
	@Override
	public String getDescription() {
		return "Harvest Area";
	}
}
