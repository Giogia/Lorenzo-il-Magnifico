package it.polimi.ingsw.HANDLER;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.GAME.ActionHandler;

public class FakeFamilyMemberHandler {
	
	
	public static boolean handle(Player player,Zone zone, Position position){
		Dice fakeDice = new Dice(DiceColour.Fake); 
		fakeDice.setValue(position.getDiceRequirement()); //crea famili member fittizio con valore giusto per la posizione segnata	
		FamilyMember fakeFamilyMember = new FamilyMember(fakeDice, player);
		if(ActionHandler.handle(fakeFamilyMember,zone,position)){ //if it's set correctly then remove the fake family member
			position.removeFamilyMember(fakeFamilyMember);
			return true;
		}
		return false;
	}
}
