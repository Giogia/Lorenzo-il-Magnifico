package it.polimi.ingsw.GC_15;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {

	@Test
	public void setValuetest() {
		
		Dice d = new Dice(DiceColour.Black);
		d.setValue();
	
		assertEquals(true,(d.getValue()>=0)&&(d.getValue()<=7));
	}

}
