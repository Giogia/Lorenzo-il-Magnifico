package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class LoseVictoryPointsPerResourceBonus extends PermanentBonus {
	private ArrayList<Resource> resources;

	public LoseVictoryPointsPerResourceBonus(ArrayList<Resource> resources) {
		super("LoseVictoryPointsPerResourceBonus");
		this.resources = resources;
	}
	
	public ArrayList<Resource> getResources() {
		return resources;
	}
	
	@Override
	public void getPermanentBonus(Player player) {
		super.getPermanentBonus(player);
	}

	public String getDescription(){
		String description = "You lose a victory point every :\n";
		for(Resource resource : resources){
			description = description + resource.getDescription() +" you have \n";
		}
		return description;
	}
}
