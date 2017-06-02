package it.polimi.ingsw.HANDLER;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.GC_15.FamilyMember;

public class ProductionAreaHandler extends HarvestProductionAreaHandler{
	
		
	public static boolean handle(FamilyMember familyMember, ProductionArea productionArea, Position position) throws Exception{
		return(abstractHandle(familyMember, productionArea, position));
	}

}
