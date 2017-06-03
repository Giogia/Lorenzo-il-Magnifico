package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.CONTROLLER.ResourceController;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.FaithPoints;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.RESOURCE.Servants;
import it.polimi.ingsw.RESOURCE.Stones;
import it.polimi.ingsw.RESOURCE.VictoryPoints;
import it.polimi.ingsw.RESOURCE.Wood;

public class ResourceControllerTest {

	@Test
	public void test() throws Exception {
		Player player = new Player("player", Color.BLUE);
		player.getPersonalBoard().getResource(ResourceType.coins).addAmount(2);
		player.getPersonalBoard().getResource(ResourceType.wood).addAmount(2);
		player.getPersonalBoard().getResource(ResourceType.servants).addAmount(2);
		player.getPersonalBoard().getResource(ResourceType.stones).addAmount(2);
		player.getPersonalBoard().getResource(ResourceType.faithPoints).addAmount(2);
		player.getPersonalBoard().getResource(ResourceType.militaryPoints).addAmount(2);
		player.getPersonalBoard().getResource(ResourceType.victoryPoints).addAmount(2);

		
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new Coins(2,1));
		resources.add(new Wood(2,1));
		resources.add(new Stones(2,1));
		resources.add(new Servants(2,1));
		resources.add(new FaithPoints(2,1));
		resources.add(new MilitaryPoints(2,1));
		resources.add(new VictoryPoints(2,1));
		
		assertEquals(true,ResourceController.check(player, resources));
		
		player.getPersonalBoard().getResource(ResourceType.coins).addAmount(-2);
		player.getPersonalBoard().getResource(ResourceType.wood).addAmount(-2);
		player.getPersonalBoard().getResource(ResourceType.servants).addAmount(-2);
		player.getPersonalBoard().getResource(ResourceType.stones).addAmount(-2);
		player.getPersonalBoard().getResource(ResourceType.faithPoints).addAmount(-2);
		player.getPersonalBoard().getResource(ResourceType.militaryPoints).addAmount(-2);
		player.getPersonalBoard().getResource(ResourceType.victoryPoints).addAmount(-2);
		
		
		try{
			ResourceController.check(player, resources);
		}
		catch(Exception exc){
			assertEquals(exc.getMessage(),"The resources are not enough");
		}
	}

}
