package it.polimi.ingsw.BOARD;

public abstract class ActionZone extends Zone{
	String type;
	
	public ActionZone(){
	}
	
	public ActionZone(int numberOfPositions) {
		super(numberOfPositions);
	}
}
