package it.polimi.ingsw.GC_15;

import java.util.HashMap;

public class ActionBonus implements ImmediateBonus {
	private HashMap<ActionZone, Integer> action;
	
	public ActionBonus(HashMap actionMap){
		action = new HashMap<>();
		action.putAll(actionMap);
	}
	
	public void getImmediateBonus(Player player){
		//TODO Bisogna decidere se usare un controller o un familymember fasullo
	}

}
