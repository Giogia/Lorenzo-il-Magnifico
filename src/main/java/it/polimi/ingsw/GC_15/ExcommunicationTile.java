package it.polimi.ingsw.GC_15;

import it.polimi.ingsw.BONUS.Bonus;

public class ExcommunicationTile {
	public final int period;
	private Bonus malus[];
	
	public ExcommunicationTile(int period, Bonus malus[]) {
		this.period=period;
		this.malus= malus;
	}
	
	public void giveMalus(Player player){
		//TODO
	}
}