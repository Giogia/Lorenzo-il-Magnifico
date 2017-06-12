package it.polimi.ingsw.HANDLER.GAME;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.*;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.PersonalBonusTile;

//classe contenitrice di tutti i dati che vengono caricati ad inizio gioco da file
public class DataFromFile implements Serializable{
	private ArrayList<Territory> territories;
	private ArrayList<Character> characters;
	private ArrayList<Venture> ventures;
	private ArrayList<Building> buildings;
	private ArrayList<ExcommunicationTile> excommunicationTiles;
	private ArrayList<PersonalBonusTile> personalBonusTiles;
	private ArrayList<ResourceBonus> councilPrivileges;
	private int[] fromFaithPointsToVictoryPoints; //tracciato punti fede
	//in first position: tot victoryPoints to first player, second position tot to second player and so on
	private  int[] fromMilitaryPointsToVictoryPoints;  
	//punti vittoria in base al numero di carte di un certo tipo
	private  int[] victoryPointsForTerritoryCard;
	private  int[] victoryPointsForCharacterCard;
	//military points requirement for territory card
	private  int[] militaryRequirement;
	//posti azione
	private ArrayList<ArrayList<ImmediateBonus>> towerTerritoryPositionBonus;//to 0 position there is bonus of floor 0 
	private ArrayList<ArrayList<ImmediateBonus>> towerCharacterPositionBonus;
	private ArrayList<ArrayList<ImmediateBonus>> towerBuildingPositionBonus;
	private ArrayList<ArrayList<ImmediateBonus>> towerVenturePositionBonus;
	private ArrayList<ArrayList<ImmediateBonus>> marketPositionBonus;
	private ArrayList<ImmediateBonus> councilPalaceBonus;
	private int[] harvestAreaPositionBonus;
	private int[] productionAreaPositionBonus;
	private ArrayList<LeaderCard> leaderCards;
	//condizioni di attivazione di tutte le zone
	//timeout prima dell'avvio della partita dopo che si raggiunge il minimo numero di giocatori
	//timeout per la mossa di un giocatore

	public DataFromFile(ArrayList<Territory> territories, ArrayList<Character> characters, ArrayList<Venture> ventures,
			ArrayList<Building> buildings, ArrayList<ExcommunicationTile> excommunicationTiles,
			ArrayList<PersonalBonusTile> personalBonusTiles, ArrayList<ResourceBonus> councilPrivileges,
			int[] fromFaithPointsToVictoryPoints, int[] fromMilitaryPointsToVictoryPoints,
			int[] victoryPointsForTerritoryCard, int[] victoryPointsForCharacterCard, int[] militaryRequirement,
			ArrayList<ArrayList<ImmediateBonus>> towerTerritoryPositionBonus, ArrayList<ArrayList<ImmediateBonus>> towerCharacterPositionBonus,
			ArrayList<ArrayList<ImmediateBonus>> towerBuildingPositionBonus,
			ArrayList<ArrayList<ImmediateBonus>> towerVenturePositionBonus, ArrayList<ArrayList<ImmediateBonus>> marketPositionBonus,
			ArrayList<ImmediateBonus> councilPalaceBonus, int[] harvestAreaPositionBonus,
			int[] productionAreaPositionBonus, ArrayList<LeaderCard> leaderCards) {
		super();
		this.territories = territories;
		this.characters = characters;
		this.ventures = ventures;
		this.buildings = buildings;
		this.excommunicationTiles = excommunicationTiles;
		this.personalBonusTiles = personalBonusTiles;
		this.councilPrivileges = councilPrivileges;
		this.fromFaithPointsToVictoryPoints = fromFaithPointsToVictoryPoints;
		this.fromMilitaryPointsToVictoryPoints = fromMilitaryPointsToVictoryPoints;
		this.victoryPointsForTerritoryCard = victoryPointsForTerritoryCard;
		this.victoryPointsForCharacterCard = victoryPointsForCharacterCard;
		this.militaryRequirement = militaryRequirement;
		this.towerTerritoryPositionBonus = towerTerritoryPositionBonus;
		this.towerCharacterPositionBonus = towerCharacterPositionBonus;
		this.towerBuildingPositionBonus = towerBuildingPositionBonus;
		this.towerVenturePositionBonus = towerVenturePositionBonus;
		this.marketPositionBonus = marketPositionBonus;
		this.councilPalaceBonus = councilPalaceBonus;
		this.harvestAreaPositionBonus = harvestAreaPositionBonus;
		this.productionAreaPositionBonus = productionAreaPositionBonus;
		this.leaderCards = leaderCards;
	}
	
	public ArrayList<LeaderCard> getLeaderCards() {
		return leaderCards;
	}
	
