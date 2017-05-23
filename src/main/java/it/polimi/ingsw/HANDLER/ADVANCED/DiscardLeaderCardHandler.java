package it.polimi.ingsw.HANDLER.ADVANCED;

import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Player;

public class DiscardLeaderCardHandler {
	public static void handle(Player player, LeaderCard leaderCard){
		//TODO
		//crea il bonus council privilege
		player.discardLeaderCard(leaderCard);
		//crea il bonus council Privilege e chiama il metodo getImmediateBonus (quest'ultimo chiamerà il CouncilPrivilegeChoiceHandler)
	}
}
