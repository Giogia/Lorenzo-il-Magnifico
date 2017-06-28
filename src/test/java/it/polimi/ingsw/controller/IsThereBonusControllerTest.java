package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.BONUS.CouncilPrivilegeBonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CONTROLLER.IsThereBonusController;
import it.polimi.ingsw.GC_15.MyException;

public class IsThereBonusControllerTest {

	@Test
	public void test() throws Exception {
		ArrayList<ImmediateBonus> boardBonus = null;
		ImmediateBonus bonus = new CouncilPrivilegeBonus(2);
		TowerFloor towerFloor = new TowerFloor(boardBonus, 4);
		
		assertFalse(IsThereBonusController.check(towerFloor));
		
		boardBonus = new ArrayList<>();
		boardBonus.add(bonus);
		towerFloor = new TowerFloor(boardBonus, 0);
		assertTrue( IsThereBonusController.check(towerFloor));
	}
}
