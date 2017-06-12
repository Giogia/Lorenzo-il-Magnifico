package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public abstract class PermanentFamilyMemberBonus extends PermanentBonus {
	protected ArrayList<FamilyMember> familyMembers;
	private String subsubtype;
	
	public PermanentFamilyMemberBonus(String subtype, ArrayList<FamilyMember> familyMembers) {
		super("PermanentFamilyMemberBonus");
		subsubtype = subtype;
		this.familyMembers = familyMembers;
	}
	
	public ArrayList<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}
	
	public String getSubsubtype() {
		return subsubtype;
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
	
	public String getDescription(){
		String description = null;
		for(FamilyMember familyMember: familyMembers){
			description = description + familyMember.getValue()+ "the value of your " + familyMember.getDice().getDiceColour().name() + " family member \n";
		}
		return description;
	}
}
