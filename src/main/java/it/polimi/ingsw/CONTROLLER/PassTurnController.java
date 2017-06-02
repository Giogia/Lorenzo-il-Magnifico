package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.GC_15.Player;

//tell last action to pass turn handler

public class PassTurnController implements Controller {
	private static Player lastMove;
	
	public static void lastMove(Player player){
		lastMove = player;
	}

	public static Player getLastMove() {
		return lastMove;
	}
}
