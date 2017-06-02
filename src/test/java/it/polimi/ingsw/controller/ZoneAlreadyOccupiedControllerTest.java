package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Market;
import it.polimi.ingsw.CONTROLLER.PositionAlreadyOccupiedController;
import it.polimi.ingsw.CONTROLLER.ZoneAlreadyOccupiedController;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class ZoneAlreadyOccupiedControllerTest {

	@Test
	public void test() throws Exception{
		Player player = new Player("player", Color.BLUE);
		Dice dice = new Dice(DiceColour.Black);
		FamilyMember familyMember = new FamilyMember(dice, player);
		Market market =new Market();
		
		assertEquals(true, ZoneAlreadyOccupiedController.check(market));
		market.getPosition(0).addFamilyMember(familyMember);
		
		
		try{
			ZoneAlreadyOccupiedController.check(market);
		}
		catch(Exception exc){
			assertEquals(exc.getMessage(),"This zone is already occupied");
		}
	}

}//TODO sistemare NullPointerException
