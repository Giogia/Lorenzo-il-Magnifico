package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import it.polimi.ingsw.BONUS.ADVANCED.CanFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.CONTROLLER.OccupiedYetBonusController;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class OccupiedYetBonusControllerTest {

	@Test
	public void test() {
		Player player = new Player("", Color.BLUE);
		Dice dice = new Dice(DiceColour.Black);
		FamilyMember familyMember = new FamilyMember(dice, player);
		
		assertFalse(OccupiedYetBonusController.check(familyMember));
		
		ArrayList<PermanentBonus> permanentBonus = familyMember.getPlayer().getPersonalBoard().getPermanentBonus();
		CanFamilyMemberBonus canFamilyMemberBonus = new CanFamilyMemberBonus(true, new HashMap<>());
		permanentBonus.add(canFamilyMemberBonus);
		
		assertTrue(OccupiedYetBonusController.check(familyMember));
	}

}
