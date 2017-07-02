package bonus;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import it.polimi.ingsw.BOARD.CouncilPalace;
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
		CouncilPalace councilPalace = new CouncilPalace();
		Tower tower2 = new Tower(DevelopmentCardType.venture);
		canGoTo2.put(productionArea, false);
		canGoTo2.put(harvestArea, true);
		canGoTo2.put(market2, true);
		canGoTo2.put(councilPalace, false);
		canGoTo2.put(tower2, false);
		canGoTo2.put(tower, false);
		
		CanFamilyMemberBonus canFamilyMemberBonus2 = new CanFamilyMemberBonus(true, canGoTo2);
		
		canFamilyMemberBonus2.getPermanentBonus(nino);
		
		CanFamilyMemberBonus playerBonus = (CanFamilyMemberBonus) nino.getPersonalBoard().getPermanentBonus().get(0);
		
		assertNotEquals(canFamilyMemberBonus2, playerBonus);
		
		HashMap<Zone, Boolean> canGoTo3 = new HashMap<>();
		canGoTo3.put(productionArea, false);
		canGoTo3.put(harvestArea, true);
		canGoTo3.put(market2, true);
		canGoTo3.put(councilPalace, false);
		
		CanFamilyMemberBonus canFamilyMemberBonus3 = new CanFamilyMemberBonus(false, canGoTo3);
		canFamilyMemberBonus3.getPermanentBonus(nino);
		
		assertNotEquals(canFamilyMemberBonus3, nino.getPersonalBoard().getPermanentBonus().get(0));
		
		HashMap<Zone, Boolean> canGoTo4 = new HashMap<>();
		canGoTo4.put(harvestArea, false);
		CanFamilyMemberBonus canFamilyMemberBonus4 = new CanFamilyMemberBonus(false, canGoTo4);
		
		Player player = new Player(null, Color.RED);
		
		assertEquals("Your family members cannot go to: \nHarvest Area\n", canFamilyMemberBonus4.getDescription());
		
		CanFamilyMemberBonus canFamilyMemberBonus5 = new CanFamilyMemberBonus(true, new HashMap<>());
		
		assertEquals("you can go to any position even if occupied \n", canFamilyMemberBonus5.getDescription());
		
		canFamilyMemberBonus4.getPermanentBonus(player);
		
		assertEquals(canFamilyMemberBonus4, player.getPersonalBoard().getPermanentBonus().get(0));
		
		canFamilyMemberBonus.getPermanentBonus(player);
		assertNotEquals(canFamilyMemberBonus, player.getPersonalBoard().getPermanentBonus().get(0));
	}

}
