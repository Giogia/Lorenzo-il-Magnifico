package it.polimi.ingsw.GC_15;

import java.io.FileNotFoundException;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.HANDLER.GAME.ConfigurationFileHandler;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.HANDLER.GAME.EndGameHandler;
import it.polimi.ingsw.HANDLER.GAME.RoundManagerHandler;
import it.polimi.ingsw.HANDLER.GAME.StartGameHandler;

public class Game {
	private static Player[] players;
	private static RoundOrder roundOrder;
	private static Board board;
	private static DataFromFile data;
	
	public static void start(){
		//setta i vari attributi di game
		try {
			data = ConfigurationFileHandler.getData();
			board = new Board();
			roundOrder = new RoundOrder();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		StartGameHandler.handle(board);
		RoundManagerHandler.handle(roundOrder, board, players);
		EndGameHandler.handle(board);
	}
	
	public static DataFromFile getData() {
		return data;
	}

	public static Board getBoard() {
		return board;
	}
	
	public static Player[] getPlayers() {
		return players;
	}
}
