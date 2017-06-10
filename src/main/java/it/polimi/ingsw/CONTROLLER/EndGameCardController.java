package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.EndGameCardBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;

public class EndGameCardController {
	
	public static boolean check(Player player, DevelopmentCardType developmentCardType){
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		for (PermanentBonus permanentBonus : playerBonus) {
			if (permanentBonus instanceof EndGameCardBonus){
				return ((EndGameCardBonus) permanentBonus).getDevelopmentCardBoolean(developmentCardType);
			}
		}
		return true;
	}

}
