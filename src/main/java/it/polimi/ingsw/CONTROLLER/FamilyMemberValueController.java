package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.BOARD.Position;

public class FamilyMemberValueController implements Controller{
	
	public static boolean check(FamilyMember familyMember, Position position ){
		if(position.getDiceRequirement() == familyMember.getValue()){
			return true;
		}
		return false;
	}//TODO bisogna sommare il permanent bonus o dire che non bisogna guardare il valore del dado ma uno fisso
}
