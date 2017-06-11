package it.polimi.ingsw.GC_15;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.HANDLER.GAME.ConfigurationFileHandler;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.HANDLER.GAME.EndGameHandler;
import it.polimi.ingsw.HANDLER.GAME.RoundManagerHandler;
import it.polimi.ingsw.HANDLER.GAME.StartGameHandler;
import it.polimi.ingsw.manager.ConnectionManagerImpl;

public class Game implements Serializable, Runnable{
	private String name;
	private Player[] players;
	private RoundOrder roundOrder;
	private Board board;
	private DataFromFile data;
	private ArrayList<Player> skipActionPlayers = new ArrayList<>();
	

	public void run(){

		try {
			data = ConfigurationFileHandler.getData();
			board = new Board(this);
			for (int i=0; i < players.length; i++){
				players[i].setBoard(board);
			}
			roundOrder = new RoundOrder();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			StartGameHandler.handle(board);
			RoundManagerHandler.handle(board, players);
			EndGameHandler.handle(board);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public DataFromFile getData() {
		return data;
	}

	public Board getBoard() {
		return board;
	}
	
	public Player[] getPlayers() {
		return players;
	}

	public void setOrder(ArrayList<Player> players2) {
		roundOrder.setPlayers(players2);
	}

	public ArrayList<Player> getRoundOrder() {
		return roundOrder.getPlayers();
	}
	
	public RoundOrder getOrder(){
		return roundOrder;
	}
	
	public void addSkippedPlayer(Player player){
		skipActionPlayers.add(player);
	}
	
	public ArrayList<Player> getSkipActionPlayers() {
		return skipActionPlayers;
	}
}
