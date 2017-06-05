package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.PermanentResourceBonus;
import it.polimi.ingsw.RESOURCE.Resource;

public class ResourceValueBonus extends PermanentResourceBonus{

	public ResourceValueBonus(ArrayList<Resource> resources) {
		super("ResourceValueBonus", resources);
	}

}
