package it.polimi.ingsw.BOARD;

import java.io.Serializable;

import it.polimi.ingsw.GC_15.Game;

public abstract class Zone implements Serializable{
	private static Game game;
	protected Position[] positions;
	private String type;
	
	
	public Zone(String type){
		this.type = type;
	}
	
	public static void setGame(Game g) {
		game = g;
	}
	
	public static Game getGame() {
		return game;
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
