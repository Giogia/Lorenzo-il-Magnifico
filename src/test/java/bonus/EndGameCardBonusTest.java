package bonus;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import it.polimi.ingsw.BONUS.ADVANCED.EndGameCardBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class EndGameCardBonusTest {

	@Test
	public void test() {
		Player fabio = new Player(null, Color.YELLOW);
		
		HashMap<DevelopmentCardType, Boolean> developmentCard = new HashMap<>();
		developmentCard.put(DevelopmentCardType.character, true);
		developmentCard.put(DevelopmentCardType.venture, false);
		
		EndGameCardBonus endGameCardBonus = new EndGameCardBonus(developmentCard);
		
		assertEquals("You cannot have the end game bonus for :\nventurecards \n", endGameCardBonus.getDescription());
		
		assertFalse(endGameCardBonus.getDevelopmentCardBoolean(DevelopmentCardType.venture));

		assertTrue(endGameCardBonus.getDevelopmentCardBoolean(DevelopmentCardType.territory));
		
		assertEquals(developmentCard, endGameCardBonus.getDevelopmentCardType());
		
		endGameCardBonus.getPermanentBonus(fabio);
		
		assertEquals(endGameCardBonus, fabio.getPersonalBoard().getPermanentBonus().get(0));
		
		HashMap<DevelopmentCardType, Boolean> developmentCard2 = new HashMap<>();
		developmentCard2.put(DevelopmentCardType.character, false);
		developmentCard2.put(DevelopmentCardType.territory, false);
		
		EndGameCardBonus endGameCardBonus2 = new EndGameCardBonus(developmentCard2);
		
		endGameCardBonus2.getPermanentBonus(fabio);
		
		assertNotEquals(developmentCard2, fabio.getPersonalBoard().getPermanentBonus().get(0));
	}

}
