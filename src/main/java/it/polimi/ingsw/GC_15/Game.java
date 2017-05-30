package it.polimi.ingsw.GC_15;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.HANDLER.GAME.EndGameHandler;
import it.polimi.ingsw.HANDLER.GAME.RoundManagerHandler;
import it.polimi.ingsw.HANDLER.GAME.StartGameHandler;

public class Game {
	private static Player[] players;
	private static RoundOrder roundOrder;
	private static Board board;
	
	public Game(int numberOfPlayers) {
		players = new Player[numberOfPlayers];
		roundOrder = new RoundOrder();
		board = new Board();
	}
	
	public static void handle(){
		StartGameHandler.handle(board);
		RoundManagerHandler.handle(roundOrder, board, players);
		EndGameHandler.handle(board);
	}

	public static Board getBoard() {
		return board;
	}
	
	public static Player[] getPlayers() {
		return players;
	}
}
