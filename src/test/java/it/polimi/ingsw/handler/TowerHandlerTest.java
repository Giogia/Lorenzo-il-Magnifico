package it.polimi.ingsw.handler;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.CONTROLLER.PositionWithoutDevelopmentCardController;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.HANDLER.TowerHandler;

public class TowerHandlerTest {

	ArrayList<Bonus> secondaryEffect = null;

	@Test
	public void test() throws Exception{
		Player player = new Player("name", Color.GREEN);
		Dice dice = new Dice(DiceColour.White);
		FamilyMember familyMember = new FamilyMember(dice, player);
		Tower tower = new Tower(DevelopmentCardType.territory);
		TowerFloor towerFloor = (TowerFloor)tower.getPosition(0);
		ArrayList<ImmediateBonus> immediateEffect =null;
		ArrayList<Bonus> secondaryEffect = null;
		Territory territory = new Territory("name", 1, 2, immediateEffect, secondaryEffect);
		towerFloor.setDevelopmentCard(territory);
		
		assertEquals(true, TowerHandler.handle(familyMember, tower, towerFloor));
		
	}

}//TODO capire come testare gli handler visto che richiedono scelte del umano
