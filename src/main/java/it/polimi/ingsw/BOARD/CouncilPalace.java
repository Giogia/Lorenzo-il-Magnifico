package it.polimi.ingsw.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.GC_15.Game;

//model class of the council palace
public class CouncilPalace extends Zone {

	public CouncilPalace(Board board){
		this();
		setBoard(board);
		positions = new Position[1];
		
		ArrayList<ImmediateBonus> boardBonus = board.getGame().getData().getCouncilPalaceBonus();
		positions[0]= new Position(boardBonus, 1);
	}
	
	public CouncilPalace() {
		super("councilPalace");
	}
	
	@Override
	public String getDescription() {
		return "Council Palace";
	}
}
