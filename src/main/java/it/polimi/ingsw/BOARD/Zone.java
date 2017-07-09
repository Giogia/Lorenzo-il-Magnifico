package it.polimi.ingsw.BOARD;

import java.io.Serializable;

import it.polimi.ingsw.GC_15.Game;

//model abstract class for every zone of the game board
public abstract class Zone implements Serializable{
	private Board board;
	protected Position[] positions;
	private String type;
	
	
	public Zone(String type){
		this.type = type;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public String getType() {
		return type;
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
