package it.polimi.ingsw.BOARD;

public class ProductionArea extends ActionZone{
	
	public ProductionArea(){
	}	
	
	public ProductionArea(int numberOfPlayers) {
		if(numberOfPlayers>=3){
			positions = new Position[4];
		}
		positions = new Position[2];
	}
	
	@Override
	public String getDescription() {
		String description = "Area Produzione";
		return description;
	}
}
