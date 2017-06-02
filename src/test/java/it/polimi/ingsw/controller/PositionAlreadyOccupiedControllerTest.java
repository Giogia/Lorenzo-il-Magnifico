package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CONTROLLER.PositionAlreadyOccupiedController;
import it.polimi.ingsw.CONTROLLER.ResourceController;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class PositionAlreadyOccupiedControllerTest {

	@Test
	public void test() throws Exception{
		ArrayList<ImmediateBonus> boardBonus = new ArrayList<>();
		Position position = new Position(boardBonus, 4);
		
		assertEquals(true, PositionAlreadyOccupiedController.check(position));
		
		Player player = new Player("player", Color.BLUE);
		Dice dice = new Dice(DiceColour.Black);
		FamilyMember familyMember = new FamilyMember(dice, player);
		position.addFamilyMember(familyMember);
		
	
		try{
			PositionAlreadyOccupiedController.check(position);
		}
		catch(Exception exc){
			assertEquals(exc.getMessage(),"This position is already occupied");
		}
	}

}
