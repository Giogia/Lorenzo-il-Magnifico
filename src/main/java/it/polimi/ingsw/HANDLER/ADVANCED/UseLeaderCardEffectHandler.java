package it.polimi.ingsw.HANDLER.ADVANCED;

import java.rmi.RemoteException;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.manager.Manager;

public class UseLeaderCardEffectHandler {

	public static void handle(Player player) throws RemoteException, MyException{
		LeaderCard chosen = Manager.chooseLeaderCard(player, player.getPersonalBoard().getOncePerRoundBonusLeaderCard());
		for(Bonus bonus: chosen.getBonus()){
			ImmediateBonus immediateBonus = (ImmediateBonus) bonus;
			immediateBonus.getImmediateBonus(player);
		}
		player.getPersonalBoard().getOncePerRoundBonusLeaderCard().remove(chosen);
		
	}
}
