package it.polimi.ingsw.BOARD;

public class Market extends Zone{

	public Market(int numberOfPlayers) {
		if(numberOfPlayers>=4){
			positions = new Position[4];
		}
		positions = new Position[2];
	}
}
