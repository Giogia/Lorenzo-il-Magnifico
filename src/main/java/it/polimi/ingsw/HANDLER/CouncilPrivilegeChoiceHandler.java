package it.polimi.ingsw.HANDLER;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.manager.Manager;

public class CouncilPrivilegeChoiceHandler {
	//councilPrivileges is the arraylist of bonus to send to the human. He choose one oh this and this is immediately activated
	//and removed from the councilPrivileges
	private static ArrayList<ResourceBonus> councilPrivileges; //TODO è giusto che sia static?
	
	public static boolean handle(Player player, int numberOfDifferentCouncilPrivileges){
		councilPrivileges = DataFromFile.getCouncilPrivileges();
		for(int i=0; i < numberOfDifferentCouncilPrivileges; i++){
			ResourceBonus singleBonus = Manager.getCouncilPrivilege(player, councilPrivileges); //the single bonus choosen from the human
			singleBonus.getImmediateBonus(player);
			councilPrivileges.remove(singleBonus);//remove the single bonus from the bonus that can choose next time the human
		}
		return true;
	}
}
