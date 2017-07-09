package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.GC_15.MyException;

public class PositionWithoutDevelopmentCardController { 

	//check if there is the card in the position you want to occupy
	//used instead of occupied position check in towerhandler
	//if there is no card you cannot place your family member on a position
	public static boolean check(TowerFloor towerFloor) throws MyException{
		if(towerFloor.getDevelopmentCard() != null){
			return true;
		}
		throw new MyException("Position without card!");
	}
}
