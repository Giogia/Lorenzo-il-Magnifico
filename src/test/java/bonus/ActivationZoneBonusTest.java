package bonus;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BONUS.ADVANCED.ActivationZoneBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class ActivationZoneBonusTest {

	@Test
	public void test() {
		Player giuseppe = new Player("Giuseppe", Color.YELLOW);
		
		HashMap<Tower, Boolean> towers = new HashMap<>();
		Tower tower1 = new Tower(DevelopmentCardType.character);
		towers.put(tower1, false);
		ActivationZoneBonus activationZoneBonus1 = new ActivationZoneBonus(towers, true);
		
		assertEquals("You cannot take position bonus from : \nTower character", activationZoneBonus1.getDescription());
		
		activationZoneBonus1.getPermanentBonus(giuseppe);
		
		assertEquals(activationZoneBonus1, giuseppe.getPersonalBoard().getPermanentBonus().get(0));
		
		HashMap<Tower, Boolean> towers2 = new HashMap<>();
		towers2.put(tower1, true);
		Tower tower2 = new Tower(DevelopmentCardType.territory);
		towers2.put(tower2, false);
		ActivationZoneBonus activationZoneBonus2 = new ActivationZoneBonus(towers2, false);
		
		assertEquals("You cannot take position bonus from : \nTower territoryCouncil Palace", activationZoneBonus2.getDescription());
		
		activationZoneBonus2.getPermanentBonus(giuseppe);
		
		ActivationZoneBonus playerBonus = (ActivationZoneBonus) giuseppe.getPersonalBoard().getPermanentBonus().get(0);
		
		
		assertNotEquals(activationZoneBonus2, playerBonus);
		
		assertFalse(playerBonus.getCouncilPalace());
		assertFalse(playerBonus.getTowers().get(tower1));
		assertFalse(playerBonus.getTowers().get(tower2));
	}

}
