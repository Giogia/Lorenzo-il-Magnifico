package it.polimi.ingsw.minigame;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;

public abstract class ZoneProxy implements Serializable{
	
	protected ArrayList<PositionProxy> positionProxies  = new ArrayList<>();
	
	public ZoneProxy(Zone zone) {
		Position[] positions = zone.getPositions();
		for(int i = 0; i < positions.length; i++){
			positionProxies.add(new PositionProxy(positions[i], this, i));
		}
	}

	public ArrayList<PositionProxy> getPositionProxies() {
		return positionProxies;
	}
	
}
