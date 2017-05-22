package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.Position;

public class PositionAlreadyOccupiedController implements Controller{

	public boolean check(Position position){
		return (position.getFamilyMembers().isEmpty());
	}
}
