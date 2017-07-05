package it.polimi.ingsw.minigame;

import java.io.Serializable;

import it.polimi.ingsw.BOARD.Market;
import it.polimi.ingsw.BOARD.Position;

public class MarketProxy extends ZoneProxy{

	public MarketProxy(Market market) {
		super(market);
		for(int i = 0; i < 2; i++){ //in market there are 2 positions
			positionProxies.add(new PositionProxy(new Position(null, 0), this, i));
		}
	}
}
