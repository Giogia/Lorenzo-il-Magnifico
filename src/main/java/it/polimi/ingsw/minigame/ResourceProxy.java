package it.polimi.ingsw.minigame;

import java.io.Serializable;

import it.polimi.ingsw.RESOURCE.ResourceType;
import javafx.beans.property.*;

public class ResourceProxy implements Serializable {
	private SimpleStringProperty val;
	private ResourceType type;
	private int value;
	
	public ResourceProxy(ResourceType type, int value) {
		this.type = type;
		this.value = value;
	}
	
	public SimpleStringProperty getVal() {
		return val;
	}
	
	public ResourceType getType() {
		return type;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setVal() {
		if (this.val == null) this.val = new SimpleStringProperty();
		this.val.set(Integer.toString(value));
	}
	
	public void setVal(int val) {
		this.val.set(Integer.toString(val));
	}
	
	
}
