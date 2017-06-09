package it.polimi.ingsw.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.GC_15.Game;

public class Market extends Zone{

	public Market() {
			super("market");
			int numberOfPlayers = getGame().getPlayers().length;
			int numberOfPositions = (numberOfPlayers>=4? 4 : 2);
			positions = new Position[numberOfPositions];
			
			ArrayList<ArrayList<ImmediateBonus>> boardBonus = getGame().getData().getMarketPositionBonus(); 
			for(int i=0;i<numberOfPositions;i++){
				positions[i]= new Position(boardBonus.get(i), 1);
			}		
	
	}
	
	@Override
	public String getDescription() {
		return "Market";
	}
}
