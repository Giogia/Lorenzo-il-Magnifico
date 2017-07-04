package bonus;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


import it.polimi.ingsw.BONUS.ADVANCED.PermanentAddFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentMultFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentValueFamilyMemberBonus;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.HANDLER.ADVANCED.PermanentFamilyMemberBonusHandler;

public class PermanentFamilyMemberBonusTest {

	@Test
	public void addTest() {
		Player giacomo = new Player(null, Color.BLUE);
		
		ArrayList<FamilyMember> bonusFamilyMembers = new ArrayList<>();
		Dice orangeDice = new Dice(DiceColour.Orange);
		orangeDice.setValue(5);
		Dice whiteDice = new Dice(DiceColour.White);
		whiteDice.setValue(-3);
		FamilyMember orange = new FamilyMember(orangeDice, null);
		FamilyMember white = new FamilyMember(whiteDice, null);
		bonusFamilyMembers.add(orange);
		bonusFamilyMembers.add(white);
		
		PermanentAddFamilyMemberBonus permanentAddFamilyMemberBonus = new PermanentAddFamilyMemberBonus(bonusFamilyMembers);
		
		assertEquals("PermanentAddFamilyMemberBonus", permanentAddFamilyMemberBonus.getSubsubtype());
		assertEquals("increase permanently of: \n5the value of your Orange family member \n-3the value of your White family member \n", permanentAddFamilyMemberBonus.getDescription());
		
		permanentAddFamilyMemberBonus.getPermanentBonus(giacomo);
		
		assertEquals(permanentAddFamilyMemberBonus, giacomo.getPersonalBoard().getPermanentBonus().get(0));
		
		ArrayList<FamilyMember> familyMembers = new ArrayList<>();
		Dice whiteDice2 = new Dice(DiceColour.White);
		whiteDice2.setValue(2);
		Dice blackDice = new Dice(DiceColour.Black);
		blackDice.setValue(26);
		FamilyMember white2 = new FamilyMember(whiteDice2, null);
		FamilyMember black = new FamilyMember(blackDice, null);
		familyMembers.add(white2);
		familyMembers.add(black);
		
		PermanentAddFamilyMemberBonus permanentAddFamilyMemberBonus2 = new PermanentAddFamilyMemberBonus(familyMembers);
		
		permanentAddFamilyMemberBonus2.getPermanentBonus(giacomo);
		
		PermanentAddFamilyMemberBonus playerBonus = (PermanentAddFamilyMemberBonus) giacomo.getPersonalBoard().getPermanentBonus().get(0);
		
		assertEquals(5, playerBonus.getFamilyMembers().get(0).getValue());
		assertEquals(-1, playerBonus.getFamilyMembers().get(1).getValue());
		assertEquals(26, playerBonus.getFamilyMembers().get(2).getValue());
		
		ArrayList<FamilyMember> playerFamilyMembers = new ArrayList<>();
		Dice o = new Dice(DiceColour.Orange);
		Dice w = new Dice(DiceColour.White);
		Dice b = new Dice(DiceColour.Black);
		Dice n = new Dice(DiceColour.Neutral);
		o.setValue(4);
		w.setValue(3);
		b.setValue(6);
		playerFamilyMembers.add(new FamilyMember(n, giacomo));
		playerFamilyMembers.add(new FamilyMember(b, giacomo));
		playerFamilyMembers.add(new FamilyMember(o, giacomo));
		playerFamilyMembers.add(new FamilyMember(w, giacomo));
		
		giacomo.setFamilyMember(playerFamilyMembers);
		
		PermanentFamilyMemberBonusHandler.handle(giacomo);
		assertEquals(9, playerFamilyMembers.get(2).getValue());
		assertEquals(32, playerFamilyMembers.get(1).getValue());
		assertEquals(2, playerFamilyMembers.get(3).getValue());
		assertEquals(0, playerFamilyMembers.get(0).getValue());
	}
	
