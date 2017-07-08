package it.polimi.ingsw.minigame;

import java.io.Serializable;

import it.polimi.ingsw.BOARD.HarvestArea;

public class HarvestProxy extends ZoneProxy{

	public HarvestProxy(HarvestArea harvestArea) {
		super(harvestArea);
		positionProxies.clear();
		positionProxies.add(new PositionProxy());
		positionProxies.add(new PositionProxy());
	}
}
