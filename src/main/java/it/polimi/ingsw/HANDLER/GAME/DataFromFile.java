package it.polimi.ingsw.HANDLER.GAME;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.*;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.PersonalBonusTile;

//classe contenitrice di tutti i dati che vengono caricati ad inizio gioco da file
public class DataFromFile {
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
	//TODO: posti azione
	//private static ArrayList<ImmediateBonus>[] towerTerritoryPositionBonus;//to 0 position there is bonus of floor 0 
	//private static ArrayList<ImmediateBonus>[] towerCharacterPositionBonus;
	//private static ArrayList<ImmediateBonus>[] towerBuildingPositionBonus;
	//private static ArrayList<ImmediateBonus>[] towerVenturePositionBonus;
	//private static ArrayList<ImmediateBonus>[] marketPositionBonus;
	//private static ArrayList<ImmediateBonus> councilPalacePositionBonus;
	//TODO
	//private static ArrayList<LeaderCard> leaderCards; think to them later
	//condizioni di attivazione di tutte le zone
	//timeout prima dell'avvio della partita dopo che si raggiunge il minimo numero di giocatori
	//timeout per la mossa di un giocatore
	public DataFromFile(ArrayList<Territory> territories, ArrayList<Character> characters, ArrayList<Venture> ventures,
			ArrayList<Building> buildings, ArrayList<ExcommunicationTile> excommunicationTiles,
			ArrayList<PersonalBonusTile> personalBonusTiles, ArrayList<ResourceBonus> councilPrivileges,
			int[] fromFaithPointsToVictoryPoints, int[] fromMilitaryPointsToVictoryPoints,
			int[] victoryPointsForTerritoryCard, int[] victoryPointsForCharacterCard, int[] militaryRequirement) {
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
	//TODO
	//private static ArrayList<LeaderCard> leaderCards; think to them later
	//condizioni di attivazione di tutte le zone
	//timeout prima dell'avvio della partita dopo che si raggiunge il minimo numero di giocatori
	//timeout per la mossa di un giocatore
	

	public void lastCards(DevelopmentCardType developmentCardType, int period,
			ArrayList<DevelopmentCard> developmentCards) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<DevelopmentCard> getBuildingsForPeriod(int period) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<DevelopmentCard> getCharactersForPeriod(int period) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<DevelopmentCard> getTerritoriesForPeriod(int period) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<DevelopmentCard> getVenturesForPeriod(int period) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] getVictoryPointsPerCard(DevelopmentCardType developmentCardType) {
		if (developmentCardType.equals(DevelopmentCardType.character)){
			return getVictoryPointsForCharacterCard();
		} else if (developmentCardType.equals(DevelopmentCardType.territory)){
			return getVictoryPointsForTerritoryCard();
		}
		else return null;
	}
}
