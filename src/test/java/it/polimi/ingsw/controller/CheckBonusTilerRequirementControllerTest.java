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
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class CheckBonusTilerRequirementControllerTest {

	@Test(expected = MyException.class) 
	public void checkTest() throws Exception{
		
		Player player = new Player("player", Color.BLUE);
		Dice dice = new Dice(DiceColour.Black);//dice has value 0
		FamilyMember familyMember = new FamilyMember(dice, player);
		Zone zone1 = new HarvestArea();
		
		assertTrue(CheckBonusTileRequirementController.check(familyMember, zone1));
		
		familyMember.setValue(-2);
		Zone zone2 = new ProductionArea();
		assertEquals(new MyException("the value of the family member is not enough for the personal bonus tile!"), CheckBonusTileRequirementController.check(familyMember, zone2));
	}
}
