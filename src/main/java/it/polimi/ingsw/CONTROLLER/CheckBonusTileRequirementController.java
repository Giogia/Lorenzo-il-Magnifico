package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBonusTile;

public class CheckBonusTileRequirementController implements Controller{

	public static boolean check(FamilyMember familyMember, Zone zone){
		PersonalBonusTile personalBonusTile = familyMember.getPlayer().getPersonalBoard().getPersonalBonusTile();
		return(familyMember.getValue()>= personalBonusTile.getCondition(zone));
	}
}
