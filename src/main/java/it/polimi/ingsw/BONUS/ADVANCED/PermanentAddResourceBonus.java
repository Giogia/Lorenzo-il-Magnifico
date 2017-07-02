package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class PermanentAddResourceBonus extends PermanentResourceBonus {

	public PermanentAddResourceBonus(ArrayList<Resource> resources) {
		super("PermanentAddResourceBonus", resources);
	}

	@Override
	public PermanentAddResourceBonus createClone() {
		return new PermanentAddResourceBonus(this.resources);
	}

	@Override
	public void getPermanentBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof PermanentAddResourceBonus){
					((PermanentAddResourceBonus) permanentBonus).addBonus(this);
					return;
				}
			}
		}
		super.getPermanentBonus(player);
	}
	
	

	@Override
	protected void modifyResource(Resource resource, Resource newResource) {
		resource.addAmount(newResource.getAmount());
	}

	@Override
	protected void addBonus(PermanentResourceBonus newBonus) {
		if (newBonus instanceof PermanentAddResourceBonus){
			for (Resource newResource : newBonus.getResources()) {
				addResource(newResource);
			}
		}
	}
	
	public String getDescription(){
		String description = "Add: ";
		description = description + super.getDescription() +" each time you get that resource";
		return description;		
	}

}
