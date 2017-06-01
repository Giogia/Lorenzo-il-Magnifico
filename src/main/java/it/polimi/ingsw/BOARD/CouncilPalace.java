package it.polimi.ingsw.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.GC_15.Game;

public class CouncilPalace extends Zone {

	
	public CouncilPalace() {
		super("market");
		positions = new Position[1];
		ArrayList<ImmediateBonus> boardBonus = new ArrayList<>();
		positions[0]= new Position(boardBonus, 0);
	}
	
	@Override
	public String getDescription() {
		String description = "Palazzo del Consiglio";
		return description;
	}
}
