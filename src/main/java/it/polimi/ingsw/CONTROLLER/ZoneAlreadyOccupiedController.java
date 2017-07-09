package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;

public class ZoneAlreadyOccupiedController implements Controller{
	
	//return true only if every position of the zone isn't occupied
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
