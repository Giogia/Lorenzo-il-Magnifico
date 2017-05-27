package it.polimi.ingsw.HANDLER;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.CONTROLLER.FamilyMemberValueController;
import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.GC_15.FamilyMember;

public class CouncilPalaceHandler {
	
	public static boolean handle(FamilyMember familyMember, Position position){
		if (FamilyMemberValueController.check(familyMember, position)){
			familyMember.getPlayer().setFamilyMemberPosition(familyMember, position);
			PassTurnController.lastMove(familyMember.getPlayer());
			return true;
		}
		return false;
	}

}
