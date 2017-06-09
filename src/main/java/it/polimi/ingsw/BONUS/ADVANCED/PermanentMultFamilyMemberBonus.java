package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.FamilyMember;

public class PermanentMultFamilyMemberBonus extends PermanentFamilyMemberBonus{

	public PermanentMultFamilyMemberBonus(ArrayList<FamilyMember> familyMembers) {
		super("PermanentMultFamilyMemberBonus", familyMembers);
	}

	@Override
	public void addBonus(PermanentFamilyMemberBonus newBonus) {
		if (newBonus instanceof PermanentMultFamilyMemberBonus){
			for (FamilyMember familyMember : newBonus.getFamilyMembers()) {
				addFamilyMember(familyMember);
			}
		}
		
	}

	@Override
	public void modifyValue(FamilyMember familyMember, FamilyMember newFamilyMember) {
		familyMember.multValue(newFamilyMember.getValue());
	}
	
}
