package it.polimi.ingsw.GC_15;

abstract public class Resource {
	
	private int amount;
	private int value;
	
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

