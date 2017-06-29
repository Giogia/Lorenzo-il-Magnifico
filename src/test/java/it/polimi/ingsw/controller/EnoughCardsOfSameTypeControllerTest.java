package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.CONTROLLER.EnoughCardsOfSameTypeController;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class EnoughCardsOfSameTypeControllerTest {

	@Test(expected = MyException.class)
	public void test() throws MyException {
		Player player = new Player("marco", Color.BLUE);
		assertFalse(EnoughCardsOfSameTypeController.check(player, 2));
		
		DevelopmentCard card = new Territory("", 1, 1, null, null);
		for (CardContainer container : player.getPersonalBoard().getCardContainers()) {
			container.add(card);
		}
		assertTrue(EnoughCardsOfSameTypeController.check(player, 1));
	}

}
