package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public abstract class PermanentResourceBonus extends PermanentBonus {
	protected ArrayList<Resource> resources;
	private String subsubtype;

	public PermanentResourceBonus(String type, ArrayList<Resource> resources) {
		super("PermanentResourceBonus");
		subsubtype = type;
		this.resources = resources;
	}
	
	public ArrayList<Resource> getResources() {
		return resources;
	}
	
	public String getSubsubtype() {
		return subsubtype;
	}
	
	public abstract PermanentResourceBonus createClone();
	
	protected void addBonus(PermanentResourceBonus newBonus){
		
	}
	
	protected void addResource(Resource newResource){
		for (Resource resource : resources) {
			if (resource.getClass().equals(newResource.getClass())){
				modifyResource(resource, newResource);
				return;
			}
		}
		resources.add(newResource);
	}

	protected void modifyResource(Resource resource, Resource newResource){
		
	}
	
}
