package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.PersonalBonusTile;

public class CheckBonusTileRequirementController implements Controller{
	//check if the family member value is greater than condition of zone (harvest or production)
	//in personal bonus tile of player 
	public static boolean check(FamilyMember familyMember, Zone zone) throws MyException{
		PersonalBonusTile personalBonusTile = familyMember.getPlayer().getPersonalBoard().getPersonalBonusTile();
		if(familyMember.getValue()>= personalBonusTile.getCondition(zone)){
			return true;
		}
		throw new MyException("the value of the family member is not enough for the personal bonus tile!");
	}
}
