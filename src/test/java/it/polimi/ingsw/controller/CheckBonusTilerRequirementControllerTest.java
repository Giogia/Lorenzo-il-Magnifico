package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.BOARD.HarvestArea;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.CONTROLLER.CheckBonusTileRequirementController;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class CheckBonusTilerRequirementControllerTest {

	@Test
	public void checkTest() throws Exception{
		
		Game game = new Game();
		
		Player player = new Player("player", Color.BLUE);
		Dice dice = new Dice(DiceColour.Black);
		FamilyMember familyMember = new FamilyMember(dice, player);
		Zone zone1 = new HarvestArea();
		
		
		assertEquals(true, CheckBonusTileRequirementController.check(familyMember, zone1));
		
		familyMember.setValue(-2);
		Zone zone2 = new ProductionArea();
		
		assertEquals(false, CheckBonusTileRequirementController.check(familyMember, zone2));
	}

}//TODO sistemare NullPointerException
