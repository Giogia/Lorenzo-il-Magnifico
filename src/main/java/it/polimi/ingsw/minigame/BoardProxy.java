package it.polimi.ingsw.minigame;

import java.util.ArrayList;

public class BoardProxy {
	
	private RoundOrderProxy roundOrderProxy;
	private ZoneProxy zoneProxy ;
	private ArrayList<ExcommunicationTileProxy> excommunicationTileProxies;
	
	public RoundOrderProxy getRoundOrderProxy() {
		return roundOrderProxy;
	}
	
	public ZoneProxy getZoneProxy() {
		return zoneProxy;
	}
	
	public ArrayList<ExcommunicationTileProxy> getExcommunicationTileProxies() {
		return excommunicationTileProxies;
	}
	

}
