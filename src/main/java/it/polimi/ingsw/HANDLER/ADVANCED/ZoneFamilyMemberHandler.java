package it.polimi.ingsw.HANDLER.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PositionFamilyMemberBonus;
import it.polimi.ingsw.GC_15.FamilyMember;

public class ZoneFamilyMemberHandler{
	
	//Check if player has PositionFamilyMemberBonus, if so modify the value of the familyMember
	public static boolean handle(ActionZone actionZone, FamilyMember familyMember) throws Exception {
		try{
			ArrayList<PermanentBonus> playerBonus = familyMember.getPlayer().getPersonalBoard().getPermanentBonus();
			PositionFamilyMemberBonus positionFamilyMemberBonus = null;
			for (PermanentBonus permanentBonus : playerBonus) {
				positionFamilyMemberBonus = controlBonus(positionFamilyMemberBonus, permanentBonus);
			}
			familyMember.addValue(positionFamilyMemberBonus.getValue(actionZone));
			return true;
		} catch (Exception e){
			return false;
		}
	}

	private static PositionFamilyMemberBonus controlBonus(PositionFamilyMemberBonus positionFamilyMemberBonus, PermanentBonus permanentBonus) {
		if (permanentBonus instanceof PositionFamilyMemberBonus){
			positionFamilyMemberBonus = (PositionFamilyMemberBonus) permanentBonus;
		}
		return positionFamilyMemberBonus;
	}

}
