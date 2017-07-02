package it.polimi.ingsw.handler;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import it.polimi.ingsw.BONUS.AddResourceBonus;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentAddResourceBonus;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.OncePerRoundLeaderCard;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.HANDLER.ADVANCED.ActivateLeaderCardHandler;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class ActivateLeaderCardHandlerTest {

	@Test
	public void nullTest() throws IOException, MyException, TimeExpiredException {
		Player player = new Player(null, Color.YELLOW);
		OncePerRoundLeaderCard leaderCard = new OncePerRoundLeaderCard(null, new ArrayList<>(), null, null, 0);
		
		ActivateLeaderCardHandler.handle(player, leaderCard);
		
		assertEquals(0, player.getPersonalBoard().getResource(ResourceType.coins).getAmount());
		assertEquals(0, player.getPersonalBoard().getResource(ResourceType.wood).getAmount());
		assertEquals(0, player.getPersonalBoard().getResource(ResourceType.stones).getAmount());
		assertEquals(0, player.getPersonalBoard().getResource(ResourceType.servants).getAmount());
		assertEquals(0, player.getPersonalBoard().getResource(ResourceType.militaryPoints).getAmount());
		assertEquals(0, player.getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
		assertEquals(0, player.getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		
		assertTrue(player.getPersonalBoard().getPermanentBonus().isEmpty());
	}
	
	@Test
	public void test() throws IOException, MyException, TimeExpiredException {
		Player player = new Player(null, Color.YELLOW);
		ArrayList<Bonus> bonus = new ArrayList<>();
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new Coins(3, 1));
		bonus.add(new AddResourceBonus(resources));
		PermanentAddResourceBonus permanentAddResourceBonus = new PermanentAddResourceBonus(resources);
		bonus.add(permanentAddResourceBonus);
		
		player.getPersonalBoard().getResource(ResourceType.coins).addAmount(3);
		
		HashMap<DevelopmentCardType, Integer> hashMap = new HashMap<>();
		hashMap.put(DevelopmentCardType.character, 1);
		
		Character character = new Character(null, null, 1, null, null);
		player.getPersonalBoard().getCardContainer(DevelopmentCardType.character).add(character);
		
		OncePerRoundLeaderCard leaderCard = new OncePerRoundLeaderCard(null, bonus, resources, hashMap, 1);
		
		ActivateLeaderCardHandler.handle(player, leaderCard);
		
		assertEquals(6, player.getPersonalBoard().getResource(ResourceType.coins).getAmount());
		assertEquals(0, player.getPersonalBoard().getResource(ResourceType.wood).getAmount());
		assertEquals(0, player.getPersonalBoard().getResource(ResourceType.stones).getAmount());
		assertEquals(0, player.getPersonalBoard().getResource(ResourceType.servants).getAmount());
		assertEquals(0, player.getPersonalBoard().getResource(ResourceType.militaryPoints).getAmount());
		assertEquals(0, player.getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
		assertEquals(0, player.getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		
		assertEquals(permanentAddResourceBonus, player.getPersonalBoard().getPermanentBonus().get(0));
	}

}
