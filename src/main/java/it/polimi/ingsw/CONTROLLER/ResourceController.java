package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.*;

public class ResourceController {
	
	//return true if all the player's resource are greater than the resources in the arraylist
	public static boolean check(Player player, ArrayList<Resource> cost) throws MyException{
		ArrayList<Resource> resources = player.getPersonalBoard().getResources();
		for (Resource playerResource : resources) {
			for (Resource costResource : cost) {
				if(playerResource.getClass().equals(costResource.getClass())){ 
					if(playerResource.getAmount()/playerResource.getValue() < costResource.getAmount()){  
						throw new MyException("The resources are not enough");
					}
				}
			}
		}
		return true;
	}
}
