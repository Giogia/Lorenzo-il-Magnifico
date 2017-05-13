package it.polimi.ingsw.RESOURCE;

abstract public class Resource {
	
	private int amount;
	private int value;
	
	public Resource() {
		this.amount=0;
		this.value=1;
	}
	
    public int getAmount() {
    	return this.amount;
    }
	
    public int getValue() {
    	return this.value;
    }
    
    public void setAmount(int amount) {
    	this.amount = amount;
    }
    
    public void setValue(int value) {
    	this.value = value;
    }
    
    public void addvalue(int value) {
    	this.value = this.value + value;
    }
    
    public void multvalue(int value) {
    	this.value = this.value * value;
    }
    
}

