package bonus;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BONUS.AddFamilyMemberBonus;
import it.polimi.ingsw.BONUS.FamilyMemberValueBonus;
import it.polimi.ingsw.BONUS.MultiplyFamilyMemberBonus;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class FamilyMemberBonusTest {
	
	

	@Test
	public void addFamilyMemberTest() {
		ArrayList<FamilyMember> familyMembersBonus = new ArrayList<>();
		Dice orangeDiceBonus = new Dice(DiceColour.Orange);
		Dice whiteDiceBonus = new Dice(DiceColour.White);
		Dice blackDiceBonus = new Dice(DiceColour.Black);
		orangeDiceBonus.setValue(2);
		whiteDiceBonus.setValue(-3);
		blackDiceBonus.setValue(43);
		familyMembersBonus.add(new FamilyMember(blackDiceBonus, null));
		familyMembersBonus.add(new FamilyMember(orangeDiceBonus, null));
		familyMembersBonus.add(new FamilyMember(whiteDiceBonus, null));
		
		Player giovanni = new Player("Giovanni", Color.RED);
		Dice orangeDice = new Dice(DiceColour.Orange);
		Dice blackDice = new Dice(DiceColour.Black);
		Dice whiteDice = new Dice(DiceColour.White);
		Dice neutralDice = new Dice(DiceColour.Neutral);
		orangeDice.setValue(4);
		whiteDice.setValue(5);
		blackDice.setValue(1);
		ArrayList<FamilyMember> familyMembers = new ArrayList<>();
		familyMembers.add(new FamilyMember(orangeDice, giovanni));
		familyMembers.add(new FamilyMember(neutralDice, giovanni));
		familyMembers.add(new FamilyMember(blackDice, giovanni));
		familyMembers.add(new FamilyMember(whiteDice, giovanni));
		giovanni.setFamilyMember(familyMembers);
		
		AddFamilyMemberBonus addFamilyMemberBonus = new AddFamilyMemberBonus(familyMembersBonus);
		
		addFamilyMemberBonus.getImmediateBonus(giovanni);
		
		assertEquals(6, giovanni.getFamilyMembers().get(0).getValue());
		assertEquals(0, giovanni.getFamilyMembers().get(1).getValue());
		assertEquals(44, giovanni.getFamilyMembers().get(2).getValue());
		assertEquals(2, giovanni.getFamilyMembers().get(3).getValue());
		
		assertEquals("Add to: \nColor: Black\n Value: 43\nColor: Orange\n Value: 2\nColor: White\n Value: -3\n", addFamilyMemberBonus.getDescription());
	
		assertEquals(familyMembersBonus, addFamilyMemberBonus.getFamilyMembers());
		assertEquals("addFamilyMemberBonus", addFamilyMemberBonus.getSubtype());
	}
	
	@Test
	public void multFamilyMemberTest() {
		ArrayList<FamilyMember> familyMembersBonus = new ArrayList<>();
		Dice orangeDiceBonus = new Dice(DiceColour.Orange);
		Dice whiteDiceBonus = new Dice(DiceColour.White);
		Dice blackDiceBonus = new Dice(DiceColour.Black);
		orangeDiceBonus.setValue(2);
		whiteDiceBonus.setValue(-3);
		blackDiceBonus.setValue(43);
		familyMembersBonus.add(new FamilyMember(blackDiceBonus, null));
		familyMembersBonus.add(new FamilyMember(orangeDiceBonus, null));
		familyMembersBonus.add(new FamilyMember(whiteDiceBonus, null));
		
		Player giovanni = new Player("Giovanni", Color.RED);
		Dice orangeDice = new Dice(DiceColour.Orange);
		Dice blackDice = new Dice(DiceColour.Black);
		Dice whiteDice = new Dice(DiceColour.White);
		Dice neutralDice = new Dice(DiceColour.Neutral);
		orangeDice.setValue(4);
		whiteDice.setValue(5);
		blackDice.setValue(1);
		ArrayList<FamilyMember> familyMembers = new ArrayList<>();
		familyMembers.add(new FamilyMember(orangeDice, giovanni));
		familyMembers.add(new FamilyMember(neutralDice, giovanni));
		familyMembers.add(new FamilyMember(blackDice, giovanni));
		familyMembers.add(new FamilyMember(whiteDice, giovanni));
		giovanni.setFamilyMember(familyMembers);
		
		MultiplyFamilyMemberBonus multiplyFamilyMemberBonus = new MultiplyFamilyMemberBonus(familyMembersBonus);
		
		multiplyFamilyMemberBonus.getImmediateBonus(giovanni);
		
		assertEquals(8, giovanni.getFamilyMembers().get(0).getValue());
		assertEquals(0, giovanni.getFamilyMembers().get(1).getValue());
		assertEquals(43, giovanni.getFamilyMembers().get(2).getValue());
		assertEquals(-15, giovanni.getFamilyMembers().get(3).getValue());
		
		assertEquals("Moltiply the value to: \nColor: Black\n Value: 43\nColor: Orange\n Value: 2\nColor: White\n Value: -3\n", multiplyFamilyMemberBonus.getDescription());
	
		assertEquals(familyMembersBonus, multiplyFamilyMemberBonus.getFamilyMembers());
		assertEquals("multiplyFamilyMemberBonus", multiplyFamilyMemberBonus.getSubtype());
	}
	
	
	@Test
	public void setFamilyMemberTest() {
		ArrayList<FamilyMember> familyMembersBonus = new ArrayList<>();
		Dice orangeDiceBonus = new Dice(DiceColour.Orange);
		Dice whiteDiceBonus = new Dice(DiceColour.White);
		Dice blackDiceBonus = new Dice(DiceColour.Black);
		orangeDiceBonus.setValue(2);
		whiteDiceBonus.setValue(-3);
		blackDiceBonus.setValue(43);
		familyMembersBonus.add(new FamilyMember(blackDiceBonus, null));
		familyMembersBonus.add(new FamilyMember(orangeDiceBonus, null));
		familyMembersBonus.add(new FamilyMember(whiteDiceBonus, null));
		
		Player giovanni = new Player("Giovanni", Color.RED);
		Dice orangeDice = new Dice(DiceColour.Orange);
		Dice blackDice = new Dice(DiceColour.Black);
		Dice whiteDice = new Dice(DiceColour.White);
		Dice neutralDice = new Dice(DiceColour.Neutral);
		orangeDice.setValue(4);
		whiteDice.setValue(5);
		blackDice.setValue(1);
		ArrayList<FamilyMember> familyMembers = new ArrayList<>();
		familyMembers.add(new FamilyMember(orangeDice, giovanni));
		familyMembers.add(new FamilyMember(neutralDice, giovanni));
		familyMembers.add(new FamilyMember(blackDice, giovanni));
		familyMembers.add(new FamilyMember(whiteDice, giovanni));
		giovanni.setFamilyMember(familyMembers);

		FamilyMemberValueBonus familyMemberValueBonus = new FamilyMemberValueBonus(familyMembersBonus);
		
		familyMemberValueBonus.getImmediateBonus(giovanni);
		
		assertEquals(2, giovanni.getFamilyMembers().get(0).getValue());
		assertEquals(0, giovanni.getFamilyMembers().get(1).getValue());
		assertEquals(43, giovanni.getFamilyMembers().get(2).getValue());
		assertEquals(-3, giovanni.getFamilyMembers().get(3).getValue());
		
		assertEquals("Modify the value of your family members: \nColor: Black\n Value: 43\nColor: Orange\n Value: 2\nColor: White\n Value: -3\n", familyMemberValueBonus.getDescription());
	
		assertEquals(familyMembersBonus, familyMemberValueBonus.getFamilyMembers());
		assertEquals("familyMemberValueBonus", familyMemberValueBonus.getSubtype());
	}

}
