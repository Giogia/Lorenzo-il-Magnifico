package it.polimi.ingsw.GC_15;

public class RoundOrder {

	private Player[] players;
	
	public Player[] getPlayers() {
		return players;
	}
	
	public Player getPlayer(int i) {
		return players[i];
	}
	
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	
	public void setplayer(Player player, int i){
		players[i] = player;
	}
	
}