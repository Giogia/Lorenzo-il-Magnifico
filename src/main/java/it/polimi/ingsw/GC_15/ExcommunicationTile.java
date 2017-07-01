package it.polimi.ingsw.GC_15;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;

public class ExcommunicationTile implements Serializable{
	public final int period;
	public final ArrayList<PermanentBonus> malus;
	
	public ExcommunicationTile(int period, ArrayList<PermanentBonus> malus) {
		this.period=period;
		this.malus= malus;
	}
	
	public int getPeriod() {
		return period;
	}
	
	public void giveMalus(Player player){
		for (PermanentBonus permanentBonus : malus) {
			permanentBonus.getPermanentBonus(player);
		}
	}

	public String getDescription() {
		String description = "Period: " + period + "\n";
		if (malus != null){
			description = description + "Malus: \n";
			for (PermanentBonus permanentBonus : malus) {
				description = description + permanentBonus.getDescription() + "\n";
			}
		}
		return description;
	}
}
