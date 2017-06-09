package it.polimi.ingsw.GC_15;

import java.io.Serializable;

import it.polimi.ingsw.BONUS.Bonus;

public class ExcommunicationTile implements Serializable{
	public final int period;
	public final Bonus malus[];
	
	public ExcommunicationTile(int period, Bonus malus[]) {
		this.period=period;
		this.malus= malus;
	}
	
	public void giveMalus(Player player){
		//TODO PERMANENT
	}
}
