package it.polimi.ingsw.BONUS;

import java.io.Serializable;

//abstract class for every bonus in the game
public abstract class Bonus implements Serializable {
	private String type1;
	
	public Bonus(String type) {
		type1 = type;
	}
	
	public String getType1() {
		return type1;
	}
	
	public abstract String getDescription();
}
