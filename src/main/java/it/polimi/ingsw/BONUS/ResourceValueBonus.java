package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.RESOURCE.Resource;

public class ResourceValueBonus extends ResourceBonus{

	public ResourceValueBonus(ArrayList<Resource> resources) {
		super("ResourceValueBonus" , resources);
	}

	@Override
	protected void modify(Resource resource1, Resource resource2) {
		resource1.setValue(resource2.getValue());
	}
	
	@Override
	public String getDescription() {
		String description = "";
		for (Resource resource : resources) {
			description = description + "Per ogni " + resource.getClass().getName() + " devi pagare " + resource.getAmount() + "\n";
		}
		return description;
	}
}
