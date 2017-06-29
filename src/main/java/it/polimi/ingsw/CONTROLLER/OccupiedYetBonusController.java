package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.CanFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.GC_15.FamilyMember;

public class OccupiedYetBonusController {
	
	//check if player has the bonus that allow him to go in a position where is another familyMember yet.
	public static boolean check(FamilyMember familyMember){
		ArrayList<PermanentBonus> playerBonus = familyMember.getPlayer().getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof CanFamilyMemberBonus){
					return ((CanFamilyMemberBonus) permanentBonus).getOccupiedYet();
				}
			}
		}
		return false; 
	}
	
}
