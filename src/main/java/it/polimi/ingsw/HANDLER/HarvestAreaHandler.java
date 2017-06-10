package it.polimi.ingsw.HANDLER;

import it.polimi.ingsw.HANDLER.HarvestProductionAreaHandler;

import java.rmi.RemoteException;

import it.polimi.ingsw.BOARD.HarvestArea;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;


public class HarvestAreaHandler extends HarvestProductionAreaHandler {
	
	public static boolean handle(FamilyMember familyMember, HarvestArea harvestArea, Position position) throws MyException, RemoteException{
		return(abstractHandle(familyMember, harvestArea, position));
	}	
	
}
