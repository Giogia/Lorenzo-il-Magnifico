package it.polimi.ingsw.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.*;


public class Board {

	private Player[] players;
	private Tower[] towers;
	private Market market;
	private CouncilPalace councilPalace;
	private HarvestArea harvestArea;
	private ProductionArea productionArea;
	private RoundOrder roundOrder;
	
	
	public Board(ArrayList<Player> players) {
		int numberOfPlayers = players.size();
		
		this.players = new Player[numberOfPlayers]; 
		market = new Market(numberOfPlayers);
		councilPalace = new CouncilPalace();
		harvestArea = new HarvestArea(numberOfPlayers);
		productionArea = new ProductionArea(numberOfPlayers);
		
		towers[0] = new Tower(DevelopmentCardType.territory);
		towers[1] = new Tower(DevelopmentCardType.character);
		towers[2] = new Tower(DevelopmentCardType.building);
		towers[3] = new Tower(DevelopmentCardType.venture);
		
	}
	
	public Player[] getPlayers() {
		return players;
	}

	public Player getPlayer(int player){
		return this.players[player];
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
	
	public RoundOrder getRoundOrder() {
		return roundOrder;
	}
	
	public void setPlayer(Player players, int playerNumber){
		this.players[playerNumber] = players;
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
	
}




