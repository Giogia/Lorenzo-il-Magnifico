package it.polimi.ingsw.RESOURCE;

public class VictoryPoints extends Resource{

	public VictoryPoints(int amount, int value) {
		super(amount, value);
		this.resourceType = ResourceType.victoryPoints;
	}
	
	@Override
	public String getDescription() {
		return amount +" Victory Points";
	}
	
	@Override
	public Resource createClone() {
		VictoryPoints victoryPoints = new VictoryPoints(this.amount, this.value);
		return victoryPoints;
	}
}

