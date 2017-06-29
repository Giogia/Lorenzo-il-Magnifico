package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import it.polimi.ingsw.BONUS.ADVANCED.EndGameCardBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.BONUS.ADVANCED.TerritoryCardRequirementBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CONTROLLER.TerritoryCardRequirementController;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class TerritoryCardRequirementControllerTest {

	@Test
	public void test() {
		Player player = new Player("", Color.BLUE);
		
		assertTrue(TerritoryCardRequirementController.check(player));
		
		PermanentBonus bonus = new TerritoryCardRequirementBonus(false);
		//creating a useless bonus
		HashMap<DevelopmentCardType, Boolean> developmentCardTypes = new HashMap<>();
		PermanentBonus bonus1 = new EndGameCardBonus(developmentCardTypes);
		player.getPersonalBoard().addPermanentBonus(bonus);
		player.getPersonalBoard().addPermanentBonus(bonus1);
		
		assertFalse(TerritoryCardRequirementController.check(player));
		
		Player player2 = new Player("", Color.GREEN);
		PermanentBonus bonus2 = new TerritoryCardRequirementBonus(true);
		player.getPersonalBoard().addPermanentBonus(bonus);
		
		assertTrue(TerritoryCardRequirementController.check(player2));
	}

}
