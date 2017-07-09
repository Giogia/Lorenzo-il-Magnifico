package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;
import it.polimi.ingsw.BONUS.AddResourceBonus;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class ResourceBonusCardController {

	//check if a bonus array contains activable resource bonus (for harvest and production)
	public static boolean check(ArrayList<Bonus> chosenEffects, Player player,ArrayList<Resource> playerResources){
		if(!chosenEffects.isEmpty()){//if it's empty it's fine
			for(Bonus bonus : chosenEffects){
				if(bonus instanceof  AddResourceBonus){
					AddResourceBonus addResourceBonus = (AddResourceBonus) bonus;
					for(Resource resource : addResourceBonus.getResources()){
						if(resource.getAmount()<0){//takes all the negative resources
							for(Resource playerResource : playerResources){
								if(resource.getClass().equals(playerResource.getClass())){//find the right resource type
									if((playerResource.getAmount()+resource.getAmount())<0)//subtract them to player's resources and check if those are >= than zero 
										return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
}
