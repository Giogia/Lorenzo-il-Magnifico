package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;

public class EnoughCardsOfSameTypeController {

	public static boolean check(Player player, int numberOfSameCards) throws MyException{
		for(CardContainer cardContainer: player.getPersonalBoard().getCardContainers()){
			if(cardContainer.getDevelopmentCards().size()>=numberOfSameCards)
				return true;
		}
		throw new MyException("You don't have enough cards of the same type!");
	}
}
