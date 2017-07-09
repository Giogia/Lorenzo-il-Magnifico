package it.polimi.ingsw.BONUS;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.HANDLER.FakeFamilyMemberHandler;
import it.polimi.ingsw.RESOURCE.Resource;

//this bonus lets the player take another card on the board
public class ActionBonus extends ImmediateBonus {
	private HashMap<ActionZone, Integer> action;
	private ArrayList<Resource> resources;
	
	public ActionBonus(HashMap<ActionZone, Integer> actionMap, ArrayList<Resource> resources){
		super("actionBonus");
		this.resources = resources;
		action = new HashMap<>();
		action.putAll(actionMap);
	}
	
	public void getImmediateBonus(Player player) throws MyException, IOException, TimeExpiredException{
		FakeFamilyMemberHandler.handle(player, action, resources);
	}

	@Override
	public String getDescription() {
		StringBuilder description = new StringBuilder();
		description.append("you can do an action in: \n");
		Set<ActionZone> actionZones = action.keySet();
		for (ActionZone actionZone : actionZones) {
			description.append(actionZone.getDescription() + " whose value is " + action.get(actionZone) + "\n");
		}
		if (resources != null){
			description.append("has a bonus of: \n");
			for (Resource resource : resources) {
				description.append(resource.getDescription());
			}
		}
		return description.toString();
	}

}
