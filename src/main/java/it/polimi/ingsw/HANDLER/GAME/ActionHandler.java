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

//sets of instructions to place a family member
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
	    		
	    		Update.getInstance().TowerFloorOccupied((TowerFloor) position, (Tower) zone, null); 
	    	}
    		else{
    			ZoneProxy zoneProxy = null;
    			int numberOfPosition = 0;
    			
    			if(zone instanceof Market){
		    		MarketHandler.handle(familyMember,position);
		    		zoneProxy = new MarketProxy((Market) zone);
		    		numberOfPosition = getNumberOfPosition(position, zone);
		    	}
		    	else if(zone instanceof CouncilPalace){
		    		CouncilPalaceHandler.handle(familyMember,position);
		    		zoneProxy = new CouncilPalaceProxy((CouncilPalace) zone);
		    		numberOfPosition = getNumberOfFamilyMember(familyMember, position);
		    	}
		    	else if(zone instanceof ProductionArea){
		    		ProductionAreaHandler.handle(familyMember,(ProductionArea) zone,position);
		    		zoneProxy = new ProductionProxy((ProductionArea) zone);
		    		numberOfPosition = getNumberOfPosition(position, zone);
		    	}
		    	else if(zone instanceof HarvestArea){
		    		HarvestAreaHandler.handle(familyMember,(HarvestArea) zone,position);
		    		zoneProxy = new HarvestProxy((HarvestArea) zone);
		    		numberOfPosition = getNumberOfPosition(position, zone);
		    	}

		    	Update.getInstance().positionOccupied(position, zoneProxy, numberOfPosition);
    		}
    		Update.getInstance().updatePlayerResources(familyMember.getPlayer().getColor(), familyMember.getPlayer().getPersonalBoard().getResources());
	    	return true;
    	}
    	throw new MyException("You cannot go to this Zone");
    }
    
  //returns number of position of family member in council palace
    private static int getNumberOfFamilyMember(FamilyMember familyMember, Position position) {
    	for (int i = 0; i < position.getFamilyMembers().size(); i++) {
    		FamilyMember familyMemberInPosition = position.getFamilyMember(i);
			if(familyMember.getPlayer().getColor().equals(familyMemberInPosition.getPlayer().getColor())){
				return i;
			}
		}
    	return 0;
	}

    //returns number of position of this position in this zone
	public static int getNumberOfPosition(Position position, Zone zone) {
		for (int i = 0; i < zone.getPositions().length; i++) {
			if(position.equals(zone.getPosition(i))){
				return i;
			}
		}
		System.out.println("NON HO TROVATO IL FAMILY MEMBER");
		return 0;
	}
}
