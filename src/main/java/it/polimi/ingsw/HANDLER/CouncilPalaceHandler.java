package it.polimi.ingsw.HANDLER;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CONTROLLER.FamilyMemberValueController;
import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.RESOURCE.Resource;

//set of controls to position a family member on council palace
public class CouncilPalaceHandler {
	
	public static boolean handle(FamilyMember familyMember, Position position) throws MyException, IOException, TimeExpiredException{
		ArrayList<Resource> playerResources = new ArrayList<>();
		for (Resource resource : familyMember.getPlayer().getPersonalBoard().getResources()) {
			playerResources.add(resource.createClone());
		}
		FamilyMember testFamilyMember = new FamilyMember(familyMember.getDice(), familyMember.getPlayer());//creates a test family member to make controls
		testFamilyMember.setValue(familyMember.getValue());
		ServantsHandler.handle(testFamilyMember, playerResources);
		if (FamilyMemberValueController.check(testFamilyMember, position)){
			testFamilyMember.getPlayer().setFamilyMemberPosition(testFamilyMember, position);
			familyMember.getPlayer().getBoard().getPassTurnController().lastMove(testFamilyMember.getPlayer());
			copyResource(testFamilyMember.getPlayer(), playerResources);
			for (ImmediateBonus immediateBonus : position.getBoardBonus()) {
				immediateBonus.getImmediateBonus(testFamilyMember.getPlayer());
			}
			return true;
		}
		return false;
	}
	
	// set an arraylist of resources on a player's resources
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
