package it.polimi.ingsw.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ImmediateBonus;

public abstract class Zone{
	protected Position[] positions;
	
	
	public Zone(){
	}
	
	public Zone(int numberOfPositions) {
		positions = new Position[numberOfPositions];
		ArrayList<ImmediateBonus> boardBonus = new ArrayList<>();
		for(int i=0;i<numberOfPositions;i++){
			positions[i]= new Position(boardBonus, 0);
		}	
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
}
