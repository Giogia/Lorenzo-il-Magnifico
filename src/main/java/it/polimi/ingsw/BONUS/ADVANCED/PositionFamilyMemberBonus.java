package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.HarvestArea;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;

//this bonus increase the value of a player's family member every time is positioned in a certain zone
public class PositionFamilyMemberBonus extends PermanentBonus{
	private HashMap<ActionZone, Integer> positionBonus;
	
	//If you go in one of these ActionZones your FamilyMember gain a value
	public PositionFamilyMemberBonus(HashMap<ActionZone, Integer> positionBonus){
		super("PositionFamilyMemberBonus");
		this.positionBonus = new HashMap<>();
		this.positionBonus.putAll(positionBonus);
	}
	
	public HashMap<ActionZone, Integer> getPositionBonus() {
		return positionBonus;
	}

	public Integer getValue(ActionZone actionZone, Board board) throws MyException{
		Set<ActionZone> actionZones = positionBonus.keySet();
		for (ActionZone bonusZone : actionZones) {
			if (actionZone.equals(getBoardZone(bonusZone, board))){
				return positionBonus.get(bonusZone);
			}
		}
		return 0;
	}
	
	private ActionZone getBoardZone(ActionZone actionZone, Board board) {
		ActionZone zone;
		if (actionZone instanceof Tower){
			DevelopmentCardType developmentCardType = ((Tower) actionZone).getDevelopmentCardType();
			zone = board.getTower(developmentCardType);
		}
		else if (actionZone instanceof ProductionArea){
			zone = board.getProductioArea();
		}
		else{
			zone = board.getHarvestArea();
		}
		return zone;
	}
	
	@Override
	public void getPermanentBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		for (PermanentBonus permanentBonus : playerBonus) {
			if (permanentBonus instanceof PositionFamilyMemberBonus){
				((PositionFamilyMemberBonus) permanentBonus).addBonus(this);
				return;
			}
		}
		super.getPermanentBonus(player);
	}
	
	public void addBonus(PositionFamilyMemberBonus positionFamilyMemberBonus){
		Set<ActionZone> bonusZones = positionFamilyMemberBonus.getPositionBonus().keySet();
		for (ActionZone actionZone : bonusZones) {
			ActionZone permanentZone;
			if (actionZone instanceof Tower){
				permanentZone = findTower((Tower) actionZone);
			}
			else if (actionZone instanceof HarvestArea){
				permanentZone = findHarvestArea((HarvestArea) actionZone);
			}
			else {
				permanentZone = findProductionArea((ProductionArea) actionZone);
			}
			if (permanentZone == null){
				positionBonus.put(actionZone, positionFamilyMemberBonus.getPositionBonus().get(actionZone));
			}
			else {
				Integer value = positionBonus.get(permanentZone);
				Integer newValue = value + positionFamilyMemberBonus.getPositionBonus().get(actionZone);
				positionBonus.replace(permanentZone, value, newValue);
			}
		}
	}

	public ActionZone findTower(Tower tower){
		DevelopmentCardType bonusDevelopmentCardType = tower.getDevelopmentCardType();
		for (ActionZone actionZone : positionBonus.keySet()) {
			if (actionZone instanceof Tower){
				if (((Tower) actionZone).getDevelopmentCardType().equals(bonusDevelopmentCardType)){
					return actionZone;
				}
			}
		}
		return null;
	}
	
	public ActionZone findHarvestArea(HarvestArea harvestArea){
		for (ActionZone actionZone : positionBonus.keySet()) {
			if (actionZone instanceof HarvestArea){
				return actionZone;
			}
		}
		return null;
	}
	
	public ActionZone findProductionArea(ProductionArea productionArea){
		for (ActionZone actionZone : positionBonus.keySet()) {
			if (actionZone instanceof ProductionArea){
				return actionZone;
			}
		}
		return null;
	}
	
	public String getDescription(){
		StringBuilder description = new StringBuilder();
		description.append("Increase permanently the value of your family members of: \n");
		for(Entry<ActionZone, Integer> entry : positionBonus.entrySet()){
			description.append(entry.getValue()+" if you go to "+entry.getKey().getDescription()+"\n");
		}
		return description.toString();
	}
}


