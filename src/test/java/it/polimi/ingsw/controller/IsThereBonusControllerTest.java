package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.BONUS.CouncilPrivilegeBonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CONTROLLER.IsThereBonusController;

public class IsThereBonusControllerTest {

	@Test
	public void test() throws Exception {
		ArrayList<ImmediateBonus> boardBonus = new ArrayList<>();
		ImmediateBonus bonus = new CouncilPrivilegeBonus(2);
		DevelopmentCard developmentCard = null;
		TowerFloor towerFloor = new TowerFloor(boardBonus, 4);
		
		try{
		IsThereBonusController.check(towerFloor);
		}
		catch(Exception exc){
			assertEquals(exc.getMessage(),"there is no bonus in this position");
		}
		
		boardBonus.add(bonus);
		assertEquals(true, IsThereBonusController.check(towerFloor));
	}

}
