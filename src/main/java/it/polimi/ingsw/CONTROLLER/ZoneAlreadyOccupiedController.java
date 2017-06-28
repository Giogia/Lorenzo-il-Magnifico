package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;

public class ZoneAlreadyOccupiedController implements Controller{
	//if each position of zone isn't occupied, return true, else false
	public static boolean check(Zone zone) {
		Position[] positions = zone.getPositions();
		for(Position position: positions){
			if(!position.getFamilyMembers().isEmpty()){
				return false;
			}	
		}
		return true;
	}

}
