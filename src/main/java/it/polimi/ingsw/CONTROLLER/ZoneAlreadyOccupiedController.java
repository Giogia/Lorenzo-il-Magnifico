package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.GC_15.MyException;

public class ZoneAlreadyOccupiedController implements Controller{
	
	public static boolean check(Zone zone) throws MyException{
		Position[] positions = zone.getPositions();
		for(Position position: positions){
			try{
				if(!position.getFamilyMembers().isEmpty()){
					return false;
				}	
			}catch(NullPointerException e){
				return false;
			}
		}
		return true;
	}

}
