package bonus;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import it.polimi.ingsw.BOARD.HarvestArea;
import it.polimi.ingsw.BOARD.Market;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.ADVANCED.CanFamilyMemberBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class CanFamilyMemberBonusTest {

	@Test
	public void test() {
		HashMap<Zone, Boolean> canGoTo = new HashMap<>();
		Tower tower = new Tower(DevelopmentCardType.character);
		Market market = new Market();
		canGoTo.put(tower, false);
		canGoTo.put(market, false);
		
		CanFamilyMemberBonus canFamilyMemberBonus = new CanFamilyMemberBonus(false, canGoTo);
		Player nino = new Player("Nino", Color.BLUE);
		
		canFamilyMemberBonus.getPermanentBonus(nino);
		
		assertEquals(canFamilyMemberBonus, nino.getPersonalBoard().getPermanentBonus().get(0));
		assertEquals(canGoTo, canFamilyMemberBonus.getCanGoTo());
		assertFalse(canFamilyMemberBonus.getOccupiedYet());
		
		HashMap<Zone, Boolean> canGoTo2 = new HashMap<>();
		Market market2 = new Market();
		HarvestArea harvestArea = new HarvestArea();
		ProductionArea productionArea = new ProductionArea();
		Tower tower2 = new Tower(DevelopmentCardType.venture);
		canGoTo2.put(productionArea, false);
		canGoTo2.put(harvestArea, true);
		canGoTo2.put(market2, true);
		canGoTo2.put(tower2, false);
		canGoTo2.put(tower, false);
		
		CanFamilyMemberBonus canFamilyMemberBonus2 = new CanFamilyMemberBonus(true, canGoTo2);
		
		assertEquals("Your family members cannot go to: \nProduction Area\nTower character\nTower venture\nyou can go to any position even if occupied \n" , canFamilyMemberBonus2.getDescription());
		
		canFamilyMemberBonus2.getPermanentBonus(nino);
		
		CanFamilyMemberBonus playerBonus = (CanFamilyMemberBonus) nino.getPersonalBoard().getPermanentBonus().get(0);
		
		assertNotEquals(canFamilyMemberBonus2, playerBonus);
	}

}
