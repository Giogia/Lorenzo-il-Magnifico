package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;

public class ZoneAlreadyOccupiedController implements Controller{
	
	public static boolean check(Zone zone){
		Position[] positions = zone.getPositions();
		for (int i = 0; i < positions.length; i++){
			if (!positions[i].getFamilyMembers().isEmpty()){
				return false;
			}
		}
		return true;
	}

}
