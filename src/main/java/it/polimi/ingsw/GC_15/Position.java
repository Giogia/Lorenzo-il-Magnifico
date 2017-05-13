package it.polimi.ingsw.GC_15;
import java.util.*;

public class Position {
	protected ArrayList<FamilyMember> familyMember;
	protected Bonus[] boardBonus;
	protected int diceRequirement;

	public Position(Bonus[] boardBonus, int diceRequirement) {
		this.boardBonus = boardBonus;
		this.diceRequirement = diceRequirement;
	}
	
	public FamilyMember getFamilyMember(int familyMember) { 
		return this.familyMember.get(familyMember);
		}
	public Bonus getBoardBonus( int boardBonus) {
		return this.boardBonus[boardBonus];
	}
	
	public int getDiceRequirement() {
		return this.diceRequirement;
	}
	
	public void addFamilyMember(FamilyMember newfamilyMember){
		familyMember.add(newfamilyMember);
	}
	
	public void giveBonus(Player player, Bonus bonus){
		bonus.getImmediateBonus(player);
		bonus.getPermanentBonus(player);
	}
	
	// this method is used to delete all the family members occuping this postion at the end of a turn
	public void resetPosition(){
			this.familyMember.clear();
		}
}
