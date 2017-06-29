package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.BONUS.ADVANCED.EndGameCardBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CONTROLLER.EndGameCardController;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class EndGameCardControllerTest {

	@Test
	public void test() {
		Player player = new Player("", Color.BLUE);
		assertTrue(EndGameCardController.check(player, DevelopmentCardType.building));
		
		HashMap<DevelopmentCardType, Boolean> map = new HashMap<>();
		map.put(DevelopmentCardType.building, false);
		EndGameCardBonus endGameBonus = new EndGameCardBonus(map);
		player.getPersonalBoard().addPermanentBonus(endGameBonus);
		
		assertFalse(EndGameCardController.check(player, DevelopmentCardType.building));
		assertTrue(EndGameCardController.check(player, DevelopmentCardType.character));
	}

}
