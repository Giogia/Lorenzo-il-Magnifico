package it.polimi.ingsw.GC_15;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.HANDLER.ResourcePerMissedExcommunicationHandler;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.manager.Manager;

//model class for vatican report and excommunication handling
public final class VaticanReport {
	private static VaticanReport instance;
	
	private final static Logger LOGGER = Logger.getLogger(VaticanReport.class.getName());
	
	private VaticanReport() {

	}
	
	
	public static VaticanReport getVaticanReport(){
		if (instance == null){
			instance = new VaticanReport();
		}
		return instance;
	}
	
	
	//ask users for excommunication if they have enough faith points
	//get them the expected bonus depending on the choice
	public static void checkPlayersFaith(int period, Board board) throws IOException {
		Player[] players = board.getPlayers();
		ExcommunicationTile excommunicationTile = board.getExcommunicationTiles()[period-1];
		for (int i=0; i < players.length; i++) {
			if (checkFaithPoints(players[i], period)) {
				boolean notExcommunicated;
				try {
					notExcommunicated = Manager.askForExcommunication(players[i], excommunicationTile);
				} catch (TimeExpiredException e) {
					notExcommunicated = true;
					LOGGER.log(Level.INFO, e.getMessage(),e);
				}
				if (notExcommunicated){
					ResourcePerMissedExcommunicationHandler.handle(players[i]);
					int faithPoints = players[i].getPersonalBoard().getResource(ResourceType.faithPoints).getAmount();
					int[] fromFaithPointsToVictoryPoints = board.getGame().getData().getFromFaithPointsToVictoryPoints();
					if (faithPoints > fromFaithPointsToVictoryPoints.length - 1){
						faithPoints = fromFaithPointsToVictoryPoints.length - 1;
					}
					players[i].getPersonalBoard().getResource(ResourceType.victoryPoints).addAmount(fromFaithPointsToVictoryPoints[faithPoints]);
					players[i].getPersonalBoard().getResource(ResourceType.faithPoints).setAmount(0);
				}else{
					excommunicationTile.giveMalus(players[i]);
				}
			}else{
				excommunicationTile.giveMalus(players[i]);
			}
		}
	}
	
	//return true if a player has enough faith points not to be excommunicated
	private static boolean checkFaithPoints(Player player, int period){
		int minimumFaithPoints = player.getBoard().getGame().getData().getMinimumFaithPoints(period); 
		int playerFaithPoints = player.getPersonalBoard().getResource(ResourceType.faithPoints).getAmount();
		return playerFaithPoints - minimumFaithPoints >= 0;
	}

}
