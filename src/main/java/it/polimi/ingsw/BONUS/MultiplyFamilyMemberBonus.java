package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.FamilyMember;

//this bonus multiplies the value of the family member
public class MultiplyFamilyMemberBonus extends FamilyMemberBonus {

	public MultiplyFamilyMemberBonus(ArrayList<FamilyMember> familyMembers) {
		super("multiplyFamilyMemberBonus", familyMembers);
	}

	@Override
	protected void modifyValue(FamilyMember familyMember1, FamilyMember familyMember2) {
		familyMember1.multValue(familyMember2.getValue());
	}

	@Override
	public String getDescription() {
		String description = "Moltiply the value to: \n";
		description = description + super.getDescription();
		return description;
	}
}
