package it.polimi.ingsw.GC_15;

import java.io.Serializable;
import java.util.Random;

//model class of a dice
public class Dice implements Serializable{
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
		value= r.nextInt(6) + 1;
	}
	
	public void setValue(int value){
		this.value = value;
	}

	public String getDescription() {
		String description = diceColour.name() + " dice value: " + value + "\n";
		return description;
	}
}
