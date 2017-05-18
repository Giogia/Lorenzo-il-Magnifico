package it.polimi.ingsw.BOARD;
import java.util.*;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.PermanentBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;

public class Position {
	protected ArrayList<FamilyMember> familyMembers;
	protected Bonus[] boardBonus;
	protected int diceRequirement;

	public Position(Bonus[] boardBonus, int diceRequirement) {
		this.boardBonus = boardBonus;
		this.diceRequirement = diceRequirement;
	}
	
	public FamilyMember getFamilyMember(int familyMember) { 
		return this.familyMembers.get(familyMember);
		}
	public Bonus getBoardBonus( int boardBonus) {
		return this.boardBonus[boardBonus];
	}
	
	public int getDiceRequirement() {
		return this.diceRequirement;
	}
	
	public void addFamilyMember(FamilyMember newfamilyMember){
		familyMembers.add(newfamilyMember);
	}
	
	public void deleteAllFamilyMember(){
		familyMembers.clear();
	}
	//TODO sistemare collegamento metodo
	private void giveImmediateBonus(Player player, ImmediateBonus bonus){
		bonus.getImmediateBonus(player);
	}
	private void givePermanentBonus(Player player, PermanentBonus bonus){
		bonus.getPermanentBonus(player);
	}
	
}
