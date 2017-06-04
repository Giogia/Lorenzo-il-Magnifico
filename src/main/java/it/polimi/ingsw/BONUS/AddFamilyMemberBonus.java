package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.FamilyMember;

public class AddFamilyMemberBonus extends FamilyMemberBonus{

	public AddFamilyMemberBonus(ArrayList<FamilyMember> familyMembers) {
		super("addFamilyMemberBonus", familyMembers);
	}

	@Override
	protected void modifyValue(FamilyMember familyMember1, FamilyMember familyMember2) {
		familyMember1.addValue(familyMember2.getValue());
	}
	
	@Override
	public String getDescription() {
		String description = "Add to: \n";
		description = description + super.getDescription();
		return description;
	}

}
