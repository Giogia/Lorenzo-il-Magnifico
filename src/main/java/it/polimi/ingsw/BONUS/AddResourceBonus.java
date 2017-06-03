package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.RESOURCE.Resource;

public class AddResourceBonus extends ResourceBonus {
	
	public AddResourceBonus(ArrayList<Resource> resources) {
		super("addResourceBonus", resources);
	}

	@Override
	protected void modify(Resource resource1, Resource resource2) {
		resource1.addAmount(resource2.getAmount());
	}
	
	@Override
	public String getDescription() {
		String description = "Aggiungi alle tue risorse: \n";
		description = description + super.getDescription();
		return description;
	}
	
	@Override
	public ResourceBonus clone() {
		AddResourceBonus newBonus = new AddResourceBonus(this.resources);
		return newBonus;
	}
}
