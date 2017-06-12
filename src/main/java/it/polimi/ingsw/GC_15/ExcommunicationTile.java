package it.polimi.ingsw.GC_15;

import java.io.Serializable;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;

public class ExcommunicationTile implements Serializable{
	public final int period;
	public final PermanentBonus malus;
	
	public ExcommunicationTile(int period, PermanentBonus malus) {
		this.period=period;
		this.malus= malus;
	}
	
	public void giveMalus(Player player){
		malus.getPermanentBonus(player);
	}

	public String getDescription() {
		String description = "Period: " + period + "\nMalus: " + malus.getDescription() + "\n";
		return description;
	}
}