	@Test
	public void multTest() {
		Player giacomo = new Player(null, Color.BLUE);
		
		ArrayList<FamilyMember> bonusFamilyMembers = new ArrayList<>();
		Dice orangeDice = new Dice(DiceColour.Orange);
		orangeDice.setValue(5);
		Dice whiteDice = new Dice(DiceColour.White);
		whiteDice.setValue(-3);
		FamilyMember orange = new FamilyMember(orangeDice, null);
		FamilyMember white = new FamilyMember(whiteDice, null);
		bonusFamilyMembers.add(orange);
		bonusFamilyMembers.add(white);
		
		PermanentMultFamilyMemberBonus permanentMultFamilyMemberBonus = new PermanentMultFamilyMemberBonus(bonusFamilyMembers);
		
		assertEquals("PermanentMultFamilyMemberBonus", permanentMultFamilyMemberBonus.getSubsubtype());
		assertEquals("Mult permanently for: \n5the value of your Orange family member \n-3the value of your White family member \n", permanentMultFamilyMemberBonus.getDescription());
		
		permanentMultFamilyMemberBonus.getPermanentBonus(giacomo);
		
		assertEquals(permanentMultFamilyMemberBonus, giacomo.getPersonalBoard().getPermanentBonus().get(0));
		
		ArrayList<FamilyMember> familyMembers = new ArrayList<>();
		Dice whiteDice2 = new Dice(DiceColour.White);
		whiteDice2.setValue(2);
		Dice blackDice = new Dice(DiceColour.Black);
		blackDice.setValue(26);
		FamilyMember white2 = new FamilyMember(whiteDice2, null);
		FamilyMember black = new FamilyMember(blackDice, null);
		familyMembers.add(white2);
		familyMembers.add(black);
		
		PermanentMultFamilyMemberBonus permanentMultFamilyMemberBonus2 = new PermanentMultFamilyMemberBonus(familyMembers);
		
		permanentMultFamilyMemberBonus2.getPermanentBonus(giacomo);
		
		PermanentMultFamilyMemberBonus playerBonus = (PermanentMultFamilyMemberBonus) giacomo.getPersonalBoard().getPermanentBonus().get(0);
		
		assertEquals(5, playerBonus.getFamilyMembers().get(0).getValue());
		assertEquals(-6, playerBonus.getFamilyMembers().get(1).getValue());
		assertEquals(26, playerBonus.getFamilyMembers().get(2).getValue());
		
		ArrayList<FamilyMember> playerFamilyMembers = new ArrayList<>();
		Dice o = new Dice(DiceColour.Orange);
		Dice w = new Dice(DiceColour.White);
		Dice b = new Dice(DiceColour.Black);
		Dice n = new Dice(DiceColour.Neutral);
		o.setValue(4);
		w.setValue(3);
		b.setValue(6);
		playerFamilyMembers.add(new FamilyMember(n, giacomo));
		playerFamilyMembers.add(new FamilyMember(b, giacomo));
		playerFamilyMembers.add(new FamilyMember(o, giacomo));
		playerFamilyMembers.add(new FamilyMember(w, giacomo));

		giacomo.setFamilyMember(playerFamilyMembers);
		
		PermanentFamilyMemberBonusHandler.handle(giacomo);
		assertEquals(20, playerFamilyMembers.get(2).getValue());
		assertEquals(156, playerFamilyMembers.get(1).getValue());
		assertEquals(-18, playerFamilyMembers.get(3).getValue());
		assertEquals(0, playerFamilyMembers.get(0).getValue());
	}
	
	@Test
	public void valueTest() {
		Player giacomo = new Player(null, Color.BLUE);
		
		ArrayList<FamilyMember> bonusFamilyMembers = new ArrayList<>();
		Dice orangeDice = new Dice(DiceColour.Orange);
		orangeDice.setValue(5);
		Dice whiteDice = new Dice(DiceColour.White);
		whiteDice.setValue(-3);
		FamilyMember orange = new FamilyMember(orangeDice, null);
		FamilyMember white = new FamilyMember(whiteDice, null);
		bonusFamilyMembers.add(orange);
		bonusFamilyMembers.add(white);
		
		PermanentValueFamilyMemberBonus permanentValueFamilyMemberBonus = new PermanentValueFamilyMemberBonus(bonusFamilyMembers);
		
		assertEquals("PermanentValueFamilyMemberBonus", permanentValueFamilyMemberBonus.getSubsubtype());
		assertEquals("Set permanently at: \n5the value of your Orange family member \n-3the value of your White family member \n", permanentValueFamilyMemberBonus.getDescription());
		
		permanentValueFamilyMemberBonus.getPermanentBonus(giacomo);
		
		assertEquals(permanentValueFamilyMemberBonus, giacomo.getPersonalBoard().getPermanentBonus().get(0));
		
		ArrayList<FamilyMember> familyMembers = new ArrayList<>();
		Dice whiteDice2 = new Dice(DiceColour.White);
		whiteDice2.setValue(2);
		Dice blackDice = new Dice(DiceColour.Black);
		blackDice.setValue(26);
		FamilyMember white2 = new FamilyMember(whiteDice2, null);
		FamilyMember black = new FamilyMember(blackDice, null);
		familyMembers.add(white2);
		familyMembers.add(black);
		
		PermanentValueFamilyMemberBonus permanentValueFamilyMemberBonus2 = new PermanentValueFamilyMemberBonus(familyMembers);
		
		permanentValueFamilyMemberBonus2.getPermanentBonus(giacomo);
		
		PermanentValueFamilyMemberBonus playerBonus = (PermanentValueFamilyMemberBonus) giacomo.getPersonalBoard().getPermanentBonus().get(0);
		
		assertEquals(5, playerBonus.getFamilyMembers().get(0).getValue());
		assertEquals(2, playerBonus.getFamilyMembers().get(1).getValue());
		assertEquals(26, playerBonus.getFamilyMembers().get(2).getValue());
		
		ArrayList<FamilyMember> playerFamilyMembers = new ArrayList<>();
		Dice o = new Dice(DiceColour.Orange);
		Dice w = new Dice(DiceColour.White);
		Dice b = new Dice(DiceColour.Black);
		Dice n = new Dice(DiceColour.Neutral);
		o.setValue(4);
		w.setValue(3);
		b.setValue(6);
		playerFamilyMembers.add(new FamilyMember(n, giacomo));
		playerFamilyMembers.add(new FamilyMember(b, giacomo));
		playerFamilyMembers.add(new FamilyMember(o, giacomo));
		playerFamilyMembers.add(new FamilyMember(w, giacomo));

		giacomo.setFamilyMember(playerFamilyMembers);
		
		PermanentFamilyMemberBonusHandler.handle(giacomo);
		assertEquals(5, playerFamilyMembers.get(2).getValue());
		assertEquals(26, playerFamilyMembers.get(1).getValue());
		assertEquals(2, playerFamilyMembers.get(3).getValue());
		assertEquals(0, playerFamilyMembers.get(0).getValue());
	}
	


}
