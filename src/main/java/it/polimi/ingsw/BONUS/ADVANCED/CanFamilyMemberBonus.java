package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.BOARD.CouncilPalace;
import it.polimi.ingsw.BOARD.HarvestArea;
import it.polimi.ingsw.BOARD.Market;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;

public class CanFamilyMemberBonus extends PermanentBonus{
	//di default false: se è occupato il familyMember non può andarci
	private boolean occupiedYet;
	//particolare scomunica: di default true
	private HashMap<Zone, Boolean> canGoTo;
	
	public CanFamilyMemberBonus(boolean occupiedYet, HashMap<Zone, Boolean> canGoTo) {
		super("CanFamilyMemberBonus");
		this.occupiedYet = occupiedYet;
		this.canGoTo = new HashMap<>();
		this.canGoTo.putAll(canGoTo);
	}
	

	public HashMap<Zone, Boolean> getCanGoTo() {
		return canGoTo;
	}
	
	public boolean getOccupiedYet() {
		return occupiedYet;
	}
	
	@Override
	public void getPermanentBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof CanFamilyMemberBonus){
					((CanFamilyMemberBonus) permanentBonus).addBonus(this);
					return;
				}
			}
		}
		super.getPermanentBonus(player);
	}
	
	public void addBonus(CanFamilyMemberBonus canFamilyMemberBonus){
		this.occupiedYet = this.occupiedYet || canFamilyMemberBonus.getOccupiedYet();
		for (Zone zone : canFamilyMemberBonus.getCanGoTo().keySet()) {
			Zone bonusZone = findZone(zone);
			if (bonusZone == null){
				canGoTo.put(zone, canFamilyMemberBonus.getCanGoTo().get(zone));
			}
			else{
				Boolean oldValue = canGoTo.get(bonusZone);
				Boolean newValue = oldValue && canFamilyMemberBonus.getCanGoTo().get(zone);
				canGoTo.replace(bonusZone, oldValue, newValue);
			}
		}
	}


	private Zone findCouncilPalace() {
		for (Zone zone : canGoTo.keySet()) {
			if (zone instanceof CouncilPalace){
				return zone;
			}
		}
		return null;
	}


	private Zone findMarket() {
		for (Zone zone : canGoTo.keySet()) {
			if (zone instanceof Market){
				return zone;
			}
		}
		return null;
	}


	private Zone findProduction() {
		for (Zone zone : canGoTo.keySet()) {
			if (zone instanceof ProductionArea){
				return zone;
			}
		}
		return null;
	}


	private Zone findHarvest() {
		for (Zone zone : canGoTo.keySet()) {
			if (zone instanceof HarvestArea){
				return zone;
			}
		}
		return null;
	}


	private Zone findTower(DevelopmentCardType developmentCardType) {
		for (Zone zone : canGoTo.keySet()) {
			if (zone instanceof Tower){
				if (((Tower) zone).getDevelopmentCardType().equals(developmentCardType)){
					return zone;
				}
			}
		}
		return null;
	}
	
	public Zone findZone(Zone zone){
		Zone foundZone;
		if (zone instanceof Tower){
			foundZone = findTower(((Tower) zone).getDevelopmentCardType());
		}
		else if (zone instanceof HarvestArea){
			foundZone = findHarvest();
		}
		else if (zone instanceof ProductionArea){
			foundZone = findProduction();
		}
		else if (zone instanceof Market){
			foundZone = findMarket();
		}
		else {
			foundZone = findCouncilPalace();
		}
		return foundZone;
	}
	
	public String getDescription(){
		String description = "";
		if(canGoTo!=null){
			for(Zone zone : canGoTo.keySet()){
				if(canGoTo.get(zone)==false){
				description = description + zone.getDescription()+"\n";
				}
			}
		}
		if(description!="")
			description = "Your family members cannot go to: \n" + description;
		if(occupiedYet==true)
			description = description + "you can go to any position even if occupied \n";
	return description;
	}
	
}
