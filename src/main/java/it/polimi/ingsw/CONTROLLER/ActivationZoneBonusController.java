package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.CouncilPalace;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.ADVANCED.ActivationZoneBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.GC_15.Player;

public class ActivationZoneBonusController {
	//return true if player can take bonus of this zone (only for Preacher Card)
	public static boolean check(Zone zone, Player player){
		ActivationZoneBonus activationZoneBonus = null;
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			//copying Activation Zone Bonus of player
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof ActivationZoneBonus){
					activationZoneBonus = (ActivationZoneBonus) permanentBonus;
				}
			}
			//if player has an activation zone bonus
			if (activationZoneBonus != null){
				if (zone instanceof Tower){
					for (Tower tower : activationZoneBonus.getTowers().keySet()) {
						if (((Tower) zone).getDevelopmentCardType().equals(tower.getDevelopmentCardType())){
							return activationZoneBonus.getTowers().get(tower);
						}
					}
				}
				else {
					return activationZoneBonus.getCouncilPalace();
				}
			}
		}
		return true;
	}

}
