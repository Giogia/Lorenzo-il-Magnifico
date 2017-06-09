package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.BONUS.ADVANCED.TerritoryCardRequirementBonus;
import it.polimi.ingsw.GC_15.Player;

public class TerritoryCardRequirementController {
	
	public static boolean check(Player player){
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof TerritoryCardRequirementBonus){
					return ((TerritoryCardRequirementBonus) permanentBonus).getNeedRequirement();
				}
			}
		}
		return true;
	}

}
