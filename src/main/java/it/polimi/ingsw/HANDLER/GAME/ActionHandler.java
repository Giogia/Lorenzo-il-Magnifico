package it.polimi.ingsw.HANDLER.GAME;

import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.GC_15.FamilyMember;
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
    
    
    public static boolean handle(FamilyMember familyMember, Zone zone,Position position){
    	Manager.handleServants(familyMember);
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
    	return false;
    }
}
