package it.polimi.ingsw.handler;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BONUS.ADVANCED.LoseVictoryPointsPerCostBonus;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.CARD.Venture;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.HANDLER.ADVANCED.LoseVictoryPointsPerCostHandler;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class LoseVictoryPointsPerCostHandlerTest {

	@Test
	public void test() {
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new Coins(2, 1));
		LoseVictoryPointsPerCostBonus l1 = new LoseVictoryPointsPerCostBonus(DevelopmentCardType.character, resources);
		LoseVictoryPointsPerCostBonus l2 = new LoseVictoryPointsPerCostBonus(DevelopmentCardType.venture, resources);
		LoseVictoryPointsPerCostBonus l3 = new LoseVictoryPointsPerCostBonus(DevelopmentCardType.territory, resources);
		LoseVictoryPointsPerCostBonus l4 = new LoseVictoryPointsPerCostBonus(DevelopmentCardType.building, resources);
		Player player = new Player(null, Color.RED);
		player.getPersonalBoard().getPermanentBonus().add(l1);
		player.getPersonalBoard().getPermanentBonus().add(l2);
		player.getPersonalBoard().getPermanentBonus().add(l3);
		player.getPersonalBoard().getPermanentBonus().add(l4);
		
		ArrayList<Resource> cost = new ArrayList<>();
		cost.add(new Coins(2, 1));
		cost.add(new MilitaryPoints(1, 1));
		
		ArrayList<Resource> cost2 = new ArrayList<>();
		cost2.add(new MilitaryPoints(1, 1));
		
		Building building = new Building(null, 1, cost, 1, null, null);
		Character character = new Character(null, new Coins(1,1), 1, null, null);
		Venture venture = new Venture(null, 0, cost2, null, 1, null, null);
		Venture venture2 = new Venture(null, 0, cost, null, 1, null, null);
		Territory territory = new Territory(null, 1, 0, null, null);
		
		player.getPersonalBoard().getCardContainer(DevelopmentCardType.building).add(building);
		player.getPersonalBoard().getCardContainer(DevelopmentCardType.territory).add(territory);
		player.getPersonalBoard().getCardContainer(DevelopmentCardType.venture).add(venture);
		player.getPersonalBoard().getCardContainer(DevelopmentCardType.venture).add(venture2);
		player.getPersonalBoard().getCardContainer(DevelopmentCardType.character).add(character);
		
		LoseVictoryPointsPerCostHandler.handle(player);
		
		assertEquals(-2, player.getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
	}

}
