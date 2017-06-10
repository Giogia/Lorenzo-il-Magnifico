package it.polimi.ingsw.HANDLER.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.OrderBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.GC_15.Player;

public class OrderBonusHandler {
	
	public static boolean handle(Player player, int turn){
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof OrderBonus){
					if (((OrderBonus) permanentBonus).getSkipAction()[turn]){
						player.getBoard().getGame().addSkippedPlayer(player);
						player.getBoard().getPassTurnController().lastMove(null);
						return false;
					}
				}
			}
		}
		return true;
	}

}
