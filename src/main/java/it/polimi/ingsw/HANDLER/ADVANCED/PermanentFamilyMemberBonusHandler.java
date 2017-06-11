package it.polimi.ingsw.HANDLER.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.PermanentAddFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentMultFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentValueFamilyMemberBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;

public class PermanentFamilyMemberBonusHandler {
	
	public static void handle(Player player){
		handleValueBonus(player);
		handleMultBonus(player);
		handleAddBonus(player);
	}

	private static void handleAddBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null && !playerBonus.isEmpty()){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof PermanentAddFamilyMemberBonus){
					ArrayList<FamilyMember> bonusFamilyMembers = ((PermanentAddFamilyMemberBonus) permanentBonus).getFamilyMembers();
					for (FamilyMember bonusFamilyMember : bonusFamilyMembers) {
						for (FamilyMember familyMember : player.getFamilyMembers()) {
							if (familyMember.getDice().getDiceColour().equals(bonusFamilyMember.getDice().getDiceColour())){
								familyMember.addValue(bonusFamilyMember.getValue());
							}
						}
					}
				}
			}
		}
	}

	private static void handleMultBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null && !playerBonus.isEmpty()){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof PermanentMultFamilyMemberBonus){
					ArrayList<FamilyMember> bonusFamilyMembers = ((PermanentMultFamilyMemberBonus) permanentBonus).getFamilyMembers();
					for (FamilyMember bonusFamilyMember : bonusFamilyMembers) {
						for (FamilyMember familyMember : player.getFamilyMembers()) {
							if (familyMember.getDice().getDiceColour().equals(bonusFamilyMember.getDice().getDiceColour())){
								familyMember.multValue(bonusFamilyMember.getValue());
							}
						}
					}
				}
			}
		}
	}

	private static void handleValueBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null && !playerBonus.isEmpty()){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof PermanentValueFamilyMemberBonus){
					ArrayList<FamilyMember> bonusFamilyMembers = ((PermanentValueFamilyMemberBonus) permanentBonus).getFamilyMembers();
					for (FamilyMember bonusFamilyMember : bonusFamilyMembers) {
						for (FamilyMember familyMember : player.getFamilyMembers()) {
							if (familyMember.getDice().getDiceColour().equals(bonusFamilyMember.getDice().getDiceColour())){
								familyMember.addValue(bonusFamilyMember.getValue());
							}
						}
					}
				}
			}
		}
	}

}
