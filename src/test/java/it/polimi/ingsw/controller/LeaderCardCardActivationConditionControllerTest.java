package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CONTROLLER.LeaderCardCardActivationConditionController;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class LeaderCardCardActivationConditionControllerTest {

	@Test (expected = MyException.class)
	public void test() throws MyException{
		Player marco = new Player("marco", Color.RED);
		HashMap<DevelopmentCardType, Integer> cardActivationCondition = new HashMap<>();
		
		assertTrue(LeaderCardCardActivationConditionController.check(marco, cardActivationCondition));
		
		cardActivationCondition.put(DevelopmentCardType.building, 4);
		
		assertFalse(LeaderCardCardActivationConditionController.check(marco, cardActivationCondition));
		
		marco.getPersonalBoard().getCardContainer(DevelopmentCardType.building).add(new Building("", 0, null, 0, null, null));
	
		assertFalse(LeaderCardCardActivationConditionController.check(marco, cardActivationCondition));
		
		for(int i = 0; i < 3; i++){
			marco.getPersonalBoard().getCardContainer(DevelopmentCardType.building).add(new Building("", 0, null, 0, null, null));
		}
		
		assertTrue(LeaderCardCardActivationConditionController.check(marco, cardActivationCondition));
	}	

}
