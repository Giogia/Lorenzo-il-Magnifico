package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.PermanentResourceBonus;
import it.polimi.ingsw.RESOURCE.Resource;

public class ResourceValueBonus extends PermanentResourceBonus{

	public ResourceValueBonus(ArrayList<Resource> resources) {
		super("ResourceValueBonus", resources);
	}

	
	public String getDescription() {
		String description = "";
		for (Resource resource : resources) {
			description = description + "For each " + resource.getClass().getName() + " you have to pay " + resource.getAmount() + "\n";
		}
		return description;
	}
	
	@Override
	public ResourceValueBonus createClone() {
		return new ResourceValueBonus(this.resources);
	}

}
