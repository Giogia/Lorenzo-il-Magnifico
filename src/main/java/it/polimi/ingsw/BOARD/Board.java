package it.polimi.ingsw.BOARD;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.GC_15.*;

//model class of the game Board
public class Board implements Serializable{

	private Tower[] towers;
	private Market market;
	private CouncilPalace councilPalace;
	private HarvestArea harvestArea;
	private ProductionArea productionArea;
	private ExcommunicationTile excommunicationTiles[] = new ExcommunicationTile[3];
	private ArrayList<Dice> dices = new ArrayList<>();
	private Game game;
	private PassTurnController passTurnController;
	
	public Board(Game game) {
		this.game= game;
		
		market = new Market(this);
		
		councilPalace = new CouncilPalace(this);
		
		harvestArea = new HarvestArea(this);
		
		productionArea = new ProductionArea(this);
		
		towers = new Tower[4];
		towers[0] = new Tower(DevelopmentCardType.territory, this);
		towers[1] = new Tower(DevelopmentCardType.character, this);
		towers[2] = new Tower(DevelopmentCardType.building, this);
		towers[3] = new Tower(DevelopmentCardType.venture, this);
		
		passTurnController = new PassTurnController();
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
	
	
	//clear every family member positioned during a round
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
	
	public void setDices(ArrayList<Dice> dices) {
		this.dices = dices;
	}
	
	public ArrayList<Dice> getDices() {
		return dices;
	}
	
	public void setExcommunicationTiles(ExcommunicationTile[] excommunicationTiles) {
		this.excommunicationTiles = excommunicationTiles;
	}
	
	public ExcommunicationTile[] getExcommunicationTiles() {
		return excommunicationTiles;
	}
	
	public PassTurnController getPassTurnController() {
		return passTurnController;
	}
	
}




