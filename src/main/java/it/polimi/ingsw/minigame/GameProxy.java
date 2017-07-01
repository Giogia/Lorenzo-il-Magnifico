package it.polimi.ingsw.minigame;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;

public class GameProxy {

	private BoardProxy boardProxy;
	private ArrayList<PlayerProxy> playerProxies;
	
	public GameProxy(Game game) {
		this.boardProxy = new BoardProxy(game.getBoard());
		for(Player player : game.getPlayers()){
			playerProxies.add(new PlayerProxy(player));
		}
	}
}
