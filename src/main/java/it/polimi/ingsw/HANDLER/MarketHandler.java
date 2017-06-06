package it.polimi.ingsw.HANDLER;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CONTROLLER.FamilyMemberValueController;
import it.polimi.ingsw.CONTROLLER.OccupiedYetBonusController;
import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.CONTROLLER.PositionAlreadyOccupiedController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class MarketHandler {
	
	public static boolean handle(FamilyMember familyMember, Position position) throws MyException{
		if (!PositionAlreadyOccupiedController.check(position)){
			if (!OccupiedYetBonusController.check(familyMember)){
				return false;
			}
		}
		ArrayList<Resource> playerResources = new ArrayList<>();
		for (Resource resource : familyMember.getPlayer().getPersonalBoard().getResources()) {
			playerResources.add(resource.clone());
		}
		FamilyMember testFamilyMember = new FamilyMember(familyMember.getDice(), familyMember.getPlayer());
		ServantsHandler.handle(testFamilyMember, playerResources);
		if (FamilyMemberValueController.check(testFamilyMember, position)){
			testFamilyMember.getPlayer().setFamilyMemberPosition(testFamilyMember, position);
			PassTurnController.lastMove(testFamilyMember.getPlayer());
			copyResource(testFamilyMember.getPlayer(), playerResources);
			for (ImmediateBonus immediateBonus : position.getBoardBonus()) {
				immediateBonus.getImmediateBonus(testFamilyMember.getPlayer());
			}
			return true;
		}
		return false;
			
	}
	
	private static void copyResource(Player player, ArrayList<Resource> copiedResources) {
		ArrayList<Resource> playerResources = player.getPersonalBoard().getResources();
		for (Resource playerResource : playerResources) {
			for (Resource copiedResource : copiedResources) {
				if (copiedResource.getResourceType().equals(playerResource.getResourceType())){
					playerResource.setAmount(copiedResource.getAmount());
				}
			}
		}
	}

}
