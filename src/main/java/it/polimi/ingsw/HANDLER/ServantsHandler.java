package it.polimi.ingsw.HANDLER;

import java.util.ArrayList;

import it.polimi.ingsw.CONTROLLER.ResourceController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.HANDLER.GAME.Manager;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.RESOURCE.Servants;

public class ServantsHandler {

	public static boolean handle(FamilyMember familyMember){
		int servantsNumber = Manager.askForServants(familyMember.getPlayer());
		Servants servants = new Servants(servantsNumber,1);
		ArrayList<Resource> servantsArrayList = new ArrayList<>();
		servantsArrayList.add(servants);
		if (ResourceController.check(familyMember.getPlayer(), servantsArrayList)){
			int servantValue = familyMember.getPlayer().getPersonalBoard().getResource(ResourceType.SERVANTS).getValue();
			int valueFamilyMember = servantsNumber/servantValue;
			familyMember.addValue(valueFamilyMember); 
			familyMember.getPlayer().getPersonalBoard().getResource(ResourceType.SERVANTS).addAmount(-servantsNumber);
			return true;
		}
		return false;
    }
}
