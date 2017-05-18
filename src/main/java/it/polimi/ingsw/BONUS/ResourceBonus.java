package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public abstract class ResourceBonus extends PermanentBonus implements ImmediateBonus {
	protected ArrayList<Resource> resources;
	
	
	public ResourceBonus(ArrayList<Resource> resources){
		this.resources = new ArrayList<>();
		this.resources.addAll(resources);
	}
	
	
	public void getImmediateBonus(Player player){ 
		ArrayList<Resource> playerResources = player.getPersonalBoard().getResources();
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
	
}
