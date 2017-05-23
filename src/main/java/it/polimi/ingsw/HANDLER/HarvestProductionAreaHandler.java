package it.polimi.ingsw.HANDLER;
import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.CONTROLLER.*;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBonusTile;


public abstract class HarvestProductionAreaHandler {

	
	public static boolean handle(FamilyMember familyMember, Zone zone, Position position){
		if(!PositionAlreadyOccupiedController.check(position) &&
			!OccupiedYetBonusController.check(familyMember)){
			return false;
		}
		if(ZoneOccupiedBySameColorController.check(zone, familyMember)){
			if(FamilyMemberValueController.check(familyMember, position)){
				if(checkBonusTile(familyMember)){
					return true;
				}
			}
		}
		return false;
	}
	protected static boolean checkBonusTile(FamilyMember familyMember){
		PersonalBonusTile personalBonusTile = familyMember.getPlayer().getPersonalBoard().getPersonalBonusTile();
		return(familyMember.getValue()>= personalBonusTile.);//TODO da sistemare il fatto che chiami o harvest o production bonustile
	}
}