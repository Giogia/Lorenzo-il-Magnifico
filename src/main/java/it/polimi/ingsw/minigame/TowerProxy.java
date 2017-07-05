package it.polimi.ingsw.minigame;

import java.io.Serializable;
import java.util.ArrayList;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.CARD.DevelopmentCardType;

public class TowerProxy implements Serializable{

	private ArrayList<TowerFloorProxy> towerFloorProxies = new ArrayList<>();
	private DevelopmentCardType developmentCardType;
	
	public TowerProxy(Tower tower) {
		//super(tower);
		developmentCardType = tower.getDevelopmentCardType();
		int numberOfTowerFloor = 0;
		for (TowerFloor position : (TowerFloor[]) tower.getPositions()) {
			towerFloorProxies.add(new TowerFloorProxy(position, developmentCardType, numberOfTowerFloor));
			numberOfTowerFloor++;
		}
	}

	public ArrayList<TowerFloorProxy> getTowerFloorProxies() {
		return towerFloorProxies;
	}
	
	public DevelopmentCardType getDevelopmentCardType() {
		return developmentCardType;
	}
	
	public TowerFloorProxy getTowerFloorProxy(int numberOfFloor){
		return towerFloorProxies.get(numberOfFloor);
	}
}
