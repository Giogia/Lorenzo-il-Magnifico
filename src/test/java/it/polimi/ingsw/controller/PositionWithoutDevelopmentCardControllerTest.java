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

public class PositionWithoutDevelopmentCardControllerTest{

	@Test
	public void test() throws Exception{
		Coins coins= new Coins(0, 1);
		ArrayList<ImmediateBonus> boardBonus = new ArrayList<>();
		ArrayList<ImmediateBonus> cardBonus1 = new ArrayList<>();
		ArrayList<Bonus> cardBonus2 = new ArrayList<>();
		TowerFloor towerFloor = new TowerFloor(boardBonus, 4);
		DevelopmentCard developmentCard = new Character("lorenzo", coins, 1, cardBonus1, cardBonus2);
	
		
		try{
			PositionWithoutDevelopmentCardController.check(towerFloor);
		}
		catch(Exception exc){
			assertEquals(exc.getMessage(),"Position without card!");
		}
		
		towerFloor.setDevelopmentCard(developmentCard);
		assertEquals(true, PositionWithoutDevelopmentCardController.check(towerFloor));
	}

}
