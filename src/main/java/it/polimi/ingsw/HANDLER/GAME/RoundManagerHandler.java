package it.polimi.ingsw.HANDLER.GAME;

import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.RoundOrder;
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
	
	public static void handle( Board board, Player[] players) throws RemoteException{
		for (int turn = 1; turn <= 6; turn++){
			int period = (turn+1)/2;
			System.out.println("Period " + period + "\n");
			ConnectionManagerImpl.roundBegins();
			RoundOrder roundOrder = Game.getOrder();
			ArrayList<Player> orderPlayers = roundOrder.getPlayers();
			StartRoundHandler.handle(period, players, board);
			//giveInitialInformations();
			handleOrder(roundOrder);
			EndRoundHandler.handle(board, roundOrder, turn);	
		}
	}
	
	/*public static void giveInitialInformations(){
		String toSend="";
		for(DevelopmentCardType type : DevelopmentCardType.values()){
			TowerFloor[] towerFloor = Game.getBoard().getTower(type).getPositions();
			toSend += type.toString().toUpperCase() + "\n";
			for (TowerFloor position : towerFloor ){
				toSend += "------------\n";
				toSend += position.getDescription() + "\n";
			}
			toSend += "\n";
		}
		ConnectionManager.giveInitialInformations(toSend);
	}*/
	
	//For each action and for each turn give to Manger the player that have the right to do an action
	private static void handleOrder(RoundOrder roundOrder) throws RemoteException {
		for (int numberOfAction = 0; numberOfAction < 4; numberOfAction++){
			for (int i = 0; i < roundOrder.getPlayers().size(); i++){
				Manager.turn(roundOrder.getPlayers().get(i));
			}
		}
	}

}
