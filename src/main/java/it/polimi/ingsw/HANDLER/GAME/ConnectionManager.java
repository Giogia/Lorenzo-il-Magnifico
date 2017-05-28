package it.polimi.ingsw.HANDLER.GAME;

import java.util.HashMap;

import it.polimi.ingsw.GC_15.Player;

public class ConnectionManager {
	private static ConnectionManager instance;
	private static HashMap<Player, View> playersView;
	//TODO forse hashmap per le connessioni
	
	
	public static ConnectionManager getConnectionManager() {
		if (instance == null){
			instance = new ConnectionManager();
		}
		return instance;
	}
	
	private ConnectionManager() {
	}
	
	//TODO al posto di tutti i TODO vanno messi i metodi per gestire le connessioni

	public static HashMap<Player, View> getPlayersView() {
		return playersView;
	}
	
	public static void addPlayerView(Player player, View view){
		playersView.put(player, view);
	}
	
	public static View getView(Player player){
		return playersView.get(player);
	}

	public static void sendToView(Player player, String message) {
		//TODO
		playersView.get(player).print(message);
	}
	
	public static int sendToViewForInt(Player player, String message){
		//TODO
		int choice = playersView.get(player).printForInt(message);
		return choice;
	}
	
	public static String sendToViewForString(Player player, String message){
		//TODO
		String choice = playersView.get(player).printForString(message);
		return choice;
	}
}