	public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
		this.leaderCards = leaderCards;
	}
	
	public void setTerritories(ArrayList<Territory> territories) {
		this.territories = territories;
	}


	public void setCharacters(ArrayList<Character> characters) {
		this.characters = characters;
	}


	public void setVentures(ArrayList<Venture> ventures) {
		this.ventures = ventures;
	}


	public void setBuildings(ArrayList<Building> buildings) {
		this.buildings = buildings;
	}
	
	public ArrayList<Territory> getTerritories() {
		return territories;
	}
	public ArrayList<Character> getCharacters() {
		return characters;
	}
	public ArrayList<Venture> getVentures() {
		return ventures;
	}
	public ArrayList<Building> getBuildings() {
		return buildings;
	}
	public ArrayList<ExcommunicationTile> getExcommunicationTiles() {
		return excommunicationTiles;
	}
	public ArrayList<PersonalBonusTile> getPersonalBonusTiles() {
		return personalBonusTiles;
	}
	public ArrayList<ResourceBonus> getCouncilPrivileges() {
		return councilPrivileges;
	}
	public int[] getFromFaithPointsToVictoryPoints() {
		return fromFaithPointsToVictoryPoints;
	}
	public int[] getFromMilitaryPointsToVictoryPoints() {
		return fromMilitaryPointsToVictoryPoints;
	}
	public int[] getVictoryPointsForTerritoryCard() {
		return victoryPointsForTerritoryCard;
	}
	public int[] getVictoryPointsForCharacterCard() {
		return victoryPointsForCharacterCard;
	}
	public int[] getMilitaryRequirement() {
		return militaryRequirement;
	}
	
	public ArrayList<ArrayList<ImmediateBonus>> getTowerTerritoryPositionBonus() {
		return towerTerritoryPositionBonus;
	}
	
	public ArrayList<ArrayList<ImmediateBonus>> getTowerCharacterPositionBonus() {
		return towerCharacterPositionBonus;
	}
	
	public ArrayList<ArrayList<ImmediateBonus>> getTowerBuildingPositionBonus() {
		return towerBuildingPositionBonus;
	}
	
	public ArrayList<ArrayList<ImmediateBonus>> getTowerVenturePositionBonus() {
		return towerVenturePositionBonus;
	}
	
	public ArrayList<ArrayList<ImmediateBonus>> getMarketPositionBonus() {
		return marketPositionBonus;
	}
	
	public ArrayList<ImmediateBonus> getCouncilPalaceBonus() {
		return councilPalaceBonus;
	}
	
	public int[] getHarvestAreaPositionBonus() {
		return harvestAreaPositionBonus;
	}

	public int[] getProductionAreaPositionBonus() {
		return productionAreaPositionBonus;
	}
	
	public void lastCards(DevelopmentCardType developmentCardType, int period,
			ArrayList<DevelopmentCard> developmentCards) {
		if (developmentCardType.equals(DevelopmentCardType.building)){
			for (DevelopmentCard developmentCard : developmentCards) {
				buildings.add((Building) developmentCard);
			}
		}
		else if (developmentCardType.equals(DevelopmentCardType.character)){
			for (DevelopmentCard developmentCard : developmentCards) {
				characters.add((Character) developmentCard);
			}
		}
		else if (developmentCardType.equals(DevelopmentCardType.venture)){
			for (DevelopmentCard developmentCard : developmentCards) {
				ventures.add((Venture) developmentCard);
			}
		}
		else{
			for (DevelopmentCard developmentCard : developmentCards) {
				territories.add((Territory) developmentCard);
			}
		}
	}

	public ArrayList<DevelopmentCard> getBuildingsForPeriod(int period) {
		ArrayList<DevelopmentCard> periodBuildings =  new ArrayList<>();
		for (Building building : buildings) {
			if (building.period == period){
				periodBuildings.add(building);
			}
		}
		return periodBuildings;
	}

	public ArrayList<DevelopmentCard> getCharactersForPeriod(int period) {
		ArrayList<DevelopmentCard> periodCharacters = new ArrayList<>();
		for (Character character : characters) {
			if (character.period == period){
				periodCharacters.add(character);
			}
		}
		return periodCharacters;
	}

	public ArrayList<DevelopmentCard> getTerritoriesForPeriod(int period) {
		ArrayList<DevelopmentCard> periodTerritories = new ArrayList<>();
		for (Territory territory : territories) {
			if (territory.period == period){
				periodTerritories.add(territory);
			}
		}
		return periodTerritories;
	}

	public ArrayList<DevelopmentCard> getVenturesForPeriod(int period) {
		ArrayList<DevelopmentCard> periodVentures = new ArrayList<>();
		for (Venture venture : ventures) {
			if (venture.period == period){
				periodVentures.add(venture);
			}
		}
		return periodVentures;
	}

	public int[] getVictoryPointsPerCard(DevelopmentCardType developmentCardType) {
		if (developmentCardType.equals(DevelopmentCardType.character)){
			return getVictoryPointsForCharacterCard();
		} else if (developmentCardType.equals(DevelopmentCardType.territory)){
			return getVictoryPointsForTerritoryCard();
		}
		else return null;
	}
	public ArrayList<ArrayList<ImmediateBonus>> getTowerPositionBonus(DevelopmentCardType developmentCardType) {
		switch (developmentCardType) {
		case territory:
			return getTowerTerritoryPositionBonus();
		
		case character:
			return getTowerCharacterPositionBonus();
			
		case venture:
			return getTowerVenturePositionBonus();
		default: //case building:
			return getTowerBuildingPositionBonus();
		}
	}


	public int getMinimumFaithPoints(int period) {
		// TODO Auto-generated method stub
		return 0;
	} 
}
