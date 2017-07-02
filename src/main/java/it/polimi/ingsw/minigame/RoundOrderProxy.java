package it.polimi.ingsw.minigame;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.GC_15.RoundOrder;

public class RoundOrderProxy implements Serializable{

	private ArrayList<OrderPawn> orderPawns = new ArrayList<>();
	
	public RoundOrderProxy(RoundOrder roundOrder) {
		for(Player player : roundOrder.getPlayers()){
			orderPawns.add(new OrderPawn(player.getColor()));
		}
	}
	
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
