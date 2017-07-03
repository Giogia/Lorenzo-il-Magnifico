package it.polimi.ingsw.HANDLER.GAME;

import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.CONTROLLER.CanGoToController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.HANDLER.*;
import it.polimi.ingsw.minigame.Update;

public final class ActionHandler {
	private static ActionHandler istanza = null;

    private ActionHandler() {}

    public static ActionHandler getActionHandler() {
        if (istanza == null) {
            istanza = new ActionHandler();
        }
        return istanza;
    }
    
    public static boolean handle(FamilyMember familyMember, Zone zone,Position position) throws MyException, IOException, TimeExpiredException {
    	if (CanGoToController.check(familyMember.getPlayer(), zone)){
    		if(zone instanceof Tower){
	    		TowerHandler.handle(familyMember,(Tower) zone,(TowerFloor) position);
	    		
	    		Update.getInstance().TowerFloorOccupied((TowerFloor) position, (Tower) zone); 
	    	}
    		else{
		    	if(zone instanceof Market){
		    		MarketHandler.handle(familyMember,position);
		    	}
		    	else if(zone instanceof CouncilPalace){
		    		CouncilPalaceHandler.handle(familyMember,position);
		    	}
		    	else if(zone instanceof ProductionArea){
		    		ProductionAreaHandler.handle(familyMember,(ProductionArea) zone,position);
		    	}
		    	else if(zone instanceof HarvestArea){
		    		HarvestAreaHandler.handle(familyMember,(HarvestArea) zone,position);
		    	}

		    	Update.getInstance().positionOccupied(position, zone);
    		}
	    	return true;
    	}
    	throw new MyException("You cannot go to this Zone");
    }
}
