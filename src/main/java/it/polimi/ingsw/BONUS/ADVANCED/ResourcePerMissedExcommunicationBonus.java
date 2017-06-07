package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.RESOURCE.Resource;

public class ResourcePerMissedExcommunicationBonus extends PermanentResourceBonus {

	public ResourcePerMissedExcommunicationBonus(ArrayList<Resource> resources) {
		super("ResourcePerMissedExcommunicationBonus", resources);
	}

	@Override
	public ResourcePerMissedExcommunicationBonus createClone() {
		return new ResourcePerMissedExcommunicationBonus(this.resources);
	}

}
