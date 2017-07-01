package it.polimi.ingsw.minigame;

import it.polimi.ingsw.CARD.DevelopmentCardType;

public class TowerFloorProxy extends PositionProxy {

	private DevelopmentCardProxy developmentCardProxy;
	private int towerFloor;
	private DevelopmentCardType towerType;
	
	public TowerFloorProxy(DevelopmentCardProxy developmentCardProxy, int towerFloor, DevelopmentCardType towerType) {
		this.developmentCardProxy = developmentCardProxy;
		this.towerFloor = towerFloor;
		this.towerType = towerType;
	}
	
	public DevelopmentCardProxy getDevelopmentCardProxy() {
		return developmentCardProxy;
	}
	
	public void setDevelopmentCardProxy(DevelopmentCardProxy developmentCardProxy) {
		this.developmentCardProxy = developmentCardProxy;
	}
	
	public int getTowerFloor() {
		return towerFloor;
	}
	
	public DevelopmentCardType getTowerType() {
		return towerType;
	}
	
}
