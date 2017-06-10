package it.polimi.ingsw.HANDLER.GAME;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.RoundOrder;
import it.polimi.ingsw.GC_15.VaticanReport;

public class EndRoundHandler {
	private static EndRoundHandler instance;
	
	private EndRoundHandler(){
	}
	
	public static synchronized EndRoundHandler getEndRoundHandler() {
		if (instance == null){
			instance = new EndRoundHandler();
		}
		return instance;
	}
	
	public static void handle(Board board, RoundOrder roundOrder, int turn){
		handleOrder(roundOrder, board);
		board.resetPositions();
		if (turn % 2 == 0){ 
			VaticanReport.checkPlayersFaith((turn+1)/2, board);
		}
		PassTurnController.lastMove(null);
	}
	
	/*This Handler see if there are some FamilyMembers in CouncilPalace.
	 * If it is, put those players in the edge of RoundOrder ArrayList
	 */
	private static void handleOrder(RoundOrder roundOrder, Board board){
		ArrayList<FamilyMember> councilPalaceFamilyMembers = board.getCouncilPalace().getPosition(0).getFamilyMembers();
		ArrayList<Player> councilPalacePlayers = new ArrayList<>();
		ArrayList<Player> oldRoundOrder = roundOrder.getPlayers();
		for (int i = 0; i < councilPalaceFamilyMembers.size(); i++){
			Player player = councilPalaceFamilyMembers.get(i).getPlayer();
			if (!councilPalacePlayers.contains(player)){
				councilPalacePlayers.add(player);
				oldRoundOrder.remove(player);
			}
		}
		councilPalacePlayers.addAll(oldRoundOrder);
		roundOrder.setPlayers(councilPalacePlayers);
	}

}
