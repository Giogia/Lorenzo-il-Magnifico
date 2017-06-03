package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.BONUS.ImmediateBonus;

import java.util.*;

public class IsThereBonusController {

	
	// Prende TowerFloor, perchè nelle altre posizioni non ha senso controllare. Serviva solo per usare il bonus per prendere la carta
	public static boolean check(TowerFloor towerFloor){
		ArrayList<ImmediateBonus> allBonus = towerFloor.getBoardBonus();
		if(allBonus == null){
			return false;
		}
		return true;
	}
}
