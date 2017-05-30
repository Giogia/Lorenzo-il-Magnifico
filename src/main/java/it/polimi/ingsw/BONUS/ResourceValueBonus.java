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
}
