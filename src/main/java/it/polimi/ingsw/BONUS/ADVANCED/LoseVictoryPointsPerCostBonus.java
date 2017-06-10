package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

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

}
