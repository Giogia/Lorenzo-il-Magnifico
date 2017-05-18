package it.polimi.ingsw.BOARD;
import java.util.*;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;

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
	
	public boolean addFamilyMember(FamilyMember newfamilyMember, int index){
		if(familyMember.get(index)!=null){
			return false;
		}
		familyMember.add(index, newfamilyMember);
		return true;
	}
	
	public void deleteAllFamilyMember(){
		familyMember.clear();
	}
	//TODO sistemare collegamento metodo
	public void giveBonus(Player player, Bonus bonus){
		bonus.getImmediateBonus(player);
		bonus.getPermanentBonus(player);
	}
	
}
