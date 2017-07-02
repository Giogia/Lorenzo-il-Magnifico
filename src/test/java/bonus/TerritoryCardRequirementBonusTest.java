package bonus;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.BONUS.ADVANCED.TerritoryCardRequirementBonus;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class TerritoryCardRequirementBonusTest {

	@Test
	public void test1() {
		Player luigi = new Player(null, Color.GREEN);
		
		TerritoryCardRequirementBonus territoryCardRequirementBonus = new TerritoryCardRequirementBonus(true);
		assertEquals("you need military points to activate a territory card ", territoryCardRequirementBonus.getDescription());
		
		territoryCardRequirementBonus.getPermanentBonus(luigi);
		
		assertEquals(territoryCardRequirementBonus, luigi.getPersonalBoard().getPermanentBonus().get(0));
		
		TerritoryCardRequirementBonus territoryCardRequirementBonus2 = new TerritoryCardRequirementBonus(false);
		
		assertEquals("You don't need Military Points to activate a territory card \n", territoryCardRequirementBonus2.getDescription());
		
		territoryCardRequirementBonus2.getPermanentBonus(luigi);
		
		TerritoryCardRequirementBonus playerBonus = (TerritoryCardRequirementBonus) luigi.getPersonalBoard().getPermanentBonus().get(0);
		
		assertFalse(playerBonus.getNeedRequirement());
	}
	
	@Test
	public void test2() {
		Player luigi = new Player(null, Color.GREEN);
		
		TerritoryCardRequirementBonus territoryCardRequirementBonus = new TerritoryCardRequirementBonus(false);
		
		territoryCardRequirementBonus.getPermanentBonus(luigi);
		
		assertEquals(territoryCardRequirementBonus, luigi.getPersonalBoard().getPermanentBonus().get(0));
		
		TerritoryCardRequirementBonus territoryCardRequirementBonus2 = new TerritoryCardRequirementBonus(true);
		
		territoryCardRequirementBonus2.getPermanentBonus(luigi);
		
		TerritoryCardRequirementBonus playerBonus = (TerritoryCardRequirementBonus) luigi.getPersonalBoard().getPermanentBonus().get(0);
		
		assertFalse(playerBonus.getNeedRequirement());
	}
	
	@Test
	public void test3() {
		Player luigi = new Player(null, Color.GREEN);
		
		TerritoryCardRequirementBonus territoryCardRequirementBonus = new TerritoryCardRequirementBonus(true);
		
		territoryCardRequirementBonus.getPermanentBonus(luigi);
		
		assertEquals(territoryCardRequirementBonus, luigi.getPersonalBoard().getPermanentBonus().get(0));
		
		TerritoryCardRequirementBonus territoryCardRequirementBonus2 = new TerritoryCardRequirementBonus(true);
		
		territoryCardRequirementBonus2.getPermanentBonus(luigi);
		
		TerritoryCardRequirementBonus playerBonus = (TerritoryCardRequirementBonus) luigi.getPersonalBoard().getPermanentBonus().get(0);
		
		assertTrue(playerBonus.getNeedRequirement());
	}

}
