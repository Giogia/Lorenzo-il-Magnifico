package it.polimi.ingsw.GC_15;

import it.polimi.ingsw.BONUS.Bonus;

public class ExcommunicationTile {
	public final int period;
	public final Bonus malus[];
	
	public ExcommunicationTile(int period, Bonus malus[]) {
		this.period=period;
		this.malus= malus;
	}
	
	public void giveMalus(Player player){
		//TODO PERMANENT
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
