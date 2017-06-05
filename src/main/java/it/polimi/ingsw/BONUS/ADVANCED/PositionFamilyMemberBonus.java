package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.HashMap;

import it.polimi.ingsw.BOARD.ActionZone;

public class PositionFamilyMemberBonus extends PermanentBonus{
	private HashMap<ActionZone, Integer> positionBonus;
	
	//If you go in some of this ActionZone your FamilyMember gain a value
	public PositionFamilyMemberBonus(HashMap<ActionZone, Integer> positionBonus){
		super("PositionFamilyMemberBonus");
		this.positionBonus = new HashMap<>();
		this.positionBonus.putAll(positionBonus);
	}
	
	public HashMap<ActionZone, Integer> getPositionBonus() {
		return positionBonus;
	}

}
