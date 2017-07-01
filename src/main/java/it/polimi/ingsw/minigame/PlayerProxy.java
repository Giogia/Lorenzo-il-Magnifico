package it.polimi.ingsw.minigame;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.Player.Color;

public class PlayerProxy {

	private String name;
	private Color color;
	private PersonalBoardProxy personalBoardProxy;
	private ArrayList<FamilyMemberProxy> familyMemberProxies;
	
	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}
	
	public PersonalBoardProxy getPersonalBoardProxy() {
		return personalBoardProxy;
	}
	
	public ArrayList<FamilyMemberProxy> getFamilyMemberProxies() {
		return familyMemberProxies;
	}
	
	public FamilyMemberProxy getFamilyMemberProxy(DiceColour diceColour) {
		for (FamilyMemberProxy familyMemberProxy : familyMemberProxies) {
			if(familyMemberProxy.getDiceColour().equals(diceColour))
				return familyMemberProxy;
		}
		return null;
	}
}
