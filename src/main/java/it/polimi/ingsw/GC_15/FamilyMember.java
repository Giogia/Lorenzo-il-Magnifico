package it.polimi.ingsw.GC_15;

public class FamilyMember {
	private Dice dice;
	private Player player;
	private int value;
	
	
	public Dice getDice() {
		return this.dice;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public FamilyMember(Dice dice, Player player) {
		this.dice=dice;
		this.player=player;
		this.value = dice.getValue();
	}
	
	public void addValue(int value) {
		this.value = this.value + value;
	}
	
	public void multValue(int value) {
		this.value= this.value * value;
	}

	public String getDescription() {
		String description = "Color: " + dice.getDiceColour().name() + "\n Value: " + value;
		return description;
	}
}
