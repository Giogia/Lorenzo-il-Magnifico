package it.polimi.ingsw.RESOURCE;

public class Stones extends Resource{

	public Stones(int amount, int value) {
		super(amount, value);
		this.resourceType = ResourceType.stones;
	}
	
	@Override
	public String getDescription() {
		return amount +" Stones";
	}
	
	@Override
	public Resource createClone() {
		Stones stones = new Stones(this.amount, this.value);
		return stones;
	}
}
