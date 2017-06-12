package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;

public class ActivationZoneBonus extends PermanentBonus {
	// If some of this boolean are false you cannot take bonus from position
	private HashMap<Tower, Boolean> towers;
	private boolean councilPalace;
	
	public ActivationZoneBonus(HashMap<Tower, Boolean> towers, boolean councilPalace) {
		super("ActivationZoneBonus");
		this.towers = new HashMap<>();
		this.towers.putAll(towers);
		this.councilPalace = councilPalace;
	}
	
	public HashMap<Tower, Boolean> getTowers() {
		return towers;
	}
	
	public boolean getCouncilPalace(){
		return councilPalace;
	}
	
	@Override
	public void getPermanentBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if(playerBonus!=null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof ActivationZoneBonus){
					((ActivationZoneBonus) permanentBonus).addBonus(this);
					return;
				}
			}
		} 
	}
	
	public void addBonus(ActivationZoneBonus activationZoneBonus){
		this.councilPalace = this.councilPalace && activationZoneBonus.getCouncilPalace();
		for (Tower bonusTower : activationZoneBonus.getTowers().keySet()) {
			DevelopmentCardType developmentCardType = bonusTower.getDevelopmentCardType();
			Tower tower = findTower(developmentCardType);
			if (tower == null){
				this.towers.put(bonusTower, activationZoneBonus.getTowers().get(bonusTower));
			}
			else{
				Boolean newValue = towers.get(tower) || activationZoneBonus.getTowers().get(bonusTower);
				this.towers.replace(tower, towers.get(tower), newValue);
			}
		}
	}

	private Tower findTower(DevelopmentCardType developmentCardType) {
		for (Tower tower : towers.keySet()) {
			if (tower.getDevelopmentCardType().equals(developmentCardType)){
				return tower;
			}
		}
		return null;
	}
	
	public String getDescription(){
		 String description = "You cannot take position bonus from : \n";
		 for(Tower tower : towers.keySet()){
			 if(towers.get(tower)==false){
				 description = description + tower.getDescription();
			 }
		 }
		 if(councilPalace==false){
			 description = description + "Council Palace";
		 }
		 return description;
	} 

}
