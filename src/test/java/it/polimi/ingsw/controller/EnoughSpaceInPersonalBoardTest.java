package it.polimi.ingsw.controller;

import org.junit.Test;

import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class EnoughSpaceInPersonalBoardTest {

	@Test
	public void test() {
		Player player = new Player("player", Color.BLUE);
		Dice dice = new Dice(DiceColour.Black);
		FamilyMember familyMember = new FamilyMember(dice, player);
		//TODO da cambiare il controller che prenda in ingresso il tipo e non la carta
		
	}

}
