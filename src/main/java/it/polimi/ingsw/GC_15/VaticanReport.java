package it.polimi.ingsw.GC_15;

public class VaticanReport {
	private ExcommunicationTile excommunicationTiles[];
	
	public VaticanReport(ExcommunicationTile excommunicationTiles[]) {//TODO: gestire l'eccezione nel caso di array di lunghezza diversa?
		this.excommunicationTiles=excommunicationTiles;
	}
	
	public void checkPlayersFaith(Player players[], int period) {
		for (int i=0; i < players.length; i++) {
			if (checkFaithPoints(players[i], period)) {
				if ( players[i].wantsToRestartFaithPoints() ){
					//TODO: dare punti vittoria in base a quanti faithpoints e poi azzera i punti fede
				}else{
					excommunicationTiles[period].giveMalus(players[i]);
				}
			}else{
				excommunicationTiles[period].giveMalus(players[i]);
			}
		}
	}
	
	private boolean checkFaithPoints(Player player, int period){
		int minimumFaithPoints=0;//TODO: ricavare tali faith points in base al period!
		return minimumFaithPoints < //punti fede del player
	}
}
