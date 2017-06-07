package it.polimi.ingsw.RESOURCE;

public class Servants extends Resource{

	public Servants(int amount, int value) {
		super(amount, value);
		this.resourceType = ResourceType.servants;
	}
	
	@Override
	public String getDescription() {
		return amount +" Servants";
	}
	
	@Override
	public Resource createClone() {
		Servants servants = new Servants(this.amount, this.value);
		return servants;
	}
}
