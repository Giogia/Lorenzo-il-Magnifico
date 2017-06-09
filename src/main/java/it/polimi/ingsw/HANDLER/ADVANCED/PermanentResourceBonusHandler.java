package it.polimi.ingsw.HANDLER.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.PermanentAddResourceBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentMultResourceBonus;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class PermanentResourceBonusHandler {

	public static void handle(ArrayList<Resource> resourceBonus, Player player){
		handleAdd(resourceBonus, player);
		handleMult(resourceBonus, player);
	}

	//Increase the amount of the resources of the ImmediateBonus
	private static void handleAdd(ArrayList<Resource> resourceBonus, Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof PermanentAddResourceBonus){
					ArrayList<Resource> permanentBonusResources = ((PermanentAddResourceBonus) permanentBonus).getResources();
					for (Resource resource : resourceBonus) {
						for (Resource permanentBonusResource : permanentBonusResources) {
							if (resource.getClass().equals(permanentBonusResource.getClass())){
								resource.addAmount(permanentBonusResource.getAmount());
							}
						}
					}
				}
			}
		}
	}

	//Multiply the amount of the resources of the ImmediateBonus
	private static void handleMult(ArrayList<Resource> resourceBonus, Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof PermanentMultResourceBonus){
					ArrayList<Resource> permanentBonusResources = ((PermanentMultResourceBonus) permanentBonus).getResources();
					for (Resource resource : resourceBonus) {
						for (Resource permanentBonusResource : permanentBonusResources) {
							if (resource.getClass().equals(permanentBonusResource.getClass())){
								resource.multAmount(permanentBonusResource.getAmount());
							}
						}
					}
				}
			}
		}
	}
}
