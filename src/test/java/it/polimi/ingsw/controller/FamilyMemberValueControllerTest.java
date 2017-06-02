package it.polimi.ingsw.controller;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CONTROLLER.FamilyMemberValueController;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class FamilyMemberValueControllerTest {

	@Test
	public void test() throws Exception {
		Player player = new Player("player", Color.BLUE);
		Dice dice = new Dice(DiceColour.Black);
		FamilyMember familyMember = new FamilyMember(dice, player);
		familyMember.setValue(5);
		ArrayList<ImmediateBonus> boardBonus = new ArrayList<>();
		Position position1 = new Position(boardBonus, 3);
		
		assertEquals(true, FamilyMemberValueController.check(familyMember, position1));
		
		Position position2 = new Position(boardBonus, 7);
		
		assertEquals(5,familyMember.getValue());
		assertEquals(7,position2.getDiceRequirement());
		
		try{
			FamilyMemberValueController.check(familyMember, position2);
		}
		catch(Exception exc){
			assertEquals(exc.getMessage(),"The Family Member Value is not enough!");
		}
	}

}
