package it.polimi.ingsw.GC_15;

import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.manager.Manager;

public final class VaticanReport {
	private static VaticanReport instance;
	private static ExcommunicationTile excommunicationTiles[] = new ExcommunicationTile[3];
	
	
	private VaticanReport(ExcommunicationTile excommunicationTiles[]) {
		VaticanReport.excommunicationTiles=excommunicationTiles;
	}
	
	
	public static VaticanReport getVaticanReport(ExcommunicationTile excommunicationTiles[]){
		if (instance == null){
			instance = new VaticanReport(excommunicationTiles);
		}
		return instance;
	}
	
	public static ExcommunicationTile[] getExcommunicationTiles() {
		return excommunicationTiles;
	}
	
	public static void setExcommunicationTiles(ExcommunicationTile[] excommunicationTiles) {
		VaticanReport.excommunicationTiles = excommunicationTiles;
	}
	
	public static void checkPlayersFaith(int period) {
		Player[] players = Game.getPlayers();
		for (int i=0; i < players.length; i++) {
			if (checkFaithPoints(players[i], period)) {
				if (Manager.askForExcommunication(players[i], excommunicationTiles[period])){
					int faithPoints = players[i].getPersonalBoard().getResource(ResourceType.faithPoints).getAmount();
					int victoryPoints = Game.getData().getFromFaithPointsToVictoryPoints()[faithPoints];
					players[i].getPersonalBoard().getResource(ResourceType.victoryPoints).addAmount(victoryPoints);
					players[i].getPersonalBoard().getResource(ResourceType.faithPoints).setAmount(0);
				}else{
					excommunicationTiles[period].giveMalus(players[i]);
				}
			}else{
				excommunicationTiles[period].giveMalus(players[i]);
			}
		}
	}
	
	private static boolean checkFaithPoints(Player player, int period){
		int minimumFaithPoints = Game.getData().getMinimumFaithPoints(period); //TODO è configurabile anche questo
		int playerFaithPoints = player.getPersonalBoard().getResource(ResourceType.faithPoints).getAmount();
		return playerFaithPoints - minimumFaithPoints >= 0; //<-punti fede del player
	}

}
