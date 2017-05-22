package it.polimi.ingsw.GC_15;

public class Dice {
	private int value;
	private DiceColour diceColour;

	public Dice(int value, DiceColour diceColour) {
		this.value= value;
		this.diceColour = diceColour;
	}
	
	public DiceColour getDiceColour() {
		return diceColour;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue() {
		value= (int) (6 * Math.random());
	}
}
