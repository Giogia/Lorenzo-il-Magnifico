package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.BOARD.HarvestArea;
import it.polimi.ingsw.BOARD.Market;
import it.polimi.ingsw.CONTROLLER.ZoneAlreadyOccupiedController;
import it.polimi.ingsw.CONTROLLER.ZoneOccupiedBySameColorController;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class ZoneOccupiedBySameColorControllerTest {

	@Test
	public void test() {
		Player player = new Player("player", Color.BLUE);
		Dice dice1 = new Dice(DiceColour.Orange);
		FamilyMember familyMember1 = new FamilyMember(dice1, player);
		Dice dice2 = new Dice(DiceColour.Fake);
		FamilyMember familyMember2 = new FamilyMember(dice2, player);
		Dice dice3 = new Dice(DiceColour.Neutral);
		FamilyMember familyMember3 = new FamilyMember(dice3, player);
		HarvestArea harvestArea =new HarvestArea();
		harvestArea.getPosition(0).addFamilyMember(familyMember3);

		assertEquals(true, ZoneOccupiedBySameColorController.check(harvestArea, familyMember1));
		
		Dice dice4 = new Dice(DiceColour.White);
		FamilyMember familyMember4= new FamilyMember(dice4, player);
		harvestArea.getPosition(1).addFamilyMember(familyMember4);
		
		assertEquals(false, ZoneOccupiedBySameColorController.check(harvestArea, familyMember1));
		assertEquals(true, ZoneOccupiedBySameColorController.check(harvestArea, familyMember2));
	}

}
