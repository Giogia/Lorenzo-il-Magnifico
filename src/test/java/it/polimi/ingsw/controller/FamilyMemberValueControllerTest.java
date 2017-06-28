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
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class FamilyMemberValueControllerTest {

	@Test (expected = MyException.class)
	public void test() throws Exception {
		Player player = new Player("player", Color.BLUE);
		Dice dice = new Dice(DiceColour.Black);
		FamilyMember familyMember = new FamilyMember(dice, player);
		familyMember.setValue(5);
		Position position1 = new Position(null, 3);
		
		assertEquals(true, FamilyMemberValueController.check(familyMember, position1));
		
		Position position2 = new Position(null, 7);
		
		assertEquals(new MyException("The Family Member Value is not enough!"), FamilyMemberValueController.check(familyMember, position2));
	}

}
