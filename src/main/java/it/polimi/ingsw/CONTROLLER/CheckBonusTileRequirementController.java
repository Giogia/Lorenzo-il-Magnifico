package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBonusTile;

public class CheckBonusTileRequirementController implements Controller{

	public static boolean check(FamilyMember familyMember, Zone zone) throws Exception{
		PersonalBonusTile personalBonusTile = familyMember.getPlayer().getPersonalBoard().getPersonalBonusTile();
		if(familyMember.getValue()>= personalBonusTile.getCondition(zone)){
			return true;
		}
		throw new Exception("the value of the family member is not enough for the personal bonus tile!");
	}
}
