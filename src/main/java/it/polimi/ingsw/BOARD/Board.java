package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.GC_15.*;


public class Board {

	private Player[] players;
	private ExcommunicationTile[] excommunicationTile;
	private Tower[] tower;
	private Market market;
	private CouncilPalace councilPalace;
	private HarvestArea harvestArea;
	private ProductionArea productionArea;
	
	
	public Board(Player[] player, ExcommunicationTile[] excommunicationTile, Tower[] tower, Market market,
				 CouncilPalace councilPalace, HarvestArea harvestArea, ProductionArea productionArea) {
		this.players = player; 
		this.excommunicationTile = excommunicationTile;
		this.tower = tower;
		this.market = market;
		this.councilPalace = councilPalace;
		this.harvestArea = harvestArea;
		this.productionArea = productionArea;
	}

	public Player getPlayer(int player){
		return this.players[player];
	}
	
	public ExcommunicationTile getExcommunicationTile(int excommunicationTileNumber){
		return this.excommunicationTile[excommunicationTileNumber];
	}
	
	public Tower getTower(int towerNumber){
		return this.tower[towerNumber];
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
	
	public void setPlayer(Player player, int playerNumber){
		this.players[playerNumber] = player;
	}
	
	public void setExcommunicationTile(ExcommunicationTile[] excommunicationTile) {
		this.excommunicationTile = excommunicationTile;
	}
	
	public void setTower(Tower tower, int towerNumber){
		this.tower[towerNumber] = tower;
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
	
	//toglie tutti i  family member in ogni zona della board
	public void resetPositions() {
		for(Tower tower : this.tower){
		tower.deleteAllFamilyMember();
		}
		this.market.deleteAllFamilyMember();
		this.councilPalace.deleteAllFamilyMember();
		this.harvestArea.deleteAllFamilyMember();
		this.productionArea.deleteAllFamilyMember();
	}
	
}




