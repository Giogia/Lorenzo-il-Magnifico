package it.polimi.ingsw.BOARD;

public class Market extends Zone{

	public Market(int numberOfPlayers) {
		super("market");
		if(numberOfPlayers>=4){
			positions = new Position[4];
		}
		positions = new Position[2];
	}
	
	@Override
	public String getDescription() {
		String description = "Mercato";
		return description;
	}
}
