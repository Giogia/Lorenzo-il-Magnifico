package it.polimi.ingsw.GC_15;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.HANDLER.GAME.ConfigurationFileHandler;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.HANDLER.GAME.EndGameHandler;
import it.polimi.ingsw.HANDLER.GAME.RoundManagerHandler;
import it.polimi.ingsw.HANDLER.GAME.StartGameHandler;

public class Game {
	private static ArrayList<Player> players;
	private static RoundOrder roundOrder;
	private static Board board;
	private static DataFromFile data;
	
	public static void start(){
		//TODO data = ConfigurationFileHandler.set();
		StartGameHandler.handle(board);
		RoundManagerHandler.handle(roundOrder, board, players);
		EndGameHandler.handle(board);
	}
	
	public static DataFromFile getData() {
		return data;
	}
}
