package it.polimi.ingsw.minigame;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player.Color;

public class RoundOrderProxy {

	private ArrayList<OrderPawn> orderPawns;
	
	public ArrayList<OrderPawn> getOrderPawns() {
		return orderPawns;
	}
	
	public OrderPawn getOrderPawn(Color color) {
		for (OrderPawn orderPawn : orderPawns) {
			if(orderPawn.getColor().equals(color))
				return orderPawn;
		}
		return null;
	}
}
