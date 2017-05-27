package it.polimi.ingsw.HANDLER;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.CONTROLLER.FamilyMemberValueController;
import it.polimi.ingsw.CONTROLLER.OccupiedYetBonusController;
import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.CONTROLLER.PositionAlreadyOccupiedController;
import it.polimi.ingsw.GC_15.FamilyMember;

public class MarketHandler {
	
	public static boolean handle(FamilyMember familyMember, Position position){
		if (!PositionAlreadyOccupiedController.check(position)){
			if (!OccupiedYetBonusController.check(familyMember)){
				return false;
			}
		}
		if (FamilyMemberValueController.check(familyMember, position)){
			familyMember.getPlayer().setFamilyMemberPosition(familyMember, position);
			PassTurnController.lastMove(familyMember.getPlayer());
			return true;
		}
		return false;
			
	}

}
