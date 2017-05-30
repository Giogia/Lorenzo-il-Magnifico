package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.*;


public class Board {
	private Tower[] towers;
	private Market market;
	private CouncilPalace councilPalace;
	private HarvestArea harvestArea;
	private ProductionArea productionArea;
	private RoundOrder roundOrder;
	
	
	public Board() {
		towers = new Tower[4];
		towers[0] = new Tower(DevelopmentCardType.TERRITORY);
		towers[1] = new Tower(DevelopmentCardType.CHARACTER);
		towers[2] = new Tower(DevelopmentCardType.BUILDING);
		towers[3] = new Tower(DevelopmentCardType.VENTURE);
		market = new Market();
		councilPalace = new CouncilPalace();
		harvestArea = new HarvestArea();
		productionArea = new ProductionArea();
	}
	
	public Player[] getPlayers() {
		return Game.getPlayers();
	}

	public Player getPlayer(int position){
		Player[] players = getPlayers();
		return players[position];
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




