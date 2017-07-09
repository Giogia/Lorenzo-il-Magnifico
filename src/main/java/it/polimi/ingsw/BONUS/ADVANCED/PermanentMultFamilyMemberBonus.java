package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.MultiplyFamilyMemberBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;

//this bonus multiplies by a certain factor a player's family member until the end of the game
public class PermanentMultFamilyMemberBonus extends PermanentFamilyMemberBonus{

	public PermanentMultFamilyMemberBonus(ArrayList<FamilyMember> familyMembers) {
		super("PermanentMultFamilyMemberBonus", familyMembers);
	}

	@Override
	public void getPermanentBonus(Player player) {
		MultiplyFamilyMemberBonus immediateBonus = new MultiplyFamilyMemberBonus(familyMembers);
		immediateBonus.getImmediateBonus(player);
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		for (PermanentBonus permanentBonus : playerBonus) {
			if (permanentBonus instanceof PermanentMultFamilyMemberBonus){
				((PermanentMultFamilyMemberBonus) permanentBonus).addBonus(this);
				return;
			}
		}
		super.getPermanentBonus(player);
	}
	
	
	@Override
	public void addBonus(PermanentFamilyMemberBonus newBonus) {
		if (newBonus instanceof PermanentMultFamilyMemberBonus){
			for (FamilyMember familyMember : newBonus.getFamilyMembers()) {
				addFamilyMember(familyMember);
			}
		}
		
	}

	@Override
	public void modifyValue(FamilyMember familyMember, FamilyMember newFamilyMember) {
		familyMember.multValue(newFamilyMember.getValue());
	}
	
	public String getDescription(){
		String description = "Mult permanently for: \n";
		description = description + super.getDescription();
		return description;		
	}
}
