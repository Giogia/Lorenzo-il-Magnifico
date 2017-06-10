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

public class Game implements Serializable{
	private Player[] players;
	private RoundOrder roundOrder;
	private Board board;
	private DataFromFile data;
	private ArrayList<Player> skipActionPlayers = new ArrayList<>();
	
	public void start(int numberOfPlayers) throws RemoteException{
		try {
			players = new Player[numberOfPlayers];
			ArrayList<Color> colors = new ArrayList<>();
			for (Color color : Color.values()) {
				colors.add(color);
			}
			for (int i = 0; i < numberOfPlayers; i++) {
				String nameChoosen = askName(i);
				Color colorChoosen = askColor(i, colors);
				players[i] = new Player(nameChoosen, colorChoosen);
				
				//delete the color choosen from the available colors
				colors.remove(colorChoosen);
			}
			ConnectionManagerImpl.addPlayers(players);//do the binding between player and his view in connectionManagerImpl
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
		
		StartGameHandler.handle(board);
		RoundManagerHandler.handle(board, players);
		EndGameHandler.handle(board);
	}
	
	private String askName(int numberOfPlayer) throws RemoteException{
		return ConnectionManagerImpl.askName(numberOfPlayer);
	}
	
	private Color askColor(int numberOfPlayer, ArrayList<Color> availableColors) throws RemoteException {
		String[] colors = new String[availableColors.size()];
		for(int counter = 0; counter < availableColors.size(); counter++){
			colors[counter] = availableColors.get(counter).toString().toLowerCase();
		}
		int colorChoiced = ConnectionManagerImpl.askColor(numberOfPlayer, colors) -1;//due the size of the array
		return Color.values()[colorChoiced];
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
