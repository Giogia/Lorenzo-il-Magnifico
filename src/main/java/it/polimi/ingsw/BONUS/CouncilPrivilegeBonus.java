package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player;

public class CouncilPrivilegeBonus extends ImmediateBonus{
	private int differentCouncilPrivilege;
	private ArrayList<ResourceBonus> resourceBonus;
	
	public CouncilPrivilegeBonus(int number, ArrayList<ResourceBonus> resource){
		super("councilPrivilegeBonus");
		differentCouncilPrivilege = number;
		resourceBonus.equals(resource);
	}
	
	/*public void getImmediateBonus(Player player){
		while (differentCouncilPrivilege > 0){
			player.choosePrivilegeCouncil(resourceBonus);
			TODO DA SISTEMARE!!!!!
			Si può fare in due modi:
			- Si chiama un'altra funzione, che chiama choosePrivilegeCouncil e ritorna a questa funzione il ResourceBonus che ha scelto
			- Fa tutto il player e questa funzione da al Player il numero di privilegi da scegliere e la lista
			
		}
	}*/

}
