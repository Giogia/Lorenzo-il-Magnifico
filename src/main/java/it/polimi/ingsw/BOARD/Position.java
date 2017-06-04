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
		this.boardBonus = boardBonus;
		this.diceRequirement = diceRequirement;
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
	
	public void addFamilyMember(FamilyMember newFamilyMember) throws Exception{
		familyMembers.add(newFamilyMember);
	}
	
	public void removeFamilyMember(FamilyMember familyMember){
		familyMembers.remove(familyMember);
	}
	
	public void deleteAllFamilyMember(){
		familyMembers.clear();
	}
	
	protected void giveImmediateBonus(Player player, ImmediateBonus immediateBonus) throws Exception{
		immediateBonus.getImmediateBonus(player);
	}
	protected void givePermanentBonus(Player player, PermanentBonus permanentBonus){
		permanentBonus.getPermanentBonus(player);
	}
	
	public ArrayList<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}

	public String getDescription() {
		String description = "Family Member minimum value: " + diceRequirement + "\n";
		try{
			description = description + "Bonus on this position: \n";
			for (ImmediateBonus immediateBonus : boardBonus) {
				description = description + immediateBonus.getDescription() + "\n";
			}
		} catch (NullPointerException e){
		}
		try {
			description = description + "Position occupied by: \n";
			for (FamilyMember familyMember : familyMembers) {
				description = description + familyMember.getPlayer().getName() + "\n " + familyMember.getDescription() + "\n";
			}
		} catch (NullPointerException e){
		}
		return description;
	}
}
