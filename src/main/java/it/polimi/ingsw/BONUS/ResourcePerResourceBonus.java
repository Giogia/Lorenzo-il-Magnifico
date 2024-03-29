package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

//this bonus increases a player's resource for every amount of a certain resource owned
public class ResourcePerResourceBonus extends AddResourceBonus{
	Resource requirement;
	
	public ResourcePerResourceBonus(ArrayList<Resource> resources, Resource requirement) {
		super(resources);
		this.requirement = requirement;
	}
	
	@Override
	public void getImmediateBonus(Player player) {
		Resource playerResource = player.getPersonalBoard().getResource(requirement.getResourceType());
		int playerResourceAmount = playerResource.getAmount();
		for (Resource resource : resources) {
			resource.multAmount(playerResourceAmount);
		}
		super.getImmediateBonus(player);
	}
	
	@Override
	public String getDescription() {
		String description = "For each " + requirement.getDescription() + " you get: \n";
		description = description + super.getDescription();
		return description;
	}
	
	@Override
	public ResourceBonus createClone() {
		ResourcePerResourceBonus newBonus = new ResourcePerResourceBonus(this.resources, this.requirement);
		return newBonus;
	}
}
