package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.RESOURCE.Resource;

public class AddResourceBonus extends ResourceBonus {

	public AddResourceBonus(ArrayList<Resource> resources) {
		super(resources);
	}

	@Override
	protected void modify(Resource resource1, Resource resource2) {
		resource1.addAmount(resource2.getAmount());
	}

	
}
