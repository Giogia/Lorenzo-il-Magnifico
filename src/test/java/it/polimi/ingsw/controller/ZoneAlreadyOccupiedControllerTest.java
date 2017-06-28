package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Market;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.CONTROLLER.ZoneAlreadyOccupiedController;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class ZoneAlreadyOccupiedControllerTest {

	@Test
	public void test() throws Exception{
		Market market =new Market();
		Position[] positions = new Position[4];
		
		for (int i = 0; i < positions.length; i++){
			positions[i] = new Position(null, 1);
		}
		
		market.setPosition(positions);
		
		assertTrue(ZoneAlreadyOccupiedController.check(market));
		
		Player player = new Player("player", Color.BLUE);
		Dice dice = new Dice(DiceColour.Black);
		FamilyMember familyMember = new FamilyMember(dice, player);
		market.getPosition(0).addFamilyMember(familyMember);
		
		assertFalse(ZoneAlreadyOccupiedController.check(market));
	}
}
