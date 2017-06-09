package it.polimi.ingsw.BOARD;

import java.io.Serializable;

import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.*;


public class Board implements Serializable{

	private Tower[] towers;
	private Market market;
	private CouncilPalace councilPalace;
	private HarvestArea harvestArea;
	private ProductionArea productionArea;
	private Game game;
	
	public Board(Game game) {
		this.game= game;
		
		market = new Market();
		
		councilPalace = new CouncilPalace();
		
		harvestArea = new HarvestArea();
		
		productionArea = new ProductionArea();
		
		towers = new Tower[4];
		towers[0] = new Tower(DevelopmentCardType.territory);
		towers[1] = new Tower(DevelopmentCardType.character);
		towers[2] = new Tower(DevelopmentCardType.building);
		towers[3] = new Tower(DevelopmentCardType.venture);
	}
	
	public Game getGame() {
		return game;
	}
	
	public Player[] getPlayers() {
		return game.getPlayers();
	}

	public Tower getTower(int towerNumber){
		return this.towers[towerNumber];
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




