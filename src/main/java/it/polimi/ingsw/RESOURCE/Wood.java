package it.polimi.ingsw.RESOURCE;

public class Wood extends Resource{
	
	public Wood(int amount, int value) {
		super(amount, value);
		this.resourceType = ResourceType.wood;
	}
	
	@Override
	public String getDescription() {
		return amount +" Wood";
	}
}

