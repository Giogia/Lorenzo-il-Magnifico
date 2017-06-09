package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.FamilyMember;

public class PermanentAddFamilyMemberBonus extends PermanentFamilyMemberBonus {
	
	public PermanentAddFamilyMemberBonus(ArrayList<FamilyMember> familyMembers) {
		super("PermanentAddFamilyMemberBonus", familyMembers);
	}

	@Override
	public void addBonus(PermanentFamilyMemberBonus newBonus) {
		if (newBonus instanceof PermanentAddFamilyMemberBonus){
			for (FamilyMember familyMember : newBonus.getFamilyMembers()) {
				addFamilyMember(familyMember);
			}
		}
		
	}
	
	@Override
	public void modifyValue(FamilyMember familyMember, FamilyMember newFamilyMember) {
		familyMember.addValue(newFamilyMember.getValue());
	}
	

}
