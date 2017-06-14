package it.polimi.ingsw.HANDLER.ADVANCED;

import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.CONTROLLER.EnoughCardsOfSameTypeController;
import it.polimi.ingsw.CONTROLLER.LeaderCardCardActivationConditionController;
import it.polimi.ingsw.CONTROLLER.ResourceController;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.manager.Manager;

public class ActivateLeaderCardHandler {

	public static void handle(Player player,LeaderCard chosen) throws IOException, MyException{
		if(chosen.getResourceActivationCondition()!=null)
			ResourceController.check(player, chosen.getResourceActivationCondition());
		if(chosen.getCardActivationCondition()!=null)
			LeaderCardCardActivationConditionController.check(player, chosen.getCardActivationCondition());
		EnoughCardsOfSameTypeController.check(player, chosen.getNumberOfSameTypeCards());
		player.getPersonalBoard().addActivatedLeaderCards(chosen);
		player.getLeaderCardInHand().remove(chosen);
		for(Bonus bonus : chosen.getBonus()){
			if(bonus  instanceof ImmediateBonus){
				ImmediateBonus immediateBonus = (ImmediateBonus) bonus;
				immediateBonus.getImmediateBonus(player);
			}
			if(bonus instanceof PermanentBonus){
				PermanentBonus permanentBonus = (PermanentBonus) bonus;
				permanentBonus.getPermanentBonus(player);
				return;
			}
		}
	}	
}
