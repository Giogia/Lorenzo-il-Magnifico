package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.HarvestArea;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;

public class PositionFamilyMemberBonus extends PermanentBonus{
	private HashMap<ActionZone, Integer> positionBonus;
	
	//If you go in some of this ActionZone your FamilyMember gain a value
	public PositionFamilyMemberBonus(HashMap<ActionZone, Integer> positionBonus){
		super("PositionFamilyMemberBonus");
		this.positionBonus = new HashMap<>();
		this.positionBonus.putAll(positionBonus);
	}
	
	public HashMap<ActionZone, Integer> getPositionBonus() {
		return positionBonus;
	}

	public Integer getValue(ActionZone actionZone, Board board) throws Exception{
		Set<ActionZone> actionZones = positionBonus.keySet();
		for (ActionZone bonusZone : actionZones) {
			if (actionZone.equals(getBoardZone(bonusZone, board))){
				return positionBonus.get(bonusZone);
			}
		}
		throw new Exception();
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
		try{
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof PositionFamilyMemberBonus){
					((PositionFamilyMemberBonus) permanentBonus).addBonus(this);
					return;
				}
			}
		}catch (Exception e){
			
		}
		super.getPermanentBonus(player);
	}
	
	public void addBonus(PositionFamilyMemberBonus positionFamilyMemberBonus){
		Set<ActionZone> bonusZones = positionFamilyMemberBonus.getPositionBonus().keySet();
		for (ActionZone actionZone : bonusZones) {
			ActionZone permanentZone = null;
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
}
