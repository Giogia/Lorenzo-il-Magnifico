package it.polimi.ingsw.HANDLER;

import java.util.ArrayList;

import it.polimi.ingsw.CONTROLLER.ResourceController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class ServantsHandler {

	public static boolean handle(FamilyMember familyMember,int servantsNumber){
		if (ResourceController.check(familyMember.getPlayer(), ResourceType.SERVANTS, servantsNumber)){
			
			familyMember.addValue(servantsNumber); 
			
			ArrayList<Resource> resources = familyMember.getPlayer().getPersonalBoard().getResources(); 
			for(Resource resource : resources){
				if(resource.getResourceType() == ResourceType.SERVANTS){ // cerca i servants
				resource.setAmount(resource.getAmount() - servantsNumber); //diminuisce di servantsNumber i servants
				}
			}
			return true;
		}
		return false;
    }// TODO forse sarebbe meglio usare un familyMemberValue e un Resource Bonus in modo da avere tutto piu' controllato
}
