package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CONTROLLER.EnoughSpaceInPersonalBoard;
import it.polimi.ingsw.GC_15.*;
import it.polimi.ingsw.GC_15.Player.Color;

public class EnoughSpaceInPersonalBoardTest {

	@Test (expected = MyException.class)
	public void test() throws MyException {
		Player player = new Player("player", Color.BLUE);
		Dice dice = new Dice(DiceColour.Black);
		FamilyMember familyMember = new FamilyMember(dice, player);
		Building card = new Building("prova", 1, null, 1, null, null);
		
		assertTrue(EnoughSpaceInPersonalBoard.check(familyMember,(DevelopmentCard) card));
		
		for(int i = 0; i < 7; i++){//now in building container there are 6 building card
			player.getPersonalBoard().getCardContainer(DevelopmentCardType.building).add(card);
		}
		
		//-> expected MyException
		assertEquals(new MyException("The Personal board is full"), EnoughSpaceInPersonalBoard.check(familyMember, card));
	}

}
