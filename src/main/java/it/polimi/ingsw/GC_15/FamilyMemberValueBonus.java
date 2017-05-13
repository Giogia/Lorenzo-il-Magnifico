package it.polimi.ingsw.GC_15;

import java.util.ArrayList;

public class FamilyMemberValueBonus implements ImmediateBonus, PermanentBonus{
	private ArrayList<FamilyMember> familyMembers;
	
	public FamilyMemberValueBonus(ArrayList<FamilyMember> familyMembers) {
		this.familyMembers = new ArrayList<FamilyMember>();
		this.familyMembers.addAll(familyMembers);
	}

	@Override
	public void getPermanentBonus(Player player) {
		player.addPermanentBonus(this);
		
	}

	@Override //Prova tutte le combinazioni di carte e per ogni combinazione, se compareDice è true, fa copyValue
	public void getImmediateBonus(Player player) { 
		ArrayList<FamilyMember> playerFamilyMembers = player.getFamilyMember;
		for (FamilyMember familyMember : familyMembers) {
			for (FamilyMember playerFamilyMember : playerFamilyMembers) {
				if(compareDice(familyMember, playerFamilyMember)){
					copyValue(familyMember, playerFamilyMember);
				}
			}
		}
	}
	
	//Confronta i colori dei dadi dei due familiari, se sono uguali ritorna true
	public boolean compareDice(FamilyMember familyMember1, FamilyMember familyMember2){
		return (familyMember1.getDiceColour().equals(familyMember2.getDiceColour()));
	}
	
	
	//Il primo FamilyMember copia il valore del secondo FamilyMember
	public void copyValue(FamilyMember copier, FamilyMember copied){
		int value = copied.getValue();
		copier.setValue();
	}

}
