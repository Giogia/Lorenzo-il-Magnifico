package it.polimi.ingsw.HANDLER.GAME;

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
	//private ArrayList<Character> characters;
	//private ArrayList<Venture> ventures;
	//private ArrayList<Building> buildings;
	//private ArrayList<LeaderCard> leaderCards;
	//private ArrayList<ExcommunicationTile> excommunicationTiles;
	//private ArrayList<PersonalBonusTile> personalBonusTiles;
	//private ArrayList<ResourceBonus> councilPrivileges; TODO: da fare?
	//posti azione
	//tracciato punti fede
	//punti vittoria in base al numero di carte di un certo tipo
	//timeout prima dell'avvio della partita dopo che si raggiunge il minimo numero di giocatori
	//timeout per la mossa di un giocatore
	
	public DataFromFile(ArrayList<Territory> territories) {
		this.territories = territories;
	}
	
	public ArrayList<Territory> getTerritories() {
		return territories;
	}	
}
