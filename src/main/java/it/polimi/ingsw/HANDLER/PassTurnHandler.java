package it.polimi.ingsw.HANDLER;

import java.util.ArrayList;

import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.RESOURCE.Servants;

//set of instructions to pass a turn
public class PassTurnHandler {
	
	public static boolean handle(Player player){
		PassTurnController passTurnController = player.getBoard().getPassTurnController();
		if (passTurnController.getLastMove() == null || !passTurnController.getLastMove().equals(player)){
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
			passTurnController.lastMove(player);
			return true;
		}
		else
			return true;
	}

}
