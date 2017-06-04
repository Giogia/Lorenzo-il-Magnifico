package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.GC_15.Game;

public class HarvestArea extends ActionZone {
	
	public HarvestArea() {
		super("harvestArea");
		int numberOfPlayers = Game.getPlayers().length;
		int numberOfPositions = (numberOfPlayers>=3? 2 : 1);
		positions = new Position[numberOfPositions];
		
		/*TODO: create ActionBonus for positions
		ArrayList<ImmediateBonus> boardBonus = new ArrayList<>();
		HashMap<ActionZone, Integer> actionMap = new HashMap<>();
		
		boardBonus.add(new ActionBonus(actionMap, null));*/
		
		//create HarvestArea's positions
		for(int i=0;i<numberOfPositions;i++){
			positions[i]= new Position(null, 1);
		}
	}
	
	@Override
	public String getDescription() {
		return "Harvest Area";
	}
}
