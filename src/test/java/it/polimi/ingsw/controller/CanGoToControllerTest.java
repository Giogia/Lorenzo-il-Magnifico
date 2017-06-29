package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.ADVANCED.CanFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CONTROLLER.CanGoToController;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class CanGoToControllerTest {

	@Test
	public void test() {
		Player player = new Player("", Color.GREEN);
		Zone zone = new Tower(DevelopmentCardType.building);
		
		assertTrue(CanGoToController.check(player, zone));
		
		HashMap<Zone, Boolean> canGoTo = new HashMap<>();
		canGoTo.put(zone, false);
		PermanentBonus malus = new CanFamilyMemberBonus(false, canGoTo);
		player.getPersonalBoard().addPermanentBonus(malus);
		
		assertFalse(CanGoToController.check(player, zone));
	}

}
