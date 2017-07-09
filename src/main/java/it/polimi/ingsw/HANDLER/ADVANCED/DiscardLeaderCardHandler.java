package it.polimi.ingsw.HANDLER.ADVANCED;

import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.HANDLER.CouncilPrivilegeChoiceHandler;
import it.polimi.ingsw.manager.Manager;

//makes a player discard a leader card
public class DiscardLeaderCardHandler {
	
	public static void handle(Player player, LeaderCard chosen) throws IOException, TimeExpiredException{
		CouncilPrivilegeChoiceHandler.handle(player, 1);
		player.getLeaderCardInHand().remove(chosen);
	}
}
