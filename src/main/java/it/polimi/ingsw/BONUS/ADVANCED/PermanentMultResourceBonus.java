package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class PermanentMultResourceBonus extends PermanentResourceBonus {

	public PermanentMultResourceBonus(ArrayList<Resource> resources) {
		super("PermanentMultResourceBonus", resources);
	}

	@Override
	public PermanentMultResourceBonus createClone() {
		return new PermanentMultResourceBonus(this.resources);
	}

	@Override
	public void getPermanentBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof PermanentMultResourceBonus){
					((PermanentMultResourceBonus) permanentBonus).addBonus(this);
					return;
				}
			}
		}
		super.getPermanentBonus(player);
	}
	
	

	@Override
	protected void modifyResource(Resource resource, Resource newResource) {
		resource.multAmount(newResource.getAmount());
	}

	@Override
	protected void addBonus(PermanentResourceBonus newBonus) {
		if (newBonus instanceof PermanentMultResourceBonus){
			for (Resource newResource : newBonus.getResources()) {
				addResource(newResource);
			}
		}
	}
	
	public String getDescription(){
		String description = "Mult for: ";
		description = description + super.getDescription() +" each time you get that resource";
		return description;		
	}
}
