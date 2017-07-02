package it.polimi.ingsw.minigame;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.ExcommunicationTile;

public class BoardProxy implements Serializable{
	
	private RoundOrderProxy roundOrderProxy;
	private ArrayList<TowerProxy> towerProxies = new ArrayList<>();
	private MarketProxy marketProxy;
	private CouncilPalaceProxy councilPalaceProxy;
	private HarvestProxy harvestProxy;
	private ProductionProxy productionProxy;
	private ArrayList<ExcommunicationTileProxy> excommunicationTileProxies = new ArrayList<>();
	
	public BoardProxy(Board board) {
		roundOrderProxy = new RoundOrderProxy(board.getGame().getOrder());
		for(Tower tower: board.getTowers()){
			towerProxies.add(new TowerProxy(tower));
		}
		marketProxy = new MarketProxy(board.getMarket());
		councilPalaceProxy = new CouncilPalaceProxy(board.getCouncilPalace());
		harvestProxy = new HarvestProxy(board.getHarvestArea());
		productionProxy = new ProductionProxy(board.getProductioArea());
		
		for(ExcommunicationTile excommunicationTile : board.getExcommunicationTiles()){
			excommunicationTileProxies.add(new ExcommunicationTileProxy(excommunicationTile));
		}
	}
	
	public RoundOrderProxy getRoundOrderProxy() {
		return roundOrderProxy;
	}
	
	public TowerProxy getTowerProxyByInt(int i){
		return towerProxies.get(i);
	}
	
	public TowerProxy getTowerProxy(DevelopmentCardType developmentCardType) {
		for (TowerProxy towerProxy : towerProxies) {
			if(towerProxy.getDevelopmentCardType().equals(developmentCardType))
				return towerProxy;
		}
		return null;//never arrive here
	}
	
	public MarketProxy getMarketProxy() {
		return marketProxy;
	}
	
	public CouncilPalaceProxy getCouncilPalaceProxy() {
		return councilPalaceProxy;
	}
	
	public HarvestProxy getHarvestProxy() {
		return harvestProxy;
	}
	
	public ProductionProxy getProductionProxy() {
		return productionProxy;
	}

	public ArrayList<ExcommunicationTileProxy> getExcommunicationTileProxies() {
		return excommunicationTileProxies;
	}
	
	public void setExcommunicationTileProxies(ArrayList<ExcommunicationTileProxy> excommunicationTileProxies) {
		this.excommunicationTileProxies = excommunicationTileProxies;
	}
	

}
