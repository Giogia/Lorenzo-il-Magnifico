package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.BOARD.Position;

public class FamilyMemberValueController implements Controller{
	
	public boolean check(FamilyMember familyMember, Position position ){
		return (position.getDiceRequirement() <= familyMember.getValue());
	}
}
