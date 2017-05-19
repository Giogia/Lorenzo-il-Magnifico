package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.GC_15.FamilyMember;

public class ZoneOccupiedBySameColorController implements Controller{
	
	
	// Controlla tutte le posizioni e tutti i familiari per ogni posizione, se becca due familiari con player uguali ritorna false
	public static boolean check(Zone zone, FamilyMember familyMember){
		Position[] positions = zone.getPositions();
		for (int i = 0; i < positions.length; i++){
			ArrayList<FamilyMember> familyMembers = positions[i].getFamilyMembers();
			for (FamilyMember familyMember2 : familyMembers) {
				if (familyMember.getPlayer().equals(familyMember2.getPlayer())){
					return false;
				}
			}
		}
		return true;
	}

}
