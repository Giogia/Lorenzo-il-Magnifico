package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.BONUS.Bonus;
import java.util.*;

public class IsThereBonusController {

	
	// Prende TowerFloor, perchè nelle altre posizioni non ha senso controllare. Serviva solo per usare il bonus per prendere la carta
	public boolean check(TowerFloor towerFloor){
		ArrayList<Bonus> allBonus = towerFloor.getBoardBonus();
		return allBonus.isEmpty();
	}
}
