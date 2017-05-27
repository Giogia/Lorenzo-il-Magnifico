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
	private static DataFromFile instance;
	
	private DataFromFile(){
	}
	
	public static DataFromFile getDataFromFile(){
		if (instance == null){
			instance= new DataFromFile();
		}
		return instance;
	}
	
	public static ArrayList<Territory> getTerritories() {
		return territories;
	}
	public static void setTerritories(ArrayList<Territory> territories) {
		DataFromFile.territories = territories;
	}
	public static ArrayList<Character> getCharacters() {
		return characters;
	}
	public static void setCharacters(ArrayList<Character> characters) {
		DataFromFile.characters = characters;
	}
	public static ArrayList<Venture> getVentures() {
		return ventures;
	}
	public static void setVentures(ArrayList<Venture> ventures) {
		DataFromFile.ventures = ventures;
	}
	public static ArrayList<Building> getBuildings() {
		return buildings;
	}
	public static void setBuildings(ArrayList<Building> buildings) {
		DataFromFile.buildings = buildings;
	}
	public static ArrayList<ExcommunicationTile> getExcommunicationTiles() {
		return excommunicationTiles;
	}
	public static void setExcommunicationTiles(ArrayList<ExcommunicationTile> excommunicationTiles) {
		DataFromFile.excommunicationTiles = excommunicationTiles;
	}
	public static ArrayList<PersonalBonusTile> getPersonalBonusTiles() {
		return personalBonusTiles;
	}
	public static void setPersonalBonusTiles(ArrayList<PersonalBonusTile> personalBonusTiles) {
		DataFromFile.personalBonusTiles = personalBonusTiles;
	}
	public static ArrayList<ResourceBonus> getCouncilPrivileges() {
		return councilPrivileges;
	}
	public static void setCouncilPrivileges(ArrayList<ResourceBonus> councilPrivileges) {
		DataFromFile.councilPrivileges = councilPrivileges;
	}
	public static int[] getFromFaithPointsToVictoryPoints() {
		return fromFaithPointsToVictoryPoints;
	}
	public static void setFromFaithPointsToVictoryPoints(int[] fromFaithPointsToVictoryPoints) {
		DataFromFile.fromFaithPointsToVictoryPoints = fromFaithPointsToVictoryPoints;
	}
	public static int[] getFromMilitaryPointsToVictoryPoints() {
		return fromMilitaryPointsToVictoryPoints;
	}
	public static void setFromMilitaryPointsToVictoryPoints(int[] fromMilitaryPointsToVictoryPoints) {
		DataFromFile.fromMilitaryPointsToVictoryPoints = fromMilitaryPointsToVictoryPoints;
	}
	public static int[] getVictoryPointsForTerritoryCard() {
		return victoryPointsForTerritoryCard;
	}
	public static void setVictoryPointsForTerritoryCard(int[] victoryPointsForTerritoryCard) {
		DataFromFile.victoryPointsForTerritoryCard = victoryPointsForTerritoryCard;
	}
	public static int[] getVictoryPointsForMilitaryCard() {
		return victoryPointsForMilitaryCard;
	}
	public static void setVictoryPointsForMilitaryCard(int[] victoryPointsForMilitaryCard) {
		DataFromFile.victoryPointsForMilitaryCard = victoryPointsForMilitaryCard;
	}
	public static int[] getMilitaryRequirement() {
		return militaryRequirement;
	}
	public static void setMilitaryRequirement(int[] militaryRequirement) {
		DataFromFile.militaryRequirement = militaryRequirement;
	}
	
	private static ArrayList<Territory> territories;
	private static ArrayList<Character> characters;
	private static ArrayList<Venture> ventures;
	private static ArrayList<Building> buildings;
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
}
