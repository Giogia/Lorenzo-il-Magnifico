package it.polimi.ingsw.BONUS.ADVANCED;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.GC_15.Player;

public abstract class PermanentBonus extends Bonus{
	private String subtype;
	
	public PermanentBonus(String type) {
		super("PermanentBonus");
		subtype = type;
	}
	
	public String getSubtype() {
		return subtype;
	}

	public void getPermanentBonus(Player player){
		player.getPersonalBoard().addPermanentBonus(this);
	}
}
