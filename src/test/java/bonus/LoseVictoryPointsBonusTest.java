package bonus;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BONUS.ADVANCED.LoseVictoryPointsPerCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.LoseVictoryPointsPerResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.Wood;

public class LoseVictoryPointsBonusTest {

	@Test
	public void costTest() {
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new Coins(2, 1));
		resources.add(new Wood(5, 1));
		
		LoseVictoryPointsPerCostBonus loseVictoryPointsPerCostBonus = new LoseVictoryPointsPerCostBonus(DevelopmentCardType.character, resources);
		
		Player giovanni = new Player(null, Color.RED);
		
		assertEquals(resources, loseVictoryPointsPerCostBonus.getResources());
		
		assertEquals(DevelopmentCardType.character, loseVictoryPointsPerCostBonus.getDevelopmentCardType());
		
		loseVictoryPointsPerCostBonus.getPermanentBonus(giovanni);
		
		assertEquals(loseVictoryPointsPerCostBonus, giovanni.getPersonalBoard().getPermanentBonus().get(0));
		
		assertEquals("You lose a victory point every :\n2 Coins\n5 Wood\nin character cards' cost \n", loseVictoryPointsPerCostBonus.getDescription());
	}
	
	@Test
	public void resourceTest() {
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new Coins(2, 1));
		resources.add(new Wood(5, 1));
		
		LoseVictoryPointsPerResourceBonus loseVictoryPointsPerResourceBonus = new LoseVictoryPointsPerResourceBonus(resources);
		
		Player giovanni = new Player(null, Color.RED);
		
		assertEquals(resources, loseVictoryPointsPerResourceBonus.getResources());
		
		loseVictoryPointsPerResourceBonus.getPermanentBonus(giovanni);
		
		assertEquals(loseVictoryPointsPerResourceBonus, giovanni.getPersonalBoard().getPermanentBonus().get(0));
		
		assertEquals("You lose a victory point every :\n2 Coins you have \n5 Wood you have \n", loseVictoryPointsPerResourceBonus.getDescription());
	}

}
