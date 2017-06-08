package it.polimi.ingsw.GC_15;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.HANDLER.GAME.ConfigurationFileHandler;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.HANDLER.GAME.EndGameHandler;
import it.polimi.ingsw.HANDLER.GAME.RoundManagerHandler;
import it.polimi.ingsw.HANDLER.GAME.StartGameHandler;
import it.polimi.ingsw.manager.ConnectionManagerImpl;

public class Game {
	private static Player[] players;
	private static RoundOrder roundOrder;
	private static Board board;
	private static DataFromFile data;
	
	public static void start(int numberOfPlayers) throws RemoteException{
		try {
			players = new Player[numberOfPlayers];
			
			//Color[] availableColors = new Color[Color.values().length];
			//availableColors = Color.values();
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
			ConnectionManagerImpl.addPlayers();//do the binding between player and his view in connectionManagerImpl
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
	
	private static String askName(int numberOfPlayer) throws RemoteException{
		return ConnectionManagerImpl.askName(numberOfPlayer);
	}
	
	private static Color askColor(int numberOfPlayer, ArrayList<Color> availableColors) throws RemoteException {
		String[] colors = new String[availableColors.size()];
		for(int counter = 0; counter < availableColors.size(); counter++){
			colors[counter] = availableColors.get(counter).toString().toLowerCase();
		}
		int colorChoiced = ConnectionManagerImpl.askColor(numberOfPlayer, colors) -1;//due the size of the array
		return Color.values()[colorChoiced];
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
