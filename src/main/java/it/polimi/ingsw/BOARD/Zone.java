package it.polimi.ingsw.BOARD;

public abstract class Zone{
	private Position[] position;
	
	
	public Position getPosition(int position) {
		return this.position[position];
	}
	
}
