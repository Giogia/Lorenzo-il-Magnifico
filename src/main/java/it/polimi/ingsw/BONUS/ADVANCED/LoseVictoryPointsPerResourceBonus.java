package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

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

}
