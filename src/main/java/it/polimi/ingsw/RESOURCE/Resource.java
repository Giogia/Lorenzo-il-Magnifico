package it.polimi.ingsw.RESOURCE;

import java.io.Serializable;

abstract public class Resource implements Serializable{
	//value is the value of the unit. value=3 -> 3 resource needed to get 1
	//amount is the number of that resource owned
	protected int amount;
	protected int value;
	protected ResourceType resourceType;
	
	public Resource(int amount, int value) {
		this.amount = amount;
		this.value = value;
	}
	
    public int getAmount() {
    	return amount;
    }
	
    public int getValue() {
    	return value;
    }
    
    public ResourceType getResourceType() {
		return resourceType;
	}
    
    public void setAmount(int amount) {
    	this.amount = amount;
    }
    
    public void setValue(int value) {
    	this.value = value;
    }
    
    public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
    
    public void addvalue(int value) {
    	this.value = this.value + value;
    }
    
    public void multvalue(int value) {
    	this.value = this.value * value;
    }
    
    public void addAmount(int value){
    	this.amount = this.amount + value;
    }
  
    public void multAmount(int value){
    	this.amount= this.amount * value;
    }

	public abstract String getDescription();
	
	public abstract Resource createClone();
    
}

