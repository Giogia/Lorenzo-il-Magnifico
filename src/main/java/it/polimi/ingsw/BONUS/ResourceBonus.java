package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public abstract class ResourceBonus extends ImmediateBonus {
	protected ArrayList<Resource> resources;	
	private String subtype;
	
	public ResourceBonus(String type, ArrayList<Resource> resources){
		super("resourceBonus");
		subtype= type;
		this.resources = new ArrayList<>();
		this.resources.addAll(resources);
	}
	
	public String getSubtype() {
		return subtype;
	}
	
	public void getImmediateBonus(Player player){ 
		ArrayList<Resource> playerResources = player.getPersonalBoard().getResources();
		modifyResources(playerResources);
	}

	protected void modifyResources(ArrayList<Resource> playerResources){
		for (Resource resource : resources) {
			for (Resource playerResource : playerResources) {
				if (compareClass(playerResource, resource)){
					modify(playerResource, resource);
				}
			}
			
		}
	}
	// confronta la classe
	protected boolean compareClass(Resource resource1, Resource resource2){
		return(resource1.getClass().equals(resource2.getClass()));
	}
	
	protected abstract void modify(Resource resource1, Resource resource2);
	
	
	public ArrayList<Resource> getResources() {
		return resources;
	}

	public String getDescription() {
		String message = subtype + "\n";
		for (Resource resource : resources) {
			message = message + resource.getDescription() + "\n";
		}
		return message;
	}
	
}
