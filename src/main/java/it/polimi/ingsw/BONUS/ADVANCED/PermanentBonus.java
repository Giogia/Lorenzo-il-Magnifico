package it.polimi.ingsw.BONUS.ADVANCED;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.GC_15.Player;

public abstract class PermanentBonus implements Bonus{
	
	public void getPermanentBonus(Player player){
		player.getPersonalBoard().addPermanentBonus(this);
	}
}
