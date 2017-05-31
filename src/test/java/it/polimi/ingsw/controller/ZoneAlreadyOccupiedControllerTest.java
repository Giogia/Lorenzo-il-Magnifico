package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Market;
import it.polimi.ingsw.CONTROLLER.ZoneAlreadyOccupiedController;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class ZoneAlreadyOccupiedControllerTest {

	@Test
	public void test() {
		Player player = new Player("player", Color.BLUE);
		Dice dice = new Dice(DiceColour.Black);
		FamilyMember familyMember = new FamilyMember(dice, player);
		Market market =new Market(4);
		
		assertEquals(true, ZoneAlreadyOccupiedController.check(market));
		market.getPosition(0).addFamilyMember(familyMember);
		
		assertEquals(false, ZoneAlreadyOccupiedController.check(market));
	}

}
