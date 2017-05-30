package it.polimi.ingsw.CONTROLLER;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class CheckBonusTilerRequirementControllerTest {

	@Test
	public void checkTest() {
		
		Player player = new Player("player", Color.BLUE);
		Dice dice = new Dice(DiceColour.Black);
		FamilyMember familyMember = new FamilyMember(dice, player);
		Zone zone = new Tower(DevelopmentCardType.building);
		
		
	}

}
