package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;

public abstract class PermanentFamilyMemberBonus extends PermanentBonus {
	protected ArrayList<FamilyMember> familyMembers;
	private String subtype;
	
	public PermanentFamilyMemberBonus(String subtype, ArrayList<FamilyMember> familyMembers) {
		super("PermanentFamilyMemberBonus");
		this.subtype = subtype;
		this.familyMembers = familyMembers;
	}
	
	public ArrayList<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}
	
	public String getSubtype() {
		return subtype;
	}
	
	
	public abstract void addBonus(PermanentFamilyMemberBonus newBonus);
	
	protected void addFamilyMember(FamilyMember newFamilyMember) {
		for (FamilyMember familyMember : familyMembers) {
			if (familyMember.getDice().getDiceColour().equals(newFamilyMember.getDice().getDiceColour())){
				modifyValue(familyMember, newFamilyMember);
				return;
			}
		}
		familyMembers.add(newFamilyMember);
	}
	
	protected abstract void modifyValue(FamilyMember familyMember, FamilyMember newFamilyMember);
}
