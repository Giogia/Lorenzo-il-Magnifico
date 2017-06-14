package it.polimi.ingsw.HANDLER.ADVANCED;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.manager.Manager;

public class PersonalBonusTileDraftHandler {
	
	public static void handle(Board board) throws IOException{
		ArrayList<PersonalBonusTile> personalBonusTiles = board.getGame().getData().getPersonalBonusTiles();
		//personalBonusTiles.remove(0);//quello per le regole base
		for(int i=board.getGame().getRoundOrder().size()-1;i>=0;i--){
			Player currentPlayer = board.getGame().getOrder().getPlayer(i);
			PersonalBonusTile chosen = Manager.askForPersonalBonusTile(currentPlayer,personalBonusTiles);
			currentPlayer.getPersonalBoard().setPersonalBonusTile(chosen);
			personalBonusTiles.remove(chosen);
		}
	}
}
