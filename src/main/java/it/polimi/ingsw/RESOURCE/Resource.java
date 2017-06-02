package it.polimi.ingsw.RESOURCE;

abstract public class Resource {
	//value è il valore dell'unità. value=3 -> 3 risorse per averne 1
	//amount indica la quantità della risorsa
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
	
	public abstract Resource clone();
    
}

