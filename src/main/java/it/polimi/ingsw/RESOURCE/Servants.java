package it.polimi.ingsw.RESOURCE;

public class Servants extends Resource{

	public Servants(int amount, int value) {
		super(amount, value);
		this.resourceType = ResourceType.SERVANTS;
	}
}
