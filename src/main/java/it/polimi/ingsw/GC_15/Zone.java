package it.polimi.ingsw.GC_15;

public abstract class Zone{
	private Position[] position;
	
	
	public Position getPosition(int position) {
		return this.position[position];
	}
	
}
