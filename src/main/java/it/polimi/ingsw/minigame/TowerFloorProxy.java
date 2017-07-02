package it.polimi.ingsw.minigame;

import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.CARD.DevelopmentCardType;

public class TowerFloorProxy extends PositionProxy {

	private DevelopmentCardProxy developmentCardProxy;
	private DevelopmentCardType towerType;
	private int numberOfFloor;
	
	public TowerFloorProxy(TowerFloor towerFloor, DevelopmentCardType towerType, int numberOfFloor) {
		super(towerFloor);
		this.towerType = towerType;
		this.numberOfFloor = numberOfFloor;
		System.out.println(towerFloor.getDevelopmentCard());
		developmentCardProxy = new DevelopmentCardProxy(towerFloor.getDevelopmentCard());
	}
	
	public DevelopmentCardProxy getDevelopmentCardProxy() {
		return developmentCardProxy;
	}
	
	public void setDevelopmentCardProxy(DevelopmentCardProxy developmentCardProxy) {
		this.developmentCardProxy = developmentCardProxy;
	}

	public DevelopmentCardType getTowerType() {
		return towerType;
	}
	
	public int getNumberOfFloor() {
		return numberOfFloor;
	}
	
	public FamilyMemberProxy getFamilyMemberProxy(){
		return super.getFamilyMemberProxies().get(0);
	}
}
