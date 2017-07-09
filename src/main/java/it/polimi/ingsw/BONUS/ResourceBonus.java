package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.ADVANCED.PermanentResourceBonusHandler;
import it.polimi.ingsw.RESOURCE.Resource;

//abstract class for every bonus that modfy a player's resource
public abstract class ResourceBonus extends ImmediateBonus {
	protected ArrayList<Resource> resources;	
	private String subtype;
	
	public ResourceBonus(String type, ArrayList<Resource> resources){
		super("resourceBonus");
		subtype = type;
		this.resources = new ArrayList<>();
		this.resources.addAll(resources);
	}
	
	public String getSubtype() {
		return subtype;
	}
	
	public void getImmediateBonus(Player player){ 
		ArrayList<Resource> clonedResources = new ArrayList<>();
		for (Resource resource : resources) {
			clonedResources.add(resource.createClone());
		}
		PermanentResourceBonusHandler.handle(clonedResources, player);
		ArrayList<Resource> playerResources = player.getPersonalBoard().getResources();
		modifyResources(playerResources, clonedResources);
	}

	protected void modifyResources(ArrayList<Resource> playerResources, ArrayList<Resource> clonedResources){
		for (Resource resource : clonedResources) {
			for (Resource playerResource : playerResources) {
				if (compareClass(playerResource, resource)){
					modify(playerResource, resource);
				}
			}
			
		}
	}

	protected boolean compareClass(Resource resource1, Resource resource2){
		return(resource1.getClass().equals(resource2.getClass()));
	}
	
	protected abstract void modify(Resource resource1, Resource resource2);
	
	
	public ArrayList<Resource> getResources() {
		return resources;
	}

	public String getDescription() {
		StringBuilder description = new StringBuilder();
		for (Resource resource : resources) {
			description.append(resource.getDescription() + "\n");
		}
		return description.toString();
	}
	
	public abstract ResourceBonus createClone();
}
