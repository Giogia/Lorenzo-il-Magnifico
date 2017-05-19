package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.*;

public class ResourceController {
	
	public static boolean check(Player player, ResourceType resourceType, int value){
		ArrayList<Resource> resources = player.getPersonalBoard().getResources();
		
		for(Resource resource : resources){
			if(resource.getResourceType() == resourceType){ // controlla che sia la risorsa giusta
				if(resource.getAmount()*resource.getValue() >= value){ //controlla se la quantita' posseduta al netto del valore e' maggiore di quella richiesta 
					return true;
				}
			}
		}
		return false;
	}
}
