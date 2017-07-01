package it.polimi.ingsw.minigame;

import java.util.ArrayList;

public class BoardProxy {
	
	private RoundOrderProxy roundOrderProxy = new RoundOrderProxy();
	private ZoneProxy zoneProxy;
	private ArrayList<ExcommunicationTileProxy> excommunicationTileProxies;
	
	public BoardProxy(ZoneProxy zoneProxy) {
		this.zoneProxy = zoneProxy;
	}
	
	public RoundOrderProxy getRoundOrderProxy() {
		return roundOrderProxy;
	}
	
	public ZoneProxy getZoneProxy() {
		return zoneProxy;
	}
	
	public ArrayList<ExcommunicationTileProxy> getExcommunicationTileProxies() {
		return excommunicationTileProxies;
	}
	
	public void setZoneProxy(ZoneProxy zoneProxy) {
		this.zoneProxy = zoneProxy;
	}
	
	public void setExcommunicationTileProxies(ArrayList<ExcommunicationTileProxy> excommunicationTileProxies) {
		this.excommunicationTileProxies = excommunicationTileProxies;
	}
	

}
