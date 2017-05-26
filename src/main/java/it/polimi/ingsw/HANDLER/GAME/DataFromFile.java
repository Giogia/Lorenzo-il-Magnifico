package it.polimi.ingsw.HANDLER.GAME;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.*;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.PersonalBonusTile;

//classe contenitrice di tutti i dati che vengono caricati ad inizio gioco da file
public class DataFromFile {
	private static ArrayList<Territory> territories;
	private static ArrayList<Character> characters;
	private static ArrayList<Venture> ventures;
	private static ArrayList<Building> buildings;
	//private static ArrayList<LeaderCard> leaderCards; think to them later
	private static ArrayList<ExcommunicationTile> excommunicationTiles;
	private static ArrayList<PersonalBonusTile> personalBonusTiles;
	private static ArrayList<ResourceBonus> councilPrivileges;
	private static int[] fromFaithPointsToVictoryPoints; //tracciato punti fede
	//in first position: tot victoryPoints to first player, second position tot to second player and so on
	private static int[] fromMilitaryPointsToVictoryPoints;  
	//punti vittoria in base al numero di carte di un certo tipo
	private static int[] victoryPointsForTerritoryCard;
	private static int[] victoryPointsForMilitaryCard;
	//military points requirement for territory card
	private static int[] militaryRequirement;
	//posti azione
	private static ArrayList<ImmediateBonus>[] towerTerritoryPositionBonus;//to 0 position there is bonus of floor 0 
	private static ArrayList<ImmediateBonus>[] towerCharacterPositionBonus;
	private static ArrayList<ImmediateBonus>[] towerBuildingPositionBonus;
	private static ArrayList<ImmediateBonus>[] towerVenturePositionBonus;
	private static ArrayList<ImmediateBonus>[] marketPositionBonus;
	private static ArrayList<ImmediateBonus> councilPalacePositionBonus;
	
	//TODO
	//condizioni di attivazione di tutte le zone
	//timeout prima dell'avvio della partita dopo che si raggiunge il minimo numero di giocatori
	//timeout per la mossa di un giocatore
	
	public static ArrayList<Territory> getTerritories() {
		return territories;
	}
	public static ArrayList<Character> getCharacters() {
		return characters;
	}
	public static ArrayList<Venture> getVentures() {
		return ventures;
	}
	public static ArrayList<Building> getBuildings() {
		return buildings;
	}
	public static ArrayList<ExcommunicationTile> getExcommunicationTiles() {
		return excommunicationTiles;
	}
	public static ArrayList<PersonalBonusTile> getPersonalBonusTiles() {
		return personalBonusTiles;
	}
	public static ArrayList<ResourceBonus> getCouncilPrivileges() {
		return councilPrivileges;
	}
	public static int[] getFromFaithPointsToVictoryPoints() {
		return fromFaithPointsToVictoryPoints;
	}
	public static int[] getFromMilitaryPointsToVictoryPoints() {
		return fromMilitaryPointsToVictoryPoints;
	}
	public static int[] getVictoryPointsForTerritoryCard() {
		return victoryPointsForTerritoryCard;
	}
	public static int[] getVictoryPointsForMilitaryCard() {
		return victoryPointsForMilitaryCard;
	}
	public static int[] getMilitaryRequirement() {
		return militaryRequirement;
	}
	public static ArrayList<ImmediateBonus>[] getTowerTerritoryPositionBonus() {
		return towerTerritoryPositionBonus;
	}
	public static ArrayList<ImmediateBonus>[] getTowerCharacterPositionBonus() {
		return towerCharacterPositionBonus;
	}
	public static ArrayList<ImmediateBonus>[] getTowerBuildingPositionBonus() {
		return towerBuildingPositionBonus;
	}
	public static ArrayList<ImmediateBonus>[] getTowerVenturePositionBonus() {
		return towerVenturePositionBonus;
	}
	public static ArrayList<ImmediateBonus>[] getMarketPositionBonus() {
		return marketPositionBonus;
	}
	public static ArrayList<ImmediateBonus> getCouncilPalacePositionBonus() {
		return councilPalacePositionBonus;
	}
}
