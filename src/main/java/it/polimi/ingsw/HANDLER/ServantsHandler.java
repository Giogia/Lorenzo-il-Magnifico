package it.polimi.ingsw.HANDLER;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.RESOURCE.Servants;
import it.polimi.ingsw.manager.Manager;

public class ServantsHandler {

	public static boolean handle(FamilyMember familyMember, ArrayList<Resource> playerResources) throws IOException, TimeExpiredException{
		int servantsNumber = Manager.askForServants(familyMember.getPlayer());
		if (servantsNumber == 0){
			return true;
		}
		Servants servants = new Servants(servantsNumber,1);
		Servants playerServants = new Servants(0, 1);
		for (Resource resource : playerResources) {
			if (resource.getResourceType().equals(ResourceType.servants)){
				playerServants = (Servants) resource;
			}
		}
		if (servants.getAmount() <= playerServants.getAmount()){
			int servantValue = playerServants.getValue();
			int valueFamilyMember = servantsNumber/servantValue;
			familyMember.addValue(valueFamilyMember); 
			playerServants.addAmount(-servantsNumber);
			for (Resource resource : playerResources) {
				if (resource.getResourceType().equals(ResourceType.servants)){
					resource = playerServants;
				}
			}
			return true;
		}
		return false;
    }
}
