package it.polimi.ingsw.HANDLER.GAME;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.RoundOrder;
import it.polimi.ingsw.manager.Manager;

public class RoundManagerHandler {
	private static RoundManagerHandler instance;
	
	private RoundManagerHandler() {
	}
	
	public static RoundManagerHandler getRoundManagerHandler(){
		if (instance == null){
			instance = new RoundManagerHandler();
		}
		return instance;
	}
	
	public static void handle(RoundOrder roundOrder, Board board, Player[] players){
		for (int turn = 1; turn <= 6; turn++){
			int period = turn/2 +1;
			StartRoundHandler.handle(period, players, board);
			handleOrder(roundOrder);
			EndRoundHandler.handle(board, roundOrder, turn);	
		}
	}
	
	
	//For each action and for each turn give to MvcController the player that have the right to do an action
	private static void handleOrder(RoundOrder roundOrder){
		for (int numberOfAction = 0; numberOfAction < 4; numberOfAction++){
			for (int i = 0; i < roundOrder.getPlayers().size(); i++){	
				Manager.turn(roundOrder.getPlayers().get(i));
			}
		}
	}

}
