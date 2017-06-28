package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.BONUS.ImmediateBonus;

import java.util.*;

public class IsThereBonusController {	
	//returns true if in this position arraylist boardBonus is initialized, otherwise returns false
	public static boolean check(TowerFloor towerFloor){
		ArrayList<ImmediateBonus> allBonus = towerFloor.getBoardBonus();
		if(allBonus == null){
			return false;
		}
		return true;
	}
}
