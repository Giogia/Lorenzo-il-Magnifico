package it.polimi.ingsw.minigame;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.GC_15.FamilyMember;

public class PositionProxy {
	
	private ArrayList<FamilyMemberProxy> familyMemberProxies = new ArrayList<>();
	
	public PositionProxy(Position position) {
		for (FamilyMember familyMember : position.getFamilyMembers()) {
			familyMemberProxies.add(new FamilyMemberProxy(familyMember));
		}
	}

	public ArrayList<FamilyMemberProxy> getFamilyMemberProxies() {
		return familyMemberProxies;
	}

}
