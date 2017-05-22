package it.polimi.ingsw.HANDLER;

import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.BONUS.OccupiedTowerCostBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.CONTROLLER.*;

public class TowerHandler {

	public static void handle(FamilyMember familyMember, Tower zone, TowerFloor towerFloor) {
		
		
		
		
		
		
		
		
		
		if( PositionAlreadyOccupiedController(towerFloor) &&
			ZoneOccupiedBySameColorController(zone, towerFloor) &&
			EnoughSpaceInPersonalBoard(familyMember.getPlayer(), towerFloor.getDevelopmentCard()) &&
			if(zone.getDevelopmentCardType()== DevelopmentCardType.TERRITORY){
			   //controlla punti militari
			} &&
			FamilyMemberValueController(familymember, position) &&
			if(ZoneAlreadyOccupiedController(zone)){
				OccupiedTowerCostBonus.ImmediateBonus;
			} &&
			ResourceController(familyMember.getPlayer(),) &&
			
		   //e' un disastro scusate domani lo sistemo ma avevo fatto altre modifiche che volevo committarvi
		   
		   
				
	}
}
