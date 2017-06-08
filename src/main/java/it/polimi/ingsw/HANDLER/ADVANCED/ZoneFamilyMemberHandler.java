package it.polimi.ingsw.HANDLER.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PositionFamilyMemberBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;

public class ZoneFamilyMemberHandler{
	
	//Check if player has PositionFamilyMemberBonus, if so modify the value of the familyMember
	public static void handle(ActionZone actionZone, FamilyMember familyMember) throws MyException {
		try{
			ArrayList<PermanentBonus> playerBonus = familyMember.getPlayer().getPersonalBoard().getPermanentBonus();
			PositionFamilyMemberBonus positionFamilyMemberBonus = null;
			for (PermanentBonus permanentBonus : playerBonus) {
				positionFamilyMemberBonus = (PositionFamilyMemberBonus) controlBonus(positionFamilyMemberBonus, permanentBonus);
			}
			familyMember.addValue(positionFamilyMemberBonus.getValue(actionZone));
		} catch (Exception e){
		}
	}

	private static PositionFamilyMemberBonus controlBonus(PositionFamilyMemberBonus askedBonus, PermanentBonus permanentBonus) {
		if (permanentBonus instanceof PositionFamilyMemberBonus){
			askedBonus = (PositionFamilyMemberBonus) permanentBonus;
		}
		return askedBonus;
	}
	

}
