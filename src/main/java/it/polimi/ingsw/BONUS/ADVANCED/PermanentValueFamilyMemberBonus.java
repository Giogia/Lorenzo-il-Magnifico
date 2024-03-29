package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.FamilyMemberValueBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;

//this bonus sets the value of a player's family member until the end of the game
public class PermanentValueFamilyMemberBonus extends PermanentFamilyMemberBonus {

	public PermanentValueFamilyMemberBonus(ArrayList<FamilyMember> familyMembers) {
		super("PermanentValueFamilyMemberBonus", familyMembers);
	}
	
	@Override
	public void getPermanentBonus(Player player) {
		FamilyMemberValueBonus immediateBonus = new FamilyMemberValueBonus(familyMembers);
		immediateBonus.getImmediateBonus(player);
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		for (PermanentBonus permanentBonus : playerBonus) {
			if (permanentBonus instanceof PermanentValueFamilyMemberBonus){
				((PermanentValueFamilyMemberBonus) permanentBonus).addBonus(this);
				return;
			}
		}
		super.getPermanentBonus(player);
	}

	@Override
	public void addBonus(PermanentFamilyMemberBonus newBonus) {
		if (newBonus instanceof PermanentValueFamilyMemberBonus){
			for (FamilyMember familyMember : newBonus.getFamilyMembers()) {
				addFamilyMember(familyMember);
			}
		}
	}
	
	@Override
	protected void modifyValue(FamilyMember familyMember, FamilyMember newFamilyMember) {
		familyMember.setValue(newFamilyMember.getValue());
	}

	public String getDescription(){
		String description = "Set permanently at: \n";
		description = description + super.getDescription();
		return description;		
	}
}
