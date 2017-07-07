package it.polimi.ingsw.minigame;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.CouncilPalace;
import it.polimi.ingsw.BOARD.Position;

public class CouncilPalaceProxy extends ZoneProxy{
	
	public CouncilPalaceProxy(CouncilPalace councilPalace) {
		super(councilPalace);
		ArrayList<FamilyMemberProxy> familyMemberProxies = positionProxies.get(0).familyMemberProxies; 
		familyMemberProxies.clear();//call family member's in council palace
		
		for(int i = 0; i < 4; i++){
			familyMemberProxies.add(new FamilyMemberProxy());//there are 4 family members fake!
		}
	}
}
