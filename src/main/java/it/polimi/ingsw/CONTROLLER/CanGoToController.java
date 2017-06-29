package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.ADVANCED.CanFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.GC_15.Player;

public class CanGoToController {
	//return true if player can go in this zone
	//controll because player may have a permanentbonus (or better, malus) that doens't allow the action 
	public static boolean check(Player player, Zone zone){
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		for (PermanentBonus permanentBonus : playerBonus) {
			if (permanentBonus instanceof CanFamilyMemberBonus){
				Zone bonusZone = ((CanFamilyMemberBonus) permanentBonus).findZone(zone);
				if (bonusZone != null){
					return ((CanFamilyMemberBonus) permanentBonus).getCanGoTo().get(bonusZone);
				}
			}
		}
		return true;
	}

}
