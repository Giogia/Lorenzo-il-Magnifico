package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.HashMap;
import java.util.Set;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Game;

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

	public Integer getValue(ActionZone actionZone) throws Exception{
		Set<ActionZone> actionZones = positionBonus.keySet();
		for (ActionZone bonusZone : actionZones) {
			if (actionZone.equals(getBoardZone(bonusZone))){
				return positionBonus.get(bonusZone);
			}
		}
		throw new Exception();
	}
	
	private ActionZone getBoardZone(ActionZone actionZone) {
		ActionZone zone;
		if (actionZone instanceof Tower){
			DevelopmentCardType developmentCardType = ((Tower) actionZone).getDevelopmentCardType();
			zone = Game.getBoard().getTower(developmentCardType);
		}
		else if (actionZone instanceof ProductionArea){
			zone = Game.getBoard().getProductioArea();
		}
		else{
			zone = Game.getBoard().getHarvestArea();
		}
		return zone;
	}
}
