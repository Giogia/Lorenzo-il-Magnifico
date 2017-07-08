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
			ArrayList<PermanentBonus> playerBonus = familyMember.getPlayer().getPersonalBoard().getPermanentBonus();
			if(playerBonus!=null && !playerBonus.isEmpty()){
				PositionFamilyMemberBonus positionFamilyMemberBonus = null;
				for (PermanentBonus permanentBonus : playerBonus) {
					positionFamilyMemberBonus = (PositionFamilyMemberBonus) controlBonus(positionFamilyMemberBonus, permanentBonus);
				}
				if (positionFamilyMemberBonus != null){
					familyMember.addValue(positionFamilyMemberBonus.getValue(actionZone, familyMember.getPlayer().getBoard()));
				}
			}
	}

	private static PositionFamilyMemberBonus controlBonus(PositionFamilyMemberBonus askedBonus, PermanentBonus permanentBonus) {
		PositionFamilyMemberBonus rightBonus = askedBonus;
		if (permanentBonus instanceof PositionFamilyMemberBonus){
			rightBonus = (PositionFamilyMemberBonus) permanentBonus;
		}
		return rightBonus;
	}
	

}
