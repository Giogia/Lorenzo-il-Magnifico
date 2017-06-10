package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.GC_15.Player;

//tell last action to pass turn handler

public class PassTurnController implements Controller {
	private Player lastMove;
	
	public PassTurnController() {
		lastMove = null;
	}
	
	public void lastMove(Player player){
		lastMove = player;
	}

	public Player getLastMove() {
		return lastMove;
	}
}
