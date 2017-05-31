package it.polimi.ingsw.BOARD;

public abstract class Zone{
	protected Position[] positions;
	private String type;
	
	
	public Zone(String type){
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public Zone(int numberOfPositions) {
		positions = new Position[numberOfPositions];
	}

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
	
	public abstract String getDescription();
}
