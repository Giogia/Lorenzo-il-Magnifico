package it.polimi.ingsw.BOARD;

public class HarvestArea extends ActionZone {
	
	
	public HarvestArea(){
	}

	public HarvestArea(int numberOfPlayers) {
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
