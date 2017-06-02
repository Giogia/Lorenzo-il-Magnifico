package it.polimi.ingsw.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.GC_15.Game;

public class Market extends Zone{

	public Market() {
			super("market");
			int numberOfPlayers = Game.getPlayers().length;
			int numberOfPositions = (numberOfPlayers>=4? 4 : 2);
			positions = new Position[numberOfPositions];
			ArrayList<ImmediateBonus> boardBonus = new ArrayList<>();
			for(int i=0;i<numberOfPositions;i++){
				positions[i]= new Position(boardBonus, 1);
			}		
	
	}
	
	@Override
	public String getDescription() {
		return "Mercato";
	}
}
