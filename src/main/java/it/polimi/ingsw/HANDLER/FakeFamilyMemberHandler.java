package it.polimi.ingsw.HANDLER;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.GAME.ActionHandler;
import it.polimi.ingsw.HANDLER.GAME.Manager;

public class FakeFamilyMemberHandler {
	
	
	public static boolean handle(Player player, ActionZone zone, int value){
		Dice fakeDice = new Dice(DiceColour.Fake); 
		fakeDice.setValue(value); 	
		FamilyMember fakeFamilyMember = new FamilyMember(fakeDice, player);
		Position position = Manager.askForAction(fakeFamilyMember, zone);
		if(ActionHandler.handle(fakeFamilyMember,zone,position)){ //if it's set correctly then remove the fake family member
			position.removeFamilyMember(fakeFamilyMember);
			return true;
		}
		return false;
	}
}
