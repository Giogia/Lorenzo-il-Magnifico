package it.polimi.ingsw.CARD;

import java.io.Serializable;

//abstract class for all the cards in the game
public abstract class Card implements Serializable{
	private String name;
	
	public Card(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
}
