package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;

public class PermanentValueFamilyMemberBonus extends PermanentFamilyMemberBonus {

	public PermanentValueFamilyMemberBonus(ArrayList<FamilyMember> familyMembers) {
		super("PermanentValueFamilyMemberBonus", familyMembers);
	}
	
	@Override
	public void getPermanentBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		for (PermanentBonus permanentBonus : playerBonus) {
			if (permanentBonus instanceof PermanentValueFamilyMemberBonus){
				((PermanentValueFamilyMemberBonus) permanentBonus).addBonus(this);
				return;
			}
		}
		super.getPermanentBonus(player);
	}

	@Override
	public void addBonus(PermanentFamilyMemberBonus newBonus) {
		if (newBonus instanceof PermanentValueFamilyMemberBonus){
			for (FamilyMember familyMember : newBonus.getFamilyMembers()) {
				addFamilyMember(familyMember);
			}
		}
	}
	
	@Override
	protected void modifyValue(FamilyMember familyMember, FamilyMember newFamilyMember) {
		familyMember.setValue(newFamilyMember.getValue());
	}

}
