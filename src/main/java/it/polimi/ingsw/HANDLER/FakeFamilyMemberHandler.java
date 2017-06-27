package it.polimi.ingsw.HANDLER;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.HANDLER.GAME.ActionHandler;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.Manager;

public class FakeFamilyMemberHandler {
	private static boolean isThereCostBonus = false;
	private static ArrayList<Resource> cost = new ArrayList<>();

	
	public static boolean handle(Player player, HashMap<ActionZone, Integer> action, ArrayList<Resource> costBonus) throws MyException, IOException, TimeExpiredException{
		//check if there is a CostBonus associated to ActionBonus
		//if there is, turn on the boolean and apply costBonus
		if (costBonus != null){
			if (!costBonus.isEmpty()){
				turnOnBoolean(costBonus);
			}
		}
		Dice fakeDice = new Dice(DiceColour.Fake);
		ArrayList<ActionZone> actionZones = new ArrayList<>();
		for (ActionZone actionZone : action.keySet()) {
			actionZones.add(actionZone);
		}
		ActionZone zone = Manager.askForZone(actionZones, player);
		fakeDice.setValue(action.get(zone)); 
		FamilyMember fakeFamilyMember = new FamilyMember(fakeDice, player);
		Position position = Manager.askForAction(fakeFamilyMember, zone, player.getBoard());
		zone = getBoardZone(zone, player.getBoard());
		//if it's set correctly then remove the fake family member
		if (ActionHandler.handle(fakeFamilyMember,zone,position)){
			position.removeFamilyMember(fakeFamilyMember);
			turnOffBoolean();
			return true;
		}
		player.getBoard().getPassTurnController().lastMove(player);
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
	
	private static ActionZone getBoardZone(ActionZone zone, Board board) {
		if (zone instanceof Tower){
			DevelopmentCardType developmentCardType = ((Tower) zone).getDevelopmentCardType();
			zone = board.getTower(developmentCardType);
		}
		else if (zone instanceof ProductionArea){
			zone = board.getProductioArea();
		}
		else{
			zone = board.getHarvestArea();
		}
		return zone;
	}
	
}
