package it.polimi.ingsw.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.GC_15.Game;

//model class of the market
public class Market extends Zone{

	public Market(Board board){
		this();
		setBoard(board);
		int numberOfPlayers = board.getGame().getPlayers().length;
		int numberOfPositions = (numberOfPlayers>=4? 4 : 2);
		positions = new Position[numberOfPositions];
		
		ArrayList<ArrayList<ImmediateBonus>> boardBonus = board.getGame().getData().getMarketPositionBonus(); 
		for(int i=0;i<numberOfPositions;i++){
			positions[i]= new Position(boardBonus.get(i), 1);
		}		

	}
	public Market() {
		super("market");
	}
	
	@Override
	public String getDescription() {
		return "Market";
	}
}
