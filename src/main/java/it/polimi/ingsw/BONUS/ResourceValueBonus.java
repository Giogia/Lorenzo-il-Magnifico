package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class ResourceValueBonus extends ImmediateBonus{
	private ArrayList<Resource> resources;

	public ResourceValueBonus() {
	}

	@Override
	public void getImmediateBonus(Player player) {
		ArrayList<Resource> playerResources = player.getPersonalBoard().getResources();
		for (Resource resource : resources) {
			for (Resource playerResource : playerResources) {
				if (compareClass(playerResource, resource)){
					setValue(playerResource, resource);
				}
			}
			
		}
	}
	
	
	//confronta le classi 
	private boolean compareClass(Resource resource1, Resource resource2){
		return(resource1.getClass().equals(resource2.getClass()));
	}

	
	//aggiunge al valore del primo il valore del secondo, bisogna quindi mettere il valore del bonus nel value e non all'amount della risorsa del bonus
	private void setValue(Resource resource1, Resource resource2){
		resource1.setValue(resource2.getValue());
	}
}
