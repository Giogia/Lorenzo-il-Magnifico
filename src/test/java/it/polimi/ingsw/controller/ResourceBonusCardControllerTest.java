package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BONUS.AddResourceBonus;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.CONTROLLER.ResourceBonusCardController;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.Resource;

public class ResourceBonusCardControllerTest {

	@Test
	public void test() {
		Player player = new Player("", Color.BLUE);
		ArrayList<Bonus> chosenEffects = new ArrayList<>();
		
		assertTrue(ResourceBonusCardController.check(chosenEffects, player, null));
	
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new Coins(-5, 1));
		AddResourceBonus bonus = new AddResourceBonus(resources);
		chosenEffects.add(bonus);
		ArrayList<Resource> playerResources = new ArrayList<>();
		playerResources.add(new Coins(2, 1));
		
		assertFalse(ResourceBonusCardController.check(chosenEffects, player, playerResources));
	}

}
