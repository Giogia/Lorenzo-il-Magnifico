package it.polimi.ingsw.GC_15;

import java.io.Serializable;
import java.util.Random;

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
	
	public boolean setValue(int value){
		if(value>=0 && value<=7){
			this.value = value;
			return true;
		}
		return false;
	}

	public String getDescription() {
		String description = diceColour.name() + " dice value: " + value + "\n";
		return description;
	}
}
