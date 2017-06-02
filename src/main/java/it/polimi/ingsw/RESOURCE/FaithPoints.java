package it.polimi.ingsw.RESOURCE;

public class FaithPoints extends Resource{

	public FaithPoints(int amount, int value) {
		super(amount, value);
		this.resourceType = ResourceType.faithPoints;
	}

	@Override
	public String getDescription() {
		return amount +" Faith Points";
	}
	
	@Override
	public Resource clone() {
		FaithPoints faithPoints = new FaithPoints(this.amount, this.value);
		return faithPoints;
	}
	
}
