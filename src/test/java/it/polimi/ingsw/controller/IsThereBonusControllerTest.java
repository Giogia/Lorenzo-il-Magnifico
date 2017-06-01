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
	public void test() {
		ArrayList<ImmediateBonus> boardBonus = new ArrayList<>();
		ImmediateBonus bonus = new CouncilPrivilegeBonus(2);
		DevelopmentCard developmentCard = null;
		TowerFloor towerFloor = new TowerFloor(boardBonus, 4);
		
		assertEquals(false, IsThereBonusController.check(towerFloor));

		boardBonus.add(bonus);
		assertEquals(true, IsThereBonusController.check(towerFloor));
	}

}
