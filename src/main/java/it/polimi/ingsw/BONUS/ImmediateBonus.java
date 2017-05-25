package it.polimi.ingsw.BONUS;

import it.polimi.ingsw.GC_15.Player;

public abstract class ImmediateBonus implements Bonus {
	private String subtype1;
	
	public ImmediateBonus(String type) {
		subtype1 = type;
	}
	
	public String getSubtype1() {
		return subtype1;
	}
	
	public void getImmediateBonus(Player player){ 
		
	};
}
