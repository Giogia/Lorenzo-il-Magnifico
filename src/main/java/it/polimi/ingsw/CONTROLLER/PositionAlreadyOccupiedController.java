package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.Position;

public class PositionAlreadyOccupiedController implements Controller{

	public static boolean check(Position position) throws Exception{
		if(position.getFamilyMembers().isEmpty()){
			return true;
		}
		throw new Exception("This position is already occupied");
	}
}
