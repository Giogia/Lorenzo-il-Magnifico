package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.TowerFloor;

public class PositionWithoutDevelopmentCardController { //to use instead of occupied position in towerhandler

	public static boolean check(TowerFloor towerFloor) throws Exception{
		if(towerFloor.getDevelopmentCard() != null){
			return true;
		}
		throw new Exception("Position without card!");
	}
}
//to use instead of occupied position in towerhandler
//if there is no card you cannot place your family member
