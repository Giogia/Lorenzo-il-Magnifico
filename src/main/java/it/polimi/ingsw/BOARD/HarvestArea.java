package it.polimi.ingsw.BOARD;

public class HarvestArea extends ActionZone {
	
	
	public HarvestArea(){
		super("harvestArea");
	}

	public HarvestArea(int numberOfPlayers) {
		super("harvestArea");
		if(numberOfPlayers>=3){
			positions = new Position[4];
		}
		positions = new Position[2];
	}
	
	@Override
	public String getDescription() {
		String description = "Area Raccolto";
		return description;
	}
}
