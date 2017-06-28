package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.GC_15.MyException;

public class PositionAlreadyOccupiedController implements Controller{

	//returns true if this position is empty! otherwise, throws a MyException
	public static boolean check(Position position) throws MyException{
		if(position.getFamilyMembers().isEmpty()){
			return true;
		}
		throw new MyException("This position is already occupied");
	}
}
