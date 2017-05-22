package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.CanFamilyMemberBonus;
import it.polimi.ingsw.BONUS.PermanentBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;

public class OccupiedPositionBonusController implements Controller {
	
	public boolean check(FamilyMember familyMember){
		Player player = familyMember.getPlayer();
		ArrayList<Bonus> permanentBonus = player.getPersonalBoard().getPermanentBonus();
		for (Bonus bonus : permanentBonus) {
			if (bonus.getClass().equals()){
				
			}
		}
	}

}
