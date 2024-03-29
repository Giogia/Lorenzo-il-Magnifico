package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

//this bonus decreases a player's victory points with a certain proportion with the resources specified.
//It's activated every time he choose a card whose cost contains those resources.
public class LoseVictoryPointsPerCostBonus extends PermanentBonus {
	private DevelopmentCardType developmentCardType;
	private ArrayList<Resource> resources;
	
	public LoseVictoryPointsPerCostBonus(DevelopmentCardType developmentCardType, ArrayList<Resource> resources) {
		super("LoseVictoryPointsPerCostBonus");
		this.developmentCardType = developmentCardType;
		this.resources = resources;
	}
	
	public DevelopmentCardType getDevelopmentCardType() {
		return developmentCardType;
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
			description.append(resource.getDescription() + "\n");
		}
		description.append("in " + developmentCardType + " cards' cost \n");
		return description.toString();
	}
}
