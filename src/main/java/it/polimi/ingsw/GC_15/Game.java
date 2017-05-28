package it.polimi.ingsw.GC_15;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.HANDLER.GAME.EndGameHandler;
import it.polimi.ingsw.HANDLER.GAME.RoundManagerHandler;
import it.polimi.ingsw.HANDLER.GAME.StartGameHandler;

public class Game {
	private static ArrayList<Player> players;
	private static RoundOrder roundOrder;
	private static Board board;
	
	
	
	public static void start(){
		StartGameHandler.handle(board);
		RoundManagerHandler.handle(roundOrder, board, players);
		EndGameHandler.handle(board);
	}

	public static Board getBoard() {
		return board;
	}
}
