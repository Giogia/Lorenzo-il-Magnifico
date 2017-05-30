package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CONTROLLER.PositionWithoutDevelopmentCardController;
import it.polimi.ingsw.RESOURCE.Coins;

public class PositionWithoutDevelopmentCardControllerTest {

	@Test
	public void test() {
		Coins coins= new Coins(0, 1);
		ArrayList<ImmediateBonus> boardBonus = new ArrayList<>();
		ArrayList<ImmediateBonus> cardBonus1 = new ArrayList<>();
		ArrayList<Bonus> cardBonus2 = new ArrayList<>();
		
		DevelopmentCard developmentCard1 = null;
		TowerFloor towerFloor1 = new TowerFloor(boardBonus, 4, developmentCard1);
		
		DevelopmentCard developmentCard2 = new Character("lorenzo", coins, 1, cardBonus1, cardBonus2);
		TowerFloor towerFloor2 = new TowerFloor(boardBonus, 4, developmentCard2);
		
		assertEquals(false, PositionWithoutDevelopmentCardController.check(towerFloor1));
		
		
		assertEquals(true, PositionWithoutDevelopmentCardController.check(towerFloor2));
	}

}
