package bonus;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BONUS.ADVANCED.AddCardCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.MultiplyCardCostBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.HANDLER.ADVANCED.CardCostHandler;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.VictoryPoints;

public class CardCostBonusTest {

	@Test
	public void addTest() {
		Player michele = new Player("Michele", Color.BLUE);
		DevelopmentCardType cardType = DevelopmentCardType.venture;
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new Coins(1, 1));
		resources.add(new MilitaryPoints(-3, 1));
		AddCardCostBonus addCardCostBonus = new AddCardCostBonus(cardType, resources);
		
		assertEquals("Add to venturecards' cost : \n1 Coins\n-3 Military Points\n", addCardCostBonus.getDescription());
		
		addCardCostBonus.getPermanentBonus(michele);
		
		assertEquals(addCardCostBonus, michele.getPersonalBoard().getPermanentBonus().get(0));
		
		resources.add(new VictoryPoints(2, 1));
		
		AddCardCostBonus addCardCostBonus2 = new AddCardCostBonus(cardType, resources);
		assertEquals(resources, addCardCostBonus2.getResources());
		
		addCardCostBonus2.getPermanentBonus(michele);

		assertNotEquals(addCardCostBonus2, michele.getPersonalBoard().getPermanentBonus().get(0));
		
		AddCardCostBonus playerBonus = (AddCardCostBonus) michele.getPersonalBoard().getPermanentBonus().get(0);
		ArrayList<Resource> playerResource = playerBonus.getResources();
		assertEquals(2, playerResource.get(0).getAmount());
		assertEquals(-6, playerResource.get(1).getAmount());
		assertEquals(2, playerResource.get(2).getAmount());
		
		assertEquals("AddCardCostBonus", addCardCostBonus.getSubsubtype());
		
		AddCardCostBonus addCardCostBonus3 = new AddCardCostBonus(DevelopmentCardType.character, resources);
		
		addCardCostBonus3.getPermanentBonus(michele);
		
		assertEquals(addCardCostBonus3, michele.getPersonalBoard().getPermanentBonus().get(1));
		
		ArrayList<Resource> cost = new ArrayList<>();
		cost.add(new Coins(3, 1));
		cost.add(new MilitaryPoints(2, 1));
		cost.add(new VictoryPoints(0, 1));
		
		CardCostHandler.handle(cost, michele, cardType);
		
		assertEquals(5, cost.get(0).getAmount());
		assertEquals(0, cost.get(1).getAmount());
		assertEquals(2, cost.get(2).getAmount());
	}

	@Test
	public void multTest() {
		Player michele = new Player("Michele", Color.BLUE);
		DevelopmentCardType cardType = DevelopmentCardType.venture;
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new Coins(1, 1));
		resources.add(new MilitaryPoints(-3, 1));
		MultiplyCardCostBonus multiplyCardCostBonus = new MultiplyCardCostBonus(cardType, resources);
		
		assertEquals("Multiply venture cards' cost for: \n1 Coins\n-3 Military Points\n", multiplyCardCostBonus.getDescription());
		
		multiplyCardCostBonus.getPermanentBonus(michele);
		
		assertEquals(multiplyCardCostBonus, michele.getPersonalBoard().getPermanentBonus().get(0));
		
		resources.add(new VictoryPoints(2, 1));

		MultiplyCardCostBonus multiplyCardCostBonus2 = new MultiplyCardCostBonus(cardType, resources);
		assertEquals(resources, multiplyCardCostBonus2.getResources());
		
		multiplyCardCostBonus2.getPermanentBonus(michele);

		assertNotEquals(multiplyCardCostBonus2, michele.getPersonalBoard().getPermanentBonus().get(0));
		
		MultiplyCardCostBonus playerBonus = (MultiplyCardCostBonus) michele.getPersonalBoard().getPermanentBonus().get(0);
		ArrayList<Resource> playerResource = playerBonus.getResources();
		assertEquals(2, playerResource.get(0).getAmount());
		assertEquals(-6, playerResource.get(1).getAmount());
		assertEquals(2, playerResource.get(2).getAmount());
		
		assertEquals("MultiplyCardCostBonus", multiplyCardCostBonus.getSubsubtype());

		MultiplyCardCostBonus multiplyCardCostBonus3 = new MultiplyCardCostBonus(DevelopmentCardType.character, resources);
		
		multiplyCardCostBonus3.getPermanentBonus(michele);
		
		assertEquals(multiplyCardCostBonus3, michele.getPersonalBoard().getPermanentBonus().get(1));
	
		ArrayList<Resource> cost = new ArrayList<>();
		cost.add(new Coins(3, 1));
		cost.add(new MilitaryPoints(2, 1));
		cost.add(new VictoryPoints(0, 1));
		
		CardCostHandler.handle(cost, michele, cardType);
		
		assertEquals(6, cost.get(0).getAmount());
		assertEquals(-12, cost.get(1).getAmount());
		assertEquals(0, cost.get(2).getAmount());
	}

}
