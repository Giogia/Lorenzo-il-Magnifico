package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.RESOURCE.Resource;

public class PermanentAddResourceBonus extends PermanentResourceBonus {

	public PermanentAddResourceBonus(ArrayList<Resource> resources) {
		super("PermanentAddResourceBonus", resources);
	}

	@Override
	public PermanentAddResourceBonus createClone() {
		return new PermanentAddResourceBonus(this.resources);
	}
}
