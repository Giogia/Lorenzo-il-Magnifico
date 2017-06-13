package it.polimi.ingsw.HANDLER.ADVANCED;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.manager.Manager;

public class LeaderCardDraftHandler {
	
	public static void handle(Board board) throws RemoteException{
		ArrayList<LeaderCard> leaderCards = board.getGame().getData().getLeaderCards();
		Random r= new Random();
		ArrayList<ArrayList<LeaderCard>> draftLeaderCards = new ArrayList<>();//ArrayList of the players' hands
		
		for(int i=0;i==board.getPlayers().length-1;i++){
			ArrayList<LeaderCard> playerLeaderCards = new ArrayList<>();//create a player's hand
			for(int j=0;j==3;j++){//give 4 random card to players's hand
				int index = r.nextInt(leaderCards.size());
				playerLeaderCards.add(leaderCards.get(index));
				leaderCards.remove(index);
			}
			draftLeaderCards.add(playerLeaderCards);//put the players' hands in a arraylist
		}
		
		for(int k=0;k==3;k++){
			int playerOrder = 0;
			for(Player player : board.getPlayers()){//every player chose one card and then the cards rotate
				leaderCards = draftLeaderCards.get(k+playerOrder);
				LeaderCard chosen = Manager.chooseLeaderCard(player, leaderCards);
				leaderCards.remove(chosen);
				player.addLeadercard(chosen);
				playerOrder++;
			}
		}
		
	}

}
