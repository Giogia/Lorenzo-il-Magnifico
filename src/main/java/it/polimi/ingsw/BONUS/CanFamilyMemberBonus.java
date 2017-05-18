package it.polimi.ingsw.BONUS;

import java.util.HashMap;

import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.GC_15.Player;

public class CanFamilyMemberBonus extends PermanentBonus implements ImmediateBonus{
	private boolean occupiedYet;
	private HashMap<Zone, Boolean> canGoTo;
	
	public CanFamilyMemberBonus(boolean occupiedYet, HashMap<Zone, Boolean> canGoTo) {
		this.occupiedYet = occupiedYet;
		this.canGoTo = new HashMap<>();
		this.canGoTo.putAll(canGoTo);
	}
	
	@Override
	public void getImmediateBonus(Player player) {
		//TODO BISOGNA AGGIUNGERE UN CONTROLLORE O QUALCOSA DEL GENERE
	}

	public HashMap<Zone, Boolean> getCanGoTo() {
		return canGoTo;
	}
	
	public boolean getOccupiedYet() {
		return occupiedYet;
	}
}
