package it.polimi.ingsw.minigame;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;

public abstract class ZoneProxy {
	
	protected ArrayList<PositionProxy> positionProxies  = new ArrayList<>();
	
	public ZoneProxy(Zone zone) {
		for(Position position : zone.getPositions()){
			positionProxies.add(new PositionProxy(position));
		}
	}


	public ArrayList<PositionProxy> getPositionProxies() {
		return positionProxies;
	}
	
}
