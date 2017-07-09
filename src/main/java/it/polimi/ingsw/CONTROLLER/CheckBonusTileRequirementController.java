package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.PersonalBonusTile;

public class CheckBonusTileRequirementController implements Controller{
	
	//check if a player's family member's value is greater than zone's condition (harvest or production)
	//graphically it's in the personal bonus tile
	public static boolean check(FamilyMember familyMember, Zone zone) throws MyException{
		PersonalBonusTile personalBonusTile = familyMember.getPlayer().getPersonalBoard().getPersonalBonusTile();
		if(familyMember.getValue()>= personalBonusTile.getCondition(zone)){
			return true;
		}
		throw new MyException("the value of the family member is not enough for the personal bonus tile!");
	}
}
