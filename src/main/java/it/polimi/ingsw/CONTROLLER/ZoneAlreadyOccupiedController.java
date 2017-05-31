package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;

public class ZoneAlreadyOccupiedController implements Controller{
	
	public static boolean check(Zone zone){
		for(Position position: zone.getPositions()){
			if(!position.getFamilyMembers().isEmpty()){
				return false;
			}	
		}
		return true;
	}

}
