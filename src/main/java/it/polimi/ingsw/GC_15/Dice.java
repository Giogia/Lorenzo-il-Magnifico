package it.polimi.ingsw.GC_15;

import java.util.Random;

public class Dice {
	private int value;
	private DiceColour diceColour;

	public Dice(DiceColour diceColour) {
		this.value= 0;
		this.diceColour = diceColour;
	}
	
	public DiceColour getDiceColour() {
		return diceColour;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue() {
		Random r = new Random();
		value= (int) (r.nextInt(6) + 1);
	}
	
	public void setValue(int value){
		this.value = value;
	}
}
