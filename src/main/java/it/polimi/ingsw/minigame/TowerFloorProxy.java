package it.polimi.ingsw.minigame;

import it.polimi.ingsw.BOARD.TowerFloor;

public class TowerFloorProxy extends PositionProxy {

	private DevelopmentCardProxy developmentCardProxy;

	public TowerFloorProxy(TowerFloor towerFloor) {
		super(towerFloor);
		developmentCardProxy = new DevelopmentCardProxy(towerFloor.getDevelopmentCard());
	}
	
	public DevelopmentCardProxy getDevelopmentCardProxy() {
		return developmentCardProxy;
	}
	
	public void setDevelopmentCardProxy(DevelopmentCardProxy developmentCardProxy) {
		this.developmentCardProxy = developmentCardProxy;
	}
	
}
