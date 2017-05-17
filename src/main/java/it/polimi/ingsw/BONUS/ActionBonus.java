package it.polimi.ingsw.BONUS;

import java.util.HashMap;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.GC_15.Player;

public class ActionBonus implements ImmediateBonus {
	private HashMap<ActionZone, Integer> action;
	
	public ActionBonus(HashMap<ActionZone, Integer> actionMap){
		action = new HashMap<>();
		action.putAll(actionMap);
	}
	
	public void getImmediateBonus(Player player){
		//TODO Bisogna decidere se usare un controller o un familymember fasullo
	}

}
