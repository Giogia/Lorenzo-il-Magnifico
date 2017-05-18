package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.FamilyMember;

public class MultiplyFamilyMemberBonus extends FamilyMemberBonus {

	public MultiplyFamilyMemberBonus(ArrayList<FamilyMember> familyMembers) {
		super(familyMembers);
	}

	@Override
	protected void modifyValue(FamilyMember familyMember1, FamilyMember familyMember2) {
		familyMember1.multValue(familyMember2.getValue());
	}

}
