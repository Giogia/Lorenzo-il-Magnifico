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
	public void setGetTest() {
		
		Player gigi = new Player("gigi", Color.YELLOW);
		ArrayList<FamilyMember> familyMembers = new ArrayList<>();
		
		gigi.setFamilyMember(familyMembers);
		
		assertEquals(familyMembers, gigi.getFamilyMembers());
	}
	
	@Test
	public void ColorTest() {
		
		Player marco = new Player("gigi", Color.RED);
		
		assertEquals(Color.RED, marco.getColor());
	}
	
	
	@Test
	public void setFamilyMemberPositionTest() {
		
		Player gigi = new Player("gigi", Color.GREEN);
		ArrayList<FamilyMember> familyMembers = new ArrayList<>();
		Dice dice = new Dice(DiceColour.Black);
		FamilyMember familyMember = new FamilyMember(dice, gigi);
		ArrayList<ImmediateBonus> boardBonus = new ArrayList<>();
		Position position = new Position(boardBonus, 0);
		
		familyMembers.add(0,familyMember);
		gigi.setFamilyMemberPosition(familyMembers.get(0), position);
		
		assertEquals(familyMembers.get(0), position.getFamilyMember(0));
	}
	
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
