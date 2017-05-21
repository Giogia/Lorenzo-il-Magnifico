package it.polimi.ingsw.HANDLER;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.CARD.Venture;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.PersonalBonusTile;

//classe contenitrice di tutti i dati che vengono caricati ad inizio gioco da file
public class DataFromFile {
	private ArrayList<Territory> territories;
	private ArrayList<Character> characters;
	//private ArrayList<Venture> ventures;
	//private ArrayList<Building> buildings;
	//private ArrayList<ExcommunicationTile> excommunicationTiles;
	//private ArrayList<PersonalBonusTile> personalBonusTiles;
	//posti azione
	//tracciato punti fede
	//timeout prima dell'avvio della partita dopo che si raggiunge il minimo numero di giocatori
	//timeout per la mossa di un giocatore
	
	public DataFromFile(ArrayList<Territory> territories, ArrayList<Character> characters) {
		this.territories = territories;
		this.characters = characters;
	}
	
	public ArrayList<Territory> getTerritories() {
		return territories;
	}
	
	public ArrayList<Character> getCharacters() {
		return characters;
	}
}
