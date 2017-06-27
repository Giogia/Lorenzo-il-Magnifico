package it.polimi.ingsw.HANDLER;

import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.TimeExpiredException;

public class ProductionAreaHandler extends HarvestProductionAreaHandler{
	
		
	public static boolean handle(FamilyMember familyMember, ProductionArea productionArea, Position position) throws MyException, IOException, TimeExpiredException{
		return(abstractHandle(familyMember, productionArea, position));
	}

}
