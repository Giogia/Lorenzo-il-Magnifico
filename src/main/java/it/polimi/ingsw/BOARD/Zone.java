package it.polimi.ingsw.BOARD;

public abstract class Zone{
	private Position[] positions;
	
	
	public Position getPosition(int position) {
		return this.positions[position];
	}
	
	public void setPosition(Position[] position) {
		this.positions = position;
	}
	
	public void deleteAllFamilyMember(){
		for(Position position: this.positions) {
			position.deleteAllFamilyMember();
		}
	}
	
	public Position[] getPositions(){
		return positions;
	}
}
