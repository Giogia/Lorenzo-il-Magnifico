package it.polimi.ingsw.HANDLER;

import java.util.ArrayList;

import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.RESOURCE.Servants;

public class PassTurnHandler {
	
	public static boolean handle(Player player){
		if (PassTurnController.getLastMove() == null){
			return false;
		}
		if (PassTurnController.getLastMove().equals(player)){
			return true;
		}
		else{
			ArrayList<FamilyMember> playerFamilyMembers = player.getFamilyMembers();
			Servants playerServants = (Servants) player.getPersonalBoard().getResource(ResourceType.servants);
			for (FamilyMember familyMember : playerFamilyMembers) {
				if (familyMember.getValue() > 0){
					return false;
				}
			}
			if (playerServants.getAmount() >= playerServants.getValue()){
				return false;
			}
			PassTurnController.lastMove(player);
			return true;
		}
	}

}
