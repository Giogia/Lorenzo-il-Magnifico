package it.polimi.ingsw.GC_15;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {

	@Test
	public void getSetValueTest() {
		
		Dice d1 = new Dice(DiceColour.Black);
		Dice d2 = new Dice(DiceColour.Orange);
		
		d1.setValue(4);
		d2.setValue();

		assertEquals(4,d1.getValue());
		assertEquals(false,d2.setValue(-30));
		assertEquals(true,d2.setValue(0));
		assertEquals(true,d2.setValue(4));
		assertNotEquals(-3,d2.getValue());
		assertNotEquals(8,d2.getValue());
	}
	
	
	@Test
	public void getSetColorTest() {
		
		Dice d1 = new Dice(DiceColour.Black);
		Dice d2 = new Dice(DiceColour.Orange);
		Dice d3 = new Dice(DiceColour.Fake);
		
		
		
		assertEquals(DiceColour.Black,d1.getDiceColour());
		assertEquals(DiceColour.Orange,d2.getDiceColour());
		assertEquals(DiceColour.Fake,d3.getDiceColour());
	}
}
