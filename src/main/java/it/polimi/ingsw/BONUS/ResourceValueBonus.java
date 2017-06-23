package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.PermanentAddResourceBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentResourceBonus;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class ResourceValueBonus extends PermanentResourceBonus{

	public ResourceValueBonus(ArrayList<Resource> resources) {
		super("ResourceValueBonus", resources);
	}

	
	public String getDescription() {
		String description = "";
		for (Resource resource : resources) {
			description = description + "For each " + resource.getClass().getSimpleName() + " you have to pay " + resource.getAmount() + "\n";
		}
		return description;
	}
	
	@Override
	public ResourceValueBonus createClone() {
		return new ResourceValueBonus(this.resources);
	}
	
	@Override
	public void getPermanentBonus(Player player) {
		setValue(player);
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof ResourceValueBonus){
					((ResourceValueBonus) permanentBonus).addBonus(this);
					return;
				}
			}
		}
		super.getPermanentBonus(player);
	}
	
	

	private void setValue(Player player) {
		for (Resource bonusResource : resources) {
			for (Resource playerResource : player.getPersonalBoard().getResources()) {
				if (playerResource.getClass().equals(bonusResource.getClass())){
					playerResource.setValue(bonusResource.getAmount());
				}
			}
		}
	}


	@Override
	protected void modifyResource(Resource resource, Resource newResource) {
		resource.setValue(newResource.getValue());
	}

	@Override
	protected void addBonus(PermanentResourceBonus newBonus) {
		if (newBonus instanceof ResourceValueBonus){
			for (Resource newResource : newBonus.getResources()) {
				addResource(newResource);
				return;
			}
		}
	}

}
