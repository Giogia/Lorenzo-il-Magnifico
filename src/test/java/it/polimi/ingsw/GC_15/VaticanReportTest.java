package it.polimi.ingsw.GC_15;

import static org.junit.Assert.*;

import org.junit.Test;

public class VaticanReportTest {

	@Test
	public void excommunicationTilesTest() {
		
		ExcommunicationTile[] excommunicationTiles = new ExcommunicationTile[3];
		VaticanReport.getVaticanReport(excommunicationTiles);
		
		assertArrayEquals(excommunicationTiles,VaticanReport.getExcommunicationTiles());
			
		ExcommunicationTile[] excommunicationTiles2 = new ExcommunicationTile[3];
		VaticanReport.setExcommunicationTiles(excommunicationTiles2);
				
		assertArrayEquals(excommunicationTiles2,VaticanReport.getExcommunicationTiles());
	}
	
	@Test
	public void checkFaithTest() {
		
		ExcommunicationTile[] excommunicationTiles = new ExcommunicationTile[3];
		VaticanReport.getVaticanReport(excommunicationTiles);
		
	//TODO finire test una volta finito Vatican report
	}
	

}
