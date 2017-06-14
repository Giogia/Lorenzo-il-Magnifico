package it.polimi.ingsw.HANDLER.GAME;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.RoundOrder;
import it.polimi.ingsw.HANDLER.ADVANCED.OrderBonusHandler;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
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
	
	public static void handle( Board board, Player[] players) throws IOException{
		for (int turn = 1; turn <= 6; turn++){
			int period = (turn+1)/2;
			System.out.println("Period " + period + "\n");
			ConnectionManagerImpl.roundBegins(board.getPlayers());
			RoundOrder roundOrder = board.getGame().getOrder();
			StartRoundHandler.handle(period, players, board);
			handleOrder(roundOrder, board);
			EndRoundHandler.handle(board, roundOrder, turn);	
		}
	}
	
	//For each action and for each turn give to Manger the player that have the right to do an action
	private static void handleOrder(RoundOrder roundOrder, Board board) throws IOException {
		for (int numberOfAction = 0; numberOfAction < 4; numberOfAction++){
			for (int i = 0; i < roundOrder.getPlayers().size(); i++){
				if (OrderBonusHandler.handle(roundOrder.getPlayer(i), numberOfAction)){
					Manager.turn(roundOrder.getPlayers().get(i));
				}
			}
		}
		ArrayList<Player> skippedPlayers = board.getGame().getSkipActionPlayers();
		for (Player player : skippedPlayers) {
			Manager.turn(player);
			board.getPassTurnController().lastMove(null);
		}
	}

}
