package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

//this bonus decreases a player's victory points for every specifi amount of specific resources owned
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
		StringBuilder description = new StringBuilder();
		description.append("You lose a victory point every :\n");
		for(Resource resource : resources){
			description.append(resource.getDescription() +" you have \n");
		}
		return description.toString();
	}
}
