package it.polimi.ingsw.RESOURCE;

public class Coins extends Resource {
	
	public Coins(int amount, int value) {
		super(amount, value);
		this.resourceType = ResourceType.COINS;
	}
}
