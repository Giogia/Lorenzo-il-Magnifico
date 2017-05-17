package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

public class OrderBonus extends PermanentBonus{
	private boolean[] skipAction = new boolean[4];
	
	public OrderBonus(boolean[] skipAction) {
		for (int i = 0; i < 4; i++){
			this.skipAction[i] = skipAction[i];
		}
	}
	
	public boolean[] getSkipAction() {
		return skipAction;
	}

}
