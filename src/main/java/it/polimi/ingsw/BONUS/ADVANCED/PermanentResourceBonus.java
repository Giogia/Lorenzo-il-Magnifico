package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.RESOURCE.Resource;

public abstract class PermanentResourceBonus extends PermanentBonus {
	protected ArrayList<Resource> resources;
	private String subtype;

	public PermanentResourceBonus(String type, ArrayList<Resource> resources) {
		super("PermanentResourceBonus");
		this.subtype = type;
		this.resources = resources;
	}
	
	public ArrayList<Resource> getResources() {
		return resources;
	}
	
	public String getSubtype() {
		return subtype;
	}
	
	public abstract PermanentResourceBonus createClone();

}
