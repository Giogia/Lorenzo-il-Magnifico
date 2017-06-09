package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class ResourcePerMissedExcommunicationBonus extends PermanentResourceBonus {

	public ResourcePerMissedExcommunicationBonus(ArrayList<Resource> resources) {
		super("ResourcePerMissedExcommunicationBonus", resources);
	}

	@Override
	public ResourcePerMissedExcommunicationBonus createClone() {
		return new ResourcePerMissedExcommunicationBonus(this.resources);
	}
	
	@Override
	public void getPermanentBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof ResourcePerMissedExcommunicationBonus){
					((ResourcePerMissedExcommunicationBonus) permanentBonus).addBonus(this);
					return;
				}
			}
		}
		super.getPermanentBonus(player);
	}

	public void addBonus(ResourcePerMissedExcommunicationBonus newBonus){
		for (Resource resource : newBonus.getResources()) {
			addResource(resource);
		}
	}
	
	public void addResource(Resource newResource){
		for (Resource resource : resources) {
			if (resource.getClass().equals(newResource.getClass())){
				resource.addAmount(newResource.getAmount());
				return;
			}
		}
		resources.add(newResource);
	}
}
