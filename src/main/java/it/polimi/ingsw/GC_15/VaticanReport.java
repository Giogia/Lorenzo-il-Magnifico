package it.polimi.ingsw.GC_15;

import java.io.IOException;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.HANDLER.ResourcePerMissedExcommunicationHandler;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.manager.Manager;

public final class VaticanReport {
	private static VaticanReport instance;
	
	
	private VaticanReport() {

	}
	
	
	public static VaticanReport getVaticanReport(){
		if (instance == null){
			instance = new VaticanReport();
		}
		return instance;
	}
	
	
	
	public static void checkPlayersFaith(int period, Board board) throws IOException {
		Player[] players = board.getPlayers();
		ExcommunicationTile excommunicationTile = board.getExcommunicationTiles()[period-1];
		for (int i=0; i < players.length; i++) {
			if (checkFaithPoints(players[i], period)) {
				boolean notExcommunicated = Manager.askForExcommunication(players[i], excommunicationTile);
				if (notExcommunicated){
					ResourcePerMissedExcommunicationHandler.handle(players[i]);
					int faithPoints = players[i].getPersonalBoard().getResource(ResourceType.faithPoints).getAmount();
					int victoryPoints = board.getGame().getData().getFromFaithPointsToVictoryPoints()[faithPoints];
					players[i].getPersonalBoard().getResource(ResourceType.victoryPoints).addAmount(victoryPoints);
					players[i].getPersonalBoard().getResource(ResourceType.faithPoints).setAmount(0);
				}else{
					excommunicationTile.giveMalus(players[i]);
				}
			}else{
				excommunicationTile.giveMalus(players[i]);
			}
		}
	}
	
	private static boolean checkFaithPoints(Player player, int period){
		int minimumFaithPoints = player.getBoard().getGame().getData().getMinimumFaithPoints(period); 
		int playerFaithPoints = player.getPersonalBoard().getResource(ResourceType.faithPoints).getAmount();
		return playerFaithPoints - minimumFaithPoints >= 0;
	}

}
