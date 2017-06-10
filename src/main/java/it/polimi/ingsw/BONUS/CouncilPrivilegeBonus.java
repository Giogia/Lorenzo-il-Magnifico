package it.polimi.ingsw.BONUS;

import java.rmi.RemoteException;

import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.CouncilPrivilegeChoiceHandler;

public class CouncilPrivilegeBonus extends ImmediateBonus{
	private int differentCouncilPrivilege;
	
	public CouncilPrivilegeBonus(int number){
		super("councilPrivilegeBonus");
		differentCouncilPrivilege = number;
	}
	
	
	@Override
	public void getImmediateBonus(Player player) throws MyException, RemoteException{
		CouncilPrivilegeChoiceHandler.handle(player, differentCouncilPrivilege);
	}
	
	@Override
	public String getDescription() {
		if(differentCouncilPrivilege==1){
			return "1 council privilege \n";
		}
		else{
			String description = differentCouncilPrivilege + " different council privileges \n";
			return description;
		}
		
	}

}
