package it.polimi.ingsw.HANDLER.GAME;

import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.CONTROLLER.CanGoToController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.HANDLER.*;

public final class ActionHandler {
	private static ActionHandler istanza = null;

    private ActionHandler() {}

    public static ActionHandler getActionHandler() {
        if (istanza == null) {
            istanza = new ActionHandler();
        }
        return istanza;
    }
    
    public static boolean handle(FamilyMember familyMember, Zone zone,Position position) throws MyException, IOException {
    	if (CanGoToController.check(familyMember.getPlayer(), zone)){
	    	if(zone instanceof Market){
	    		return MarketHandler.handle(familyMember,position);
	    	}
	    	if(zone instanceof CouncilPalace){
	    		return CouncilPalaceHandler.handle(familyMember,position);
	    	}
	    	if(zone instanceof Tower){
	    		return TowerHandler.handle(familyMember,(Tower) zone,(TowerFloor) position);
	    	}
	    	if(zone instanceof ProductionArea){
	    		return ProductionAreaHandler.handle(familyMember,(ProductionArea) zone,position);
	    	}
	    	if(zone instanceof HarvestArea){
	    		return HarvestAreaHandler.handle(familyMember,(HarvestArea) zone,position);
	    	}	
    	}
    	throw new MyException("You cannot go to this Zone");
    }
}
