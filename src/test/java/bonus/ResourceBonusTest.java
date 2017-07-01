package bonus;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BONUS.AddResourceBonus;
import it.polimi.ingsw.BONUS.MultiplyResourceBonus;
import it.polimi.ingsw.BONUS.ResourcePerDevelopmentCardBonus;
import it.polimi.ingsw.BONUS.ResourcePerResourceBonus;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.FaithPoints;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class ResourceBonusTest {

	@Test
	public void addResourceBonusTest() {
		ArrayList<Resource> resourcesBonus = new ArrayList<>();
		resourcesBonus.add(new Coins(3, 1));
		resourcesBonus.add(new MilitaryPoints(-2, 1));
		resourcesBonus.add(new FaithPoints(0, 1));
		
		AddResourceBonus addResourceBonus = new AddResourceBonus(resourcesBonus);
		
		Player marco = new Player("Marco", Color.BLUE);
		marco.getPersonalBoard().getResource(ResourceType.coins).addAmount(2);
		marco.getPersonalBoard().getResource(ResourceType.militaryPoints).addAmount(1);
		marco.getPersonalBoard().getResource(ResourceType.faithPoints).addAmount(3);
		
		addResourceBonus.getImmediateBonus(marco);
		
		assertEquals(5, marco.getPersonalBoard().getResource(ResourceType.coins).getAmount());
		assertEquals(-1, marco.getPersonalBoard().getResource(ResourceType.militaryPoints).getAmount());
		assertEquals(3, marco.getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		
		assertEquals("Add to your resources: \n3 Coins\n-2 Military Points\n0 Faith Points\n", addResourceBonus.getDescription());
	
		assertEquals(resourcesBonus, addResourceBonus.getResources());
		assertEquals("addResourceBonus", addResourceBonus.getSubtype());
		
		AddResourceBonus cloned = (AddResourceBonus) addResourceBonus.createClone();
		assertEquals(cloned.getResources(), addResourceBonus.getResources());
		
		assertEquals("resourceBonus", addResourceBonus.getType());
		
	}
	
	@Test
	public void multResourceBonusTest() {
		ArrayList<Resource> resourcesBonus = new ArrayList<>();
		resourcesBonus.add(new Coins(3, 1));
		resourcesBonus.add(new MilitaryPoints(-2, 1));
		resourcesBonus.add(new FaithPoints(0, 1));
		
		MultiplyResourceBonus multiplyResourceBonus = new MultiplyResourceBonus(resourcesBonus);
		
		Player marco = new Player("Marco", Color.BLUE);
		marco.getPersonalBoard().getResource(ResourceType.coins).addAmount(2);
		marco.getPersonalBoard().getResource(ResourceType.militaryPoints).addAmount(1);
		marco.getPersonalBoard().getResource(ResourceType.faithPoints).addAmount(3);
		
		multiplyResourceBonus.getImmediateBonus(marco);
		
		assertEquals(6, marco.getPersonalBoard().getResource(ResourceType.coins).getAmount());
		assertEquals(-2, marco.getPersonalBoard().getResource(ResourceType.militaryPoints).getAmount());
		assertEquals(0, marco.getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		
		assertEquals("Moltiply your resources for a factor of: \n3 Coins\n-2 Military Points\n0 Faith Points\n", multiplyResourceBonus.getDescription());
	
		assertEquals(resourcesBonus, multiplyResourceBonus.getResources());
		assertEquals("multiplyResourceBonus", multiplyResourceBonus.getSubtype());
		
		MultiplyResourceBonus cloned = (MultiplyResourceBonus) multiplyResourceBonus.createClone();
		assertEquals(cloned.getResources(), multiplyResourceBonus.getResources());
		
	}

	@Test
	public void resourcePerResourceBonusTest() {
		ArrayList<Resource> resourcesBonus = new ArrayList<>();
		resourcesBonus.add(new Coins(3, 1));
		resourcesBonus.add(new MilitaryPoints(-2, 1));
		resourcesBonus.add(new FaithPoints(0, 1));
		
		ResourcePerResourceBonus resourcePerResourceBonus = new ResourcePerResourceBonus(resourcesBonus, new Coins(1, 1));
		
		Player marco = new Player("Marco", Color.BLUE);
		marco.getPersonalBoard().getResource(ResourceType.coins).addAmount(2);
		marco.getPersonalBoard().getResource(ResourceType.militaryPoints).addAmount(1);
		marco.getPersonalBoard().getResource(ResourceType.faithPoints).addAmount(3);
		
		assertEquals("For each 1 Coins you get: \nAdd to your resources: \n3 Coins\n-2 Military Points\n0 Faith Points\n", resourcePerResourceBonus.getDescription());
		
		resourcePerResourceBonus.getImmediateBonus(marco);
		
		assertEquals(8, marco.getPersonalBoard().getResource(ResourceType.coins).getAmount());
		assertEquals(-3, marco.getPersonalBoard().getResource(ResourceType.militaryPoints).getAmount());
		assertEquals(3, marco.getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		
		
		assertEquals(resourcesBonus, resourcePerResourceBonus.getResources());
		assertEquals("addResourceBonus", resourcePerResourceBonus.getSubtype());
		
		ResourcePerResourceBonus cloned = (ResourcePerResourceBonus) resourcePerResourceBonus.createClone();
		assertEquals(cloned.getResources(), resourcePerResourceBonus.getResources());
		
	}
	
	@Test
	public void resourcePerDevelopmentCardBonusTest() {
		ArrayList<Resource> resourcesBonus = new ArrayList<>();
		resourcesBonus.add(new Coins(3, 1));
		resourcesBonus.add(new MilitaryPoints(-2, 1));
		resourcesBonus.add(new FaithPoints(0, 1));
		
		ResourcePerDevelopmentCardBonus resourcePerDevelopmentCardBonus = new ResourcePerDevelopmentCardBonus(resourcesBonus, DevelopmentCardType.character);
		
		Player marco = new Player("Marco", Color.BLUE);
		marco.getPersonalBoard().getResource(ResourceType.coins).addAmount(2);
		marco.getPersonalBoard().getResource(ResourceType.militaryPoints).addAmount(1);
		marco.getPersonalBoard().getResource(ResourceType.faithPoints).addAmount(3);
		Character character = new Character("character", null, 1, null, null);
		marco.getPersonalBoard().getCardContainer(DevelopmentCardType.character).add(character);
		marco.getPersonalBoard().getCardContainer(DevelopmentCardType.character).add(character);
		marco.getPersonalBoard().getCardContainer(DevelopmentCardType.character).add(character);
		
		assertEquals("for Each character card you receive: \nAdd to your resources: \n3 Coins\n-2 Military Points\n0 Faith Points\n", resourcePerDevelopmentCardBonus.getDescription());
		
		resourcePerDevelopmentCardBonus.getImmediateBonus(marco);
		
		assertEquals(11, marco.getPersonalBoard().getResource(ResourceType.coins).getAmount());
		assertEquals(-5, marco.getPersonalBoard().getResource(ResourceType.militaryPoints).getAmount());
		assertEquals(3, marco.getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		
		
		assertEquals(resourcesBonus, resourcePerDevelopmentCardBonus.getResources());
		assertEquals("addResourceBonus", resourcePerDevelopmentCardBonus.getSubtype());
		
		ResourcePerDevelopmentCardBonus cloned = (ResourcePerDevelopmentCardBonus) resourcePerDevelopmentCardBonus.createClone();
		assertEquals(cloned.getResources(), resourcePerDevelopmentCardBonus.getResources());
		
	}
}
