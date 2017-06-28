package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.BOARD.Position;

public class FamilyMemberValueController implements Controller {
	//check if family member has value greater than position's requirement. Returns true or throws MyException 
	public static boolean check(FamilyMember familyMember, Position position ) throws MyException{
		if(position.getDiceRequirement() <= familyMember.getValue()){
			return true;
		}
		throw new MyException("The Family Member Value is not enough!");
	}
}
