package it.polimi.ingsw.RESOURCE;

public class Coins extends Resource {
	
	public Coins(int amount, int value) {
		super(amount, value);
		this.resourceType = ResourceType.coins;
	}

	@Override
	public String getDescription() {
		return amount +" Coins";
	}
	
	public Resource clone(){
		Coins coins = new Coins(this.amount, this.value);
		return coins;
	}
}
