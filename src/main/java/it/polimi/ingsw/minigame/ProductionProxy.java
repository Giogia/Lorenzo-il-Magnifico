package it.polimi.ingsw.minigame;

import it.polimi.ingsw.BOARD.ProductionArea;

public class ProductionProxy extends ZoneProxy{

	public ProductionProxy(ProductionArea productionArea) {
		super(productionArea);
		positionProxies.clear();
		positionProxies.add(new PositionProxy());
		positionProxies.add(new PositionProxy());
		
	}
}
