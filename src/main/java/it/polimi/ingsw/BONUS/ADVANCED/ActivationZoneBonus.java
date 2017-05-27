package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.HashMap;

import it.polimi.ingsw.BOARD.Tower;

public class ActivationZoneBonus extends PermanentBonus {
	private HashMap<Tower, Boolean> towers;
	private boolean councilPalace;
	
	public ActivationZoneBonus(HashMap<Tower, Boolean> towers, boolean councilPalace) {
		super("ActivationZoneBonus");
		this.towers = new HashMap<>();
		this.towers.putAll(towers);
		this.councilPalace = councilPalace;
	}
	
	public HashMap<Tower, Boolean> getTowers() {
		return towers;
	}
	
	public boolean getCouncilPalace(){
		return councilPalace;
	}

}
