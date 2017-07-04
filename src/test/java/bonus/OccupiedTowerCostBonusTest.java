package bonus;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.BONUS.ADVANCED.OccupiedTowerCostBonus;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.HANDLER.ADVANCED.OccupiedTowerCostBonusHandler;
import it.polimi.ingsw.RESOURCE.Coins;

public class OccupiedTowerCostBonusTest {

	@Test
	public void test() {
		Player marco = new Player(null, Color.BLUE);
		
		Coins occupiedTowerCost = new Coins(3, 1);
		OccupiedTowerCostBonusHandler.handle(occupiedTowerCost, marco);
		assertEquals(3, occupiedTowerCost.getAmount());
		assertEquals(1, occupiedTowerCost.getValue());
		
		OccupiedTowerCostBonus occupiedTowerCostBonus1 = new OccupiedTowerCostBonus(2, 1);
		
		assertEquals("Everytime a tower is occupied you have to pay : \n2more coins \n", occupiedTowerCostBonus1.getDescription());
		
		occupiedTowerCostBonus1.getPermanentBonus(marco);
		assertEquals(occupiedTowerCostBonus1, marco.getPersonalBoard().getPermanentBonus().get(0));
		
		OccupiedTowerCostBonus occupiedTowerCostBonus2 = new OccupiedTowerCostBonus(0, 3);
		
		assertEquals("Everytime a tower is occupied you have to pay : \n3times the coins \n", occupiedTowerCostBonus2.getDescription());
		occupiedTowerCostBonus2.getPermanentBonus(marco);
		
		OccupiedTowerCostBonus playerBonus = (OccupiedTowerCostBonus) marco.getPersonalBoard().getPermanentBonus().get(0);
		assertEquals(2, playerBonus.getAddOccupiedCost());
		assertEquals(3, playerBonus.getMultOccupiedCost());
		
		OccupiedTowerCostBonusHandler.handle(occupiedTowerCost, marco);
		assertEquals(15, occupiedTowerCost.getAmount());
		assertEquals(1, occupiedTowerCost.getValue());
	}

}
