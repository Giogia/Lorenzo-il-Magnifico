package it.polimi.ingsw.HANDLER.ADVANCED;

import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.manager.Manager;

public class UseLeaderCardEffectHandler {

	public static void handle(Player player, LeaderCard chosen) throws IOException, MyException, TimeExpiredException{
		for(Bonus bonus: chosen.getBonus()){
			ImmediateBonus immediateBonus = (ImmediateBonus) bonus;
			immediateBonus.getImmediateBonus(player);
		}
		player.getPersonalBoard().getOncePerRoundBonusLeaderCard().remove(chosen);
		
	}
}
