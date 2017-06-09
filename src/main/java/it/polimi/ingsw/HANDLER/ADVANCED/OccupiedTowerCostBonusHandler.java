package it.polimi.ingsw.HANDLER.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.OccupiedTowerCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Coins;

public class OccupiedTowerCostBonusHandler {
	
	public static void handle(Coins occupiedTowerCost, Player player){
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof OccupiedTowerCostBonus){
					occupiedTowerCost.addAmount(((OccupiedTowerCostBonus) permanentBonus).getAddOccupiedCost());
					occupiedTowerCost.multAmount(((OccupiedTowerCostBonus) permanentBonus).getMultOccupiedCost());
					return;
				}
			}
		}
	}

}
