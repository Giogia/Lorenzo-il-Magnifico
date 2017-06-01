package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;

public abstract class FamilyMemberBonus extends ImmediateBonus{
	protected ArrayList<FamilyMember> familyMembers;
	private String subtype;
	
	public FamilyMemberBonus(String type, ArrayList<FamilyMember> familyMembers){
		super("familyMemberBonus");
		subtype=type;
		this.familyMembers = new ArrayList<>();
		this.familyMembers.addAll(familyMembers);
	}

	public String getSubtype() {
		return subtype;
	}
	
	@Override
	public void getImmediateBonus(Player player) {
		ArrayList<FamilyMember> playerFamilyMembers = player.getFamilyMembers();
		for (FamilyMember playerFamilyMember : playerFamilyMembers) {
			for (FamilyMember familyMember : familyMembers) {
				if (compareDice(playerFamilyMember, familyMember)){
					modifyValue(playerFamilyMember, familyMember);
				}
			}
		}
	}
	
	protected boolean compareDice(FamilyMember familyMember1, FamilyMember familyMember2) {
		return familyMember1.getDice().equals(familyMember2.getDice());
	}
	
	protected abstract void modifyValue(FamilyMember familyMember1, FamilyMember familyMember2);

	
	
	public ArrayList<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}
	
	@Override
	public String getDescription() {
		String description = "";
		for (FamilyMember familyMember : familyMembers) {
			description = description + familyMember.getDescription() + "\n";
		}
		return description;
	}
}
