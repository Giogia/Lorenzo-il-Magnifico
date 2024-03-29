package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.FamilyMember;

//this bonus sets the value of a family member to a specific number
public class FamilyMemberValueBonus extends FamilyMemberBonus{

	public FamilyMemberValueBonus(ArrayList<FamilyMember> familyMembers) {
		super("familyMemberValueBonus", familyMembers);
	}

	@Override
	protected void modifyValue(FamilyMember familyMember1, FamilyMember familyMember2) {
		familyMember1.setValue(familyMember2.getValue());
	}
	
	@Override
	public String getDescription() {
		String description = "Modify the value of your family members: \n";
		description = description + super.getDescription();
		return description;
	}
}
