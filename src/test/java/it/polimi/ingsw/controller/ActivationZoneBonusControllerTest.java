package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import it.polimi.ingsw.BOARD.CouncilPalace;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.ADVANCED.ActivationZoneBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CONTROLLER.ActivationZoneBonusController;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class ActivationZoneBonusControllerTest {

	@Test
	public void test() {
		Player player = new Player("", Color.RED);
		
		assertTrue(ActivationZoneBonusController.check(null, player));
		

		Zone zone = new Tower(DevelopmentCardType.territory);
		HashMap<Tower, Boolean> towers = new HashMap<>();
		towers.put(new Tower(DevelopmentCardType.territory), false);
		ActivationZoneBonus bonus = new ActivationZoneBonus(towers, true);
		player.getPersonalBoard().addPermanentBonus(bonus);
	
		assertFalse(ActivationZoneBonusController.check(zone, player));
		
		Zone councilPalace = new CouncilPalace();
		HashMap<Tower, Boolean> towers2 = new HashMap<>();
		ActivationZoneBonus bonus2 = new ActivationZoneBonus(towers2, false);
		player.getPersonalBoard().addPermanentBonus(bonus2);
		
		assertFalse(ActivationZoneBonusController.check(councilPalace, player));
	
	}

}
