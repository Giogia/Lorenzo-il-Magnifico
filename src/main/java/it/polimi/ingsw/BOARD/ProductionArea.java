package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.GC_15.Game;

//model class of the production area
public class ProductionArea extends ActionZone{
	
	public ProductionArea(Board board){
		this();
		setBoard(board);
		int numberOfPlayers = board.getGame().getPlayers().length;
		int numberOfPositions = (numberOfPlayers>=3? 2 : 1);
		positions = new Position[numberOfPositions];
		
		for(int i = 0; i < numberOfPositions; i++){
			positions[i]= new Position(null, 1);
		}
	}
	public ProductionArea() {
		super("productionArea");
		/*int numberOfPlayers = getGame().getPlayers().length;
		int numberOfPositions = (numberOfPlayers>=3? 2 : 1);
		positions = new Position[numberOfPositions];
		
		for(int i = 0; i < numberOfPositions; i++){
			positions[i]= new Position(null, 1);
		}
*/
	}
	
	@Override
	public String getDescription() {
		return "Production Area";
	}
}
