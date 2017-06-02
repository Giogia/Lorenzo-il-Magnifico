package it.polimi.ingsw.GC_15;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.HANDLER.GAME.ConfigurationFileHandler;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.HANDLER.GAME.EndGameHandler;
import it.polimi.ingsw.HANDLER.GAME.RoundManagerHandler;
import it.polimi.ingsw.HANDLER.GAME.StartGameHandler;
import it.polimi.ingsw.manager.ConnectionManager;

public class Game {
	private static Player[] players;
	private static RoundOrder roundOrder;
	private static Board board;
	private static DataFromFile data;
	
	public static void start(int numberOfPlayers){
		//setta i vari attributi di game
		try {
			players = new Player[numberOfPlayers];
			//TODO
			players[0] = new Player("Michele" , Color.BLUE);
			players[1] = new Player("Giovanni" , Color.RED);
			ConnectionManager.addPlayers();
			data = ConfigurationFileHandler.getData();
			board = new Board();
			roundOrder = new RoundOrder();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StartGameHandler.handle(board);
		RoundManagerHandler.handle(board, players);
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

	public static void setOrder(ArrayList<Player> players2) {
		roundOrder.setPlayers(players2);
	}

	public static ArrayList<Player> getRoundOrder() {
		return roundOrder.getPlayers();
	}
	
	public static RoundOrder getOrder(){
		return roundOrder;
	}
}
