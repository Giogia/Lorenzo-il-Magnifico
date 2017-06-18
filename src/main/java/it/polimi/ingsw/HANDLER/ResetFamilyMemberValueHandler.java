package it.polimi.ingsw.HANDLER;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;

public class ResetFamilyMemberValueHandler {
	
	public static void handle(Player player){
		ArrayList<FamilyMember> playerFamilyMembers = player.getFamilyMembers();
		for (FamilyMember familyMember : playerFamilyMembers) {
			familyMember.setValue(familyMember.getDice().getValue());
		}
	}

}
