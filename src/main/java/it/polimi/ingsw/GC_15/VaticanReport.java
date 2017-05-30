package it.polimi.ingsw.GC_15;

public final class VaticanReport {
	private static VaticanReport instance;
	private static ExcommunicationTile excommunicationTiles[];
	
	
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
	
	public static void checkPlayersFaith(Player players[], int period) {
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
	
	private static boolean checkFaithPoints(Player player, int period){
		int minimumFaithPoints=0;//TODO: ricavare tali faith points in base al period!
		return minimumFaithPoints < 0; //<-punti fede del player
	}


	public static void check() {
		// TODO Auto-generated method stub
		
	}
}
