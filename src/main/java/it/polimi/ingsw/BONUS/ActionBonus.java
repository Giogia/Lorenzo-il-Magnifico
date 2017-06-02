package it.polimi.ingsw.BONUS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.FakeFamilyMemberHandler;
import it.polimi.ingsw.RESOURCE.Resource;

public class ActionBonus extends ImmediateBonus {
	private HashMap<ActionZone, Integer> action;
	private ArrayList<Resource> resources;
	
	public ActionBonus(HashMap<ActionZone, Integer> actionMap, ArrayList<Resource> resources){
		super("actionBonus");
		this.resources = resources;
		action = new HashMap<>();
		action.putAll(actionMap);
	}
	
	public void getImmediateBonus(Player player) throws Exception{
		Set<ActionZone> actionZones = action.keySet();
		for (ActionZone zone : actionZones) {
			int value = action.get(zone);
			FakeFamilyMemberHandler.handle(player, zone, value, resources);
		}
	}

	@Override
	public String getDescription() {
		String description = "Puoi fare un'azione in: \n";
		Set<ActionZone> actionZones = action.keySet();
		for (ActionZone actionZone : actionZones) {
			description = description + actionZone.getDescription() + "del valore di " + action.get(actionZone) + "\n";
		}
		if (resources != null){
			description = description + "Hai un bonus di: \n";
			for (Resource resource : resources) {
				description = description + resource.getDescription();
			}
		}
		return description;
	}

}
