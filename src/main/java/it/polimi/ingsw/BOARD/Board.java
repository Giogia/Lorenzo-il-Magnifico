package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.*;


public class Board {

	private Player[] players;
	private ExcommunicationTile[] excommunicationTiles;
	private Tower[] towers;
	private Market market;
	private CouncilPalace councilPalace;
	private HarvestArea harvestArea;
	private ProductionArea productionArea;
	private RoundOrder roundOrder;
	
	
	public Board(Player[] player, ExcommunicationTile[] excommunicationTile, Tower[] tower, Market market,
				 CouncilPalace councilPalace, HarvestArea harvestArea, ProductionArea productionArea) {
		this.players = player; 
		this.excommunicationTiles = excommunicationTile;
		this.towers = tower;
		this.market = market;
		this.councilPalace = councilPalace;
		this.harvestArea = harvestArea;
		this.productionArea = productionArea;
	}
	
	public Player[] getPlayers() {
		return players;
	}

	public Player getPlayer(int player){
		return this.players[player];
	}
	
	public ExcommunicationTile getExcommunicationTile(int excommunicationTileNumber){
		return this.excommunicationTiles[excommunicationTileNumber];
	}
	
	
	public Market getMarket(){
		return this.market;
	}
	
	
	public CouncilPalace getCouncilPalace(){
		return this.councilPalace;
	}
	
	public HarvestArea getHarvestArea(){
		return this.harvestArea;
	}
	
	public ProductionArea getProductioArea(){
		return this.productionArea;
	}
	
	public RoundOrder getRoundOrder() {
		return roundOrder;
	}
	
	public void setPlayer(Player players, int playerNumber){
		this.players[playerNumber] = players;
	}
	
	public void setExcommunicationTile(ExcommunicationTile[] excommunicationTile) {
		this.excommunicationTiles = excommunicationTile;
	}
	
	public void setTower(Tower tower, int towerNumber){
		this.towers[towerNumber] = tower;
	}
	
	public void setMarket(Market market){
		this.market = market;
	}
	
	public void setCouncilPalace(CouncilPalace councilPalace){
		this.councilPalace = councilPalace;
	}
	
	public void setHarvestArea(HarvestArea harvestArea){
		this.harvestArea = harvestArea;
	}
	
	public void setProductionArea(ProductionArea productionArea){
		this.productionArea = productionArea;
	}
	
	public void setRoundOrder(RoundOrder roundOrder) {
		this.roundOrder = roundOrder;
	}
	
	//toglie tutti i  family member in ogni zona della board
	public void resetPositions() {
		for(Tower tower : this.towers){
		tower.deleteAllFamilyMember();
		}
		this.market.deleteAllFamilyMember();
		this.councilPalace.deleteAllFamilyMember();
		this.harvestArea.deleteAllFamilyMember();
		this.productionArea.deleteAllFamilyMember();
	}

	public Tower[] getTowers() {
		return towers;
	}
	
	public Tower getTower(DevelopmentCardType developmentCardType){
		for (Tower tower : towers) {
			if (tower.getDevelopmentCardType().equals(developmentCardType))
				return tower;
		} 
		return null;
	}
}




