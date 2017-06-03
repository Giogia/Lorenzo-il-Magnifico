package it.polimi.ingsw.HANDLER;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.GAME.ActionHandler;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.Manager;

public class FakeFamilyMemberHandler {
	private static boolean isThereCostBonus = false;
	private static ArrayList<Resource> cost = new ArrayList<>();

	
	public static boolean handle(Player player, HashMap<ActionZone, Integer> action, ArrayList<Resource> costBonus) throws Exception{
		if (costBonus != null){
			if (!costBonus.isEmpty()){
				turnOnBoolean(costBonus);
			}
		}
		Dice fakeDice = new Dice(DiceColour.Fake);
		ActionZone zone = Manager.askForZone(action.keySet(), player);
		fakeDice.setValue(action.get(zone)); 
		FamilyMember fakeFamilyMember = new FamilyMember(fakeDice, player);
		Position position = Manager.askForAction(fakeFamilyMember, zone);
		if(ActionHandler.handle(fakeFamilyMember,zone,position)){ //if it's set correctly then remove the fake family member
			position.removeFamilyMember(fakeFamilyMember);
			turnOffBoolean();
			return true;
		}
		turnOffBoolean();
		return false;
	}


	private static void turnOffBoolean() {
		if (isThereCostBonus){
			isThereCostBonus = false;
			cost.clear();
		}
	}


	private static void turnOnBoolean(ArrayList<Resource> costBonus) {
		isThereCostBonus = true;
		cost = costBonus;
	}


	public static boolean getBoolean() {
		return isThereCostBonus;
	}
	
	public static ArrayList<Resource> getCost() {
		return cost;
	}
	
	
}
