package it.polimi.ingsw.BOARD;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.TimeExpiredException;

public class Position implements Serializable{
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
	
	public void addFamilyMember(FamilyMember newFamilyMember) throws MyException, IOException, TimeExpiredException{
		familyMembers.add(newFamilyMember);
	}
	
	public void removeFamilyMember(FamilyMember familyMember){
		int i = 0;
		while (i < familyMembers.size()) {
			FamilyMember positionFamilyMember = familyMembers.get(i);
			if (positionFamilyMember.getPlayer().equals(familyMember.getPlayer()) && 
					positionFamilyMember.getDice().getDiceColour().equals(familyMember.getDice().getDiceColour())){
				familyMembers.remove(positionFamilyMember);
			}
			else {
				i++;
			}
		}
	}
	
	public void deleteAllFamilyMember(){
		familyMembers.clear();
	}
	
	protected void giveImmediateBonus(Player player, ImmediateBonus immediateBonus) throws MyException, IOException, TimeExpiredException{
		immediateBonus.getImmediateBonus(player);
	}
	protected void givePermanentBonus(Player player, PermanentBonus permanentBonus){
		permanentBonus.getPermanentBonus(player);
	}
	
	public ArrayList<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}
	
	public void setFamilyMembers(ArrayList<FamilyMember> familyMembers) {
		this.familyMembers = familyMembers;
	}

	public String getDescription() {
		StringBuilder description = new StringBuilder();
		description.append("Family Member minimum value: " + diceRequirement + "\n");
			description.append("Bonus on this position: \n");
			if(boardBonus==null){
				description.append("No Bonus on this Position \n");
			}
			else{
				for (ImmediateBonus immediateBonus : boardBonus) {
					description.append(immediateBonus.getDescription() + "\n");
				}
			}
			if(familyMembers.isEmpty()){
				description.append("Position not occupied \n");
			}
			else{
				description.append("Position occupied by: \n");
				for (FamilyMember familyMember : familyMembers) {
					description.append(familyMember.getPlayer().getName() + "\n " + familyMember.getDescription() + "\n");
				}
			}	
		return description.toString();
	}
}
