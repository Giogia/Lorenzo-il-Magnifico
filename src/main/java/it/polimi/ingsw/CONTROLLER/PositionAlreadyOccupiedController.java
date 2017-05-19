package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.Position;

public class PositionAlreadyOccupiedController implements Controller{

	public static boolean check(Position position){
		return (position.getFamilyMembers().isEmpty());
	}
}
