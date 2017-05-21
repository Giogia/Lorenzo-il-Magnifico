package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.BONUS.Bonus;

public class IsThereBonusController {

	public boolean check(TowerFloor towerFloor){
		Bonus[] allBonus = towerFloor.getBoardBonus();
		for(Bonus bonus: allBonus){
				if(bonus != null){
					return true;
				}
		}
		return false;
	}
}
