package it.polimi.ingsw.minigame;

import it.polimi.ingsw.GC_15.Game;

public class Create {
	private static Create instance = new Create();
	private Create() { } //singleton 
	
	public static Create getInstance() {
		return instance;
	}
	
	public GameProxy createGameProxy(Game game){
		return new GameProxy(game);
	}
}
