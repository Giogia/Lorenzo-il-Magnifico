package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.HashMap;

import it.polimi.ingsw.BOARD.Zone;

public class CanFamilyMemberBonus extends PermanentBonus{
	//di default false: se è occupato il familyMember non può andarci
	private boolean occupiedYet;
	//particolare scomunica: di default true
	private HashMap<Zone, Boolean> canGoTo;
	
	public CanFamilyMemberBonus(boolean occupiedYet, HashMap<Zone, Boolean> canGoTo) {
		super("CanFamilyMemberBonus");
		this.occupiedYet = occupiedYet;
		this.canGoTo = new HashMap<>();
		this.canGoTo.putAll(canGoTo);
	}
	

	public HashMap<Zone, Boolean> getCanGoTo() {
		return canGoTo;
	}
	
	public boolean getOccupiedYet() {
		return occupiedYet;
	}
}
