package it.polimi.ingsw.BONUS;

import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.TimeExpiredException;

//abstract class for every bonus that have only an immediate effect on the game
public abstract class ImmediateBonus extends Bonus {
	private String type; 
	
	public ImmediateBonus(String type) {
		super("immediateBonus");
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void getImmediateBonus(Player player) throws MyException, IOException, TimeExpiredException{ 
		
	}

	public abstract String getDescription();
}
