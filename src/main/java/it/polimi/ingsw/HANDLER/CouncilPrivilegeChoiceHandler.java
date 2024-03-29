package it.polimi.ingsw.HANDLER;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.manager.Manager;

public class CouncilPrivilegeChoiceHandler {
	//councilPrivileges is the arraylist of bonus to send to the user.
	//He choose one of this, immediately activates it 
	//and removes it from the councilPrivileges
private static ArrayList<ResourceBonus> councilPrivileges;
	
	public static boolean handle(Player player, int numberOfDifferentCouncilPrivileges) throws IOException, TimeExpiredException{
		councilPrivileges = new ArrayList<>();
		for (ResourceBonus resourceBonus : player.getBoard().getGame().getData().getCouncilPrivileges()) {
			councilPrivileges.add(resourceBonus.createClone());
		}
		for(int i=0; i < numberOfDifferentCouncilPrivileges; i++){
			ResourceBonus singleBonus = Manager.getCouncilPrivilege(player, councilPrivileges); //the single bonus choosen from the human
			singleBonus.getImmediateBonus(player);
			councilPrivileges.remove(singleBonus);//remove the single bonus from the bonus that can choose next time the human
		}
		return true;
	}
}
