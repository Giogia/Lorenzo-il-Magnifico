package it.polimi.ingsw.BOARD;

public abstract class ActionZone extends Zone{
	private String subType;
	
	public ActionZone(String subType){
		super("actionZone");
		this.subType = subType;
	}
	
	public String getSubType() {
		return subType;
	}
}
