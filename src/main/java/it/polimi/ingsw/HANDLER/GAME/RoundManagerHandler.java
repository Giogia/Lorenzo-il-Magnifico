package it.polimi.ingsw.HANDLER.GAME;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.RoundOrder;
import it.polimi.ingsw.HANDLER.ResetFamilyMemberValueHandler;
import it.polimi.ingsw.HANDLER.ADVANCED.OrderBonusHandler;
import it.polimi.ingsw.HANDLER.ADVANCED.PermanentFamilyMemberBonusHandler;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.manager.Manager;
import it.polimi.ingsw.manager.User;

//set the order of the players every round
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
			System.out.println("Period " + period);
			RoundOrder roundOrder = board.getGame().getOrder();
			StartRoundHandler.handle(period, players, board);
	
			ConnectionManagerImpl.roundBegins(board.getPlayers());
			handleOrder(roundOrder, board);
			EndRoundHandler.handle(board, roundOrder, turn);	
		}
	}
	
	//For each action and for each turn give to Manger the player that have the right to do an action
	private static void handleOrder(RoundOrder roundOrder, Board board) throws IOException {
		//print on server the order of the game
		for (Player pl : roundOrder.getPlayers()) {
			System.out.println(pl.getName());
		}
		
		for (int numberOfAction = 0; numberOfAction < 4; numberOfAction++){
			//each action clear player disconnected
			for (int i = 0; i < roundOrder.getPlayers().size(); i++){
				Player player = roundOrder.getPlayer(i);
				if (OrderBonusHandler.handle(player, numberOfAction)){
					Manager.startTurn(player, roundOrder.getPlayers());
				}
			}
		}
		ArrayList<Player> skippedPlayers = board.getGame().getSkipActionPlayers();
		for (Player player : skippedPlayers) {
			board.getPassTurnController().lastMove(null);
			Manager.startTurn(player, roundOrder.getPlayers());
		}
		clearUsersDisconnected(ConnectionManagerImpl.getConnectionManager().getUsersDisconnected(), roundOrder.getPlayers());//each turn clear 
	}

	//every end turn remove players of this game. So, disconnected players can reconnect
	private static void clearUsersDisconnected(List<User> usersDisconnected, ArrayList<Player> players) throws RemoteException {
		for (Player player : players) {
			User user = ConnectionManagerImpl.getConnectionManager().findUserByPlayer(player);
			if (usersDisconnected.contains(user)){
				usersDisconnected.remove(user);
			}
		}
		
	}

}
