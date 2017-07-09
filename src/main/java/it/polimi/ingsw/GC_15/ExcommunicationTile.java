package it.polimi.ingsw.GC_15;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;

//model class of excommunication tile
public class ExcommunicationTile implements Serializable{
	private int id;
	public final int period;
	public final ArrayList<PermanentBonus> malus;

	public ExcommunicationTile(int id, int period, ArrayList<PermanentBonus> malus) {
		this.id = id;
		this.period=period;
		this.malus= malus;
	}

	public int getId() {
		return id;
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
		StringBuilder description = new StringBuilder();
		description.append("Period: " + period + "\n");
		if (malus != null){
			description.append("Malus: \n");
			for (PermanentBonus permanentBonus : malus) {
				description.append(permanentBonus.getDescription() + "\n");
			}
		}
		return description.toString();
	}
}
