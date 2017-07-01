package it.polimi.ingsw.minigame;

import java.util.ArrayList;

public class TowerProxy extends ZoneProxy{

	private ArrayList<TowerFloorProxy> towerFloorProxies;
	private DevelopmentCardProxy developmentCardProxy;

	public ArrayList<TowerFloorProxy> getTowerFloorProxies() {
		return towerFloorProxies;
	}
	
	public DevelopmentCardProxy getDevelopmentCardProxy() {
		return developmentCardProxy;
	}
}
