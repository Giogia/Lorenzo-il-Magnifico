package it.polimi.ingsw.BOARD;
import java.util.*;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;

public class Position {
	protected ArrayList<FamilyMember> familyMembers;
	protected ArrayList<ImmediateBonus> boardBonus;
	protected int diceRequirement;

	public Position(ArrayList<ImmediateBonus> boardBonus, int diceRequirement) {
		boardBonus = boardBonus;
		diceRequirement = diceRequirement;
		familyMembers = new ArrayList<>();
	}
	
	public FamilyMember getFamilyMember(int familyMember) { 
		return this.familyMembers.get(familyMember);
		}
	
	public ArrayList<ImmediateBonus> getBoardBonus(){
		return this.boardBonus;
		
	}
	public Bonus getBoardBonus( int boardBonus) {
		return this.boardBonus.get(boardBonus);
	}
	
	public int getDiceRequirement() {
		return this.diceRequirement;
	}
	
	public void addFamilyMember(FamilyMember newFamilyMember){
		familyMembers.add(newFamilyMember);
	}
	
	public void removeFamilyMember(FamilyMember familyMember){
		familyMembers.remove(familyMember);
	}
	
	public void deleteAllFamilyMember(){
		familyMembers.clear();
	}
	
	protected void giveImmediateBonus(Player player, ImmediateBonus immediateBonus){
		immediateBonus.getImmediateBonus(player);
	}
	protected void givePermanentBonus(Player player, PermanentBonus permanentBonus){
		permanentBonus.getPermanentBonus(player);
	}
	
	public ArrayList<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}
	
}
