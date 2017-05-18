package it.polimi.ingsw.RESOURCE;

abstract public class Resource {
	//value è il valore dell'unità. value=3 -> 3 risorse per averne 1
	//amount indica la quantità della risorsa
	private int amount;
	private int value;
	
    public int getAmount() {
    	return amount;
    }
	
    public int getValue() {
    	return value;
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
    
    public void addAmount(int value){
    	this.value= this.value + value;
    }
  
    public void multAmount(int value){
    	this.value= this.value * value;
    }
}

