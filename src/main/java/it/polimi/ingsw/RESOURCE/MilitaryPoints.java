package it.polimi.ingsw.RESOURCE;

public class MilitaryPoints extends Resource {

	public MilitaryPoints(int amount, int value) {
		super(amount, value);
		this.resourceType = ResourceType.militaryPoints;
	}
	

	@Override
	public String getDescription() {
		return amount +" Military Points";
	}
	
	@Override
	public Resource createClone() {
		MilitaryPoints militaryPoints = new MilitaryPoints(this.amount, this.value);
		return militaryPoints;
	}
}
