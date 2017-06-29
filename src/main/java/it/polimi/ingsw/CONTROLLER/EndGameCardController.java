package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.EndGameCardBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;

public class EndGameCardController {
	//check if player can take bonus of end game of that developmentCardType
	public static boolean check(Player player, DevelopmentCardType developmentCardType){
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		
		//if permanent bonus is a EndGameCardBonus
		for (PermanentBonus permanentBonus : playerBonus) {
			if (permanentBonus instanceof EndGameCardBonus){
				//If a developmentCardType is false you can't have the endGame bonus for that cardType
				return ((EndGameCardBonus) permanentBonus).getDevelopmentCardBoolean(developmentCardType);
			}
		}
		//player hasn't an EndGameCardBonus -> he can take end game bonus of all developmentCardType
		return true;
	}

}
