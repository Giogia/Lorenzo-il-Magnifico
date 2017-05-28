package it.polimi.ingsw.RESOURCE;

public class MilitaryPoints extends Resource {

	public MilitaryPoints(int amount, int value) {
		super(amount, value);
		this.resourceType = ResourceType.militaryPoints;
	}
}
