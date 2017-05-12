package it.polimi.ingsw.GC_15;

public class Dice {
	private int value;
	private Color color;
	public enum Color {NEUTRAL, WHITE, BLACK, ORANGE};

	public Dice(int value, Color color) {
		this.value= value;
		this.color = color;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue() {
		value= (int) (6 * Math.random());
	}
}
