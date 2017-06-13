package it.polimi.ingsw.HANDLER.ADVANCED;

import java.rmi.RemoteException;

import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.CouncilPrivilegeChoiceHandler;
import it.polimi.ingsw.manager.Manager;

public class DiscardLeaderCardHandler {
	
	public static void handle(Player player) throws RemoteException{
		LeaderCard chosen = Manager.chooseLeaderCard(player, player.getLeaderCardInHand());
		player.getLeaderCardInHand().remove(chosen);
		CouncilPrivilegeChoiceHandler.handle(player, 1);
	}
}
