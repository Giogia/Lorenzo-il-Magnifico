package it.polimi.ingsw.HANDLER;

import java.util.ArrayList;

import it.polimi.ingsw.CONTROLLER.ResourceController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.RESOURCE.Servants;

public class ServantsHandler {

	public static boolean handle(FamilyMember familyMember,int servantsNumber){
		Servants servants = new Servants();
		servants.setAmount(servantsNumber);
		ArrayList<Resource> servantsArrayList = new ArrayList<>();
		servantsArrayList.add(servants);
		if (ResourceController.check(familyMember.getPlayer(), servantsArrayList)){
			
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
