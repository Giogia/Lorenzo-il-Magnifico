package it.polimi.ingsw.GC_15;

import java.util.ArrayList;

public class RoundOrder {
	private ArrayList<Player> players;
	
	public RoundOrder() {
		players = new ArrayList<>();
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public void setplayer(Player player, int i){
		players.set(i, player);
	}
	
}