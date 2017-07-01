package it.polimi.ingsw.minigame;

import java.util.ArrayList;

public abstract class ZoneProxy {

	private ArrayList<PositionProxy> positionProxies  = new ArrayList<>();

	public ArrayList<PositionProxy> getPositionProxies() {
		return positionProxies;
	}
	
}
