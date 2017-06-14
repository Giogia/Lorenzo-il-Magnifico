package it.polimi.ingsw.BONUS;

import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;

public abstract class ImmediateBonus extends Bonus {
	private String type; 
	
	public ImmediateBonus(String type) {
		super("immediateBonus");
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void getImmediateBonus(Player player) throws MyException, IOException{ 
		
	}

	public abstract String getDescription();
}
