package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.*;

public class ResourceController {
	
	
	//TODO per Michele se non ti piace come sta scritto dividiamo quei metodi lunghi in metodi privati
	
	/* Fa un ciclo delle risorse del player per ogni risorsa del costo. 
	 * ogni volta che le risorse sono dello stesso tipo controlla
	 * Se le risorse del player al netto del valore dell'unità sono minori dell'amount del cost
	 * ritorna false
	 * Dopo che controlla tutte le risorse, ritorna true
	 */
	public static boolean check(Player player, ArrayList<Resource> cost){
		ArrayList<Resource> playerResources = player.getPersonalBoard().getResources();
		for(Resource playerResource : playerResources){
			for (Resource costResource : cost) {
				if(playerResource.getResourceType().equals(costResource.getResourceType())){ 
					if(playerResource.getAmount()/playerResource.getValue() < costResource.getAmount()){  
						return false;
					}
				}
			}
		}
		return true;
	}
}
