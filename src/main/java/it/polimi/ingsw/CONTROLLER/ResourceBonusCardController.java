package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;
import it.polimi.ingsw.BONUS.AddResourceBonus;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class ResourceBonusCardController {// controlla se un array di bonus contiene resourceBonus attivabili

	public static boolean check(ArrayList<Bonus> chosenEffects, Player player,ArrayList<Resource> playerResources){
		for(Bonus bonus : chosenEffects){
			if(bonus instanceof  AddResourceBonus){//prende tutte le risorse negative, le sottrae a player e vede se ne ha ancora 
				AddResourceBonus addResourceBonus = (AddResourceBonus) bonus;
				for(Resource resource : addResourceBonus.getResources()){
					if(resource.getAmount()<0){
						for(Resource playerResource : playerResources){
							if(playerResource.getResourceType().equals(resource.getResourceType())){
								if(playerResource.getAmount()+resource.getAmount()<0)
									return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
}
