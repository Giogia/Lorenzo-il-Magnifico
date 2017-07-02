package it.polimi.ingsw.minigame;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;

public class GameProxy implements Serializable{

	private BoardProxy boardProxy;
	private ArrayList<PlayerProxy> playerProxies = new ArrayList<>();
	
	public GameProxy(Game game) {
		this.boardProxy = new BoardProxy(game.getBoard());
		for(Player player : game.getPlayers()){
			playerProxies.add(new PlayerProxy(player));
		}
	}

	public ArrayList<PlayerProxy> getPlayerProxies() {
		return playerProxies;
	}
	
	public BoardProxy getBoardProxy() {
		return boardProxy;
	}
}
