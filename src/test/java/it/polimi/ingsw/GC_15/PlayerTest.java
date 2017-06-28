package it.polimi.ingsw.GC_15;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class PlayerTest {
	
	@Test
	public void ServantsTest(){
		
		Player luca = new Player("luca", Color.BLUE);
		ArrayList<FamilyMember> familyMembers = new ArrayList<>();
		Dice dice = new Dice(DiceColour.Black);
		FamilyMember familyMember = new FamilyMember(dice, luca);
		familyMembers.add(0,familyMember);
		luca.getPersonalBoard().getResource(ResourceType.servants).addAmount(8);
		luca.useServants(5, familyMembers.get(0));
		
		assertEquals(5, familyMember.getValue());
		assertEquals(3, luca.getPersonalBoard().getResource(ResourceType.servants).getAmount());
	}

}
