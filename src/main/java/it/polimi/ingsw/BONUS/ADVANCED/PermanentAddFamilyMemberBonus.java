package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.AddFamilyMemberBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;

public class PermanentAddFamilyMemberBonus extends PermanentFamilyMemberBonus {
	
	public PermanentAddFamilyMemberBonus(ArrayList<FamilyMember> familyMembers) {
		super("PermanentAddFamilyMemberBonus", familyMembers);
	}

	
	@Override
	public void getPermanentBonus(Player player) {
		AddFamilyMemberBonus immediateBonus = new AddFamilyMemberBonus(familyMembers);
		immediateBonus.getImmediateBonus(player);
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		for (PermanentBonus permanentBonus : playerBonus) {
			if (permanentBonus instanceof PermanentAddFamilyMemberBonus){
				((PermanentAddFamilyMemberBonus) permanentBonus).addBonus(this);
				return;
			}
		}
		super.getPermanentBonus(player);
	}
	
	
	@Override
	public void addBonus(PermanentFamilyMemberBonus newBonus) {
		if (newBonus instanceof PermanentAddFamilyMemberBonus){
			for (FamilyMember familyMember : newBonus.getFamilyMembers()) {
				addFamilyMember(familyMember);
			}
		}
		
	}
	
	@Override
	public void modifyValue(FamilyMember familyMember, FamilyMember newFamilyMember) {
		familyMember.addValue(newFamilyMember.getValue());
	}
	
	public String getDescription(){
		String description = "increase permanently of: \n";
		description = description + super.getDescription();
		return description;		
	}

}
