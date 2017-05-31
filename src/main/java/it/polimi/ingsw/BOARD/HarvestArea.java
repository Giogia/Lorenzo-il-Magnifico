package it.polimi.ingsw.BOARD;

public class HarvestArea extends ActionZone {
	
	
	public HarvestArea(){
	}

	public HarvestArea(int numberOfPlayers) {
		super(numberOfPlayers>=3? 2 : 1);
	}
}
