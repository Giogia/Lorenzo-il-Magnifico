package it.polimi.ingsw.minigame;

import java.util.ArrayList;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.CARD.DevelopmentCardType;

public class TowerProxy extends ZoneProxy{

	private ArrayList<TowerFloorProxy> towerFloorProxies = new ArrayList<>();
	private DevelopmentCardType developmentCardType;
	
	public TowerProxy(Tower tower) {
		super(tower);
		developmentCardType = tower.getDevelopmentCardType();
	}

	public ArrayList<TowerFloorProxy> getTowerFloorProxies() {
		return towerFloorProxies;
	}
	
	public DevelopmentCardType getDevelopmentCardType() {
		return developmentCardType;
	}
}
