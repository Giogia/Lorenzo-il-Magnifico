package it.polimi.ingsw.BONUS;

import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.CouncilPrivilegeChoiceHandler;

public class CouncilPrivilegeBonus extends ImmediateBonus{
	private int differentCouncilPrivilege;
	
	public CouncilPrivilegeBonus(int number){
		super("councilPrivilegeBonus");
		differentCouncilPrivilege = number;
	}
	
	
	@Override
	public void getImmediateBonus(Player player) {
		CouncilPrivilegeChoiceHandler.handle(player, differentCouncilPrivilege);
	}
	
	@Override
	public String getDescription() {
		String description = differentCouncilPrivilege + " diversi Privilegi del Consiglio \n";
		return description;
	}

}
