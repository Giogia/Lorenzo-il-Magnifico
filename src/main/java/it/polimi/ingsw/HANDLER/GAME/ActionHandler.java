package it.polimi.ingsw.HANDLER.GAME;

import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.CONTROLLER.CanGoToController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.HANDLER.*;
import it.polimi.ingsw.minigame.CouncilPalaceProxy;
import it.polimi.ingsw.minigame.HarvestProxy;
import it.polimi.ingsw.minigame.MarketProxy;
import it.polimi.ingsw.minigame.ProductionProxy;
import it.polimi.ingsw.minigame.Update;
import it.polimi.ingsw.minigame.ZoneProxy;

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
    			ZoneProxy zoneProxy = null;
    			
    			if(zone instanceof Market){
		    		MarketHandler.handle(familyMember,position);
		    		zoneProxy = new MarketProxy((Market) zone);
		    	}
		    	else if(zone instanceof CouncilPalace){
		    		CouncilPalaceHandler.handle(familyMember,position);
		    		zoneProxy = new CouncilPalaceProxy((CouncilPalace) zone);
		    	}
		    	else if(zone instanceof ProductionArea){
		    		ProductionAreaHandler.handle(familyMember,(ProductionArea) zone,position);
		    		zoneProxy = new ProductionProxy((ProductionArea) zone);
		    	}
		    	else if(zone instanceof HarvestArea){
		    		HarvestAreaHandler.handle(familyMember,(HarvestArea) zone,position);
		    		zoneProxy = new HarvestProxy((HarvestArea) zone);
		    	}

    			int numberOfPosition = getNumberOfPosition(position, zone);
		    	Update.getInstance().positionOccupied(position, zoneProxy, numberOfPosition);
    		}
	    	return true;
    	}
    	throw new MyException("You cannot go to this Zone");
    }
    
    public static int getNumberOfPosition(Position position, Zone zone) {
		for (int i = 0; i < zone.getPositions().length; i++) {
			if(position.equals(zone.getPosition(i))){
				return i;
			}
		}
		System.out.println("NON HO TROVATO LA POSITION IN GET POSITION UPDATE");
		return 0;
	}
}
