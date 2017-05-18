package it.polimi.ingsw.BONUS;

import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.CARD.OncePerRoundLeaderCard;
import it.polimi.ingsw.GC_15.Player;

//NB dato che questo bonus è particolare e richiede di sapere la leaderCard da copiare, ho dovuto eliminare l'implements ImmediateBonus
//poichè altrimenti dovevo scrivere il metodo di immediateBonus che prendeva come ingresso solo il player.

//-> se vi piace, lasciamo così, a me non sono venute altre idee
public class CopyBonus implements Bonus{
	
	
	//da' al player la carta leader da lui scelta 
	public void getImmediateBonus(Player player, LeaderCard leaderCard){
		if (leaderCard instanceof OncePerRoundLeaderCard){
			player.getPersonalBoard().addOncePerRoundBonusLeaderCard(leaderCard);
		}else{
			player.getPersonalBoard().getPermanentBonus().addAll(leaderCard.bonus);
		}
	}

	
}

