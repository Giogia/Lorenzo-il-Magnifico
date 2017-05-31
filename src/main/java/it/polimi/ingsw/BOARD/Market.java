package it.polimi.ingsw.BOARD;

public class Market extends Zone{

	public Market(int numberOfPlayers) {
			super(numberOfPlayers>=4? 4 : 2);
	}
}
