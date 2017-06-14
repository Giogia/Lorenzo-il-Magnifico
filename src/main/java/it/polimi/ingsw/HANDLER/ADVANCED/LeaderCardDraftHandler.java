package it.polimi.ingsw.HANDLER.ADVANCED;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.manager.Manager;

public class LeaderCardDraftHandler {
	

	public static void handle(Board board) throws RemoteException, IOException{
		int numberOfPlayers = board.getPlayers().length;
		ArrayList<LeaderCard> leaderCards = board.getGame().getData().getLeaderCards();
		Random r= new Random();
		ArrayList<ArrayList<LeaderCard>> draftLeaderCards = new ArrayList<>();//ArrayList of the players' hands
		
		for(int i=0;i<numberOfPlayers;i++){
			ArrayList<LeaderCard> playerLeaderCards = new ArrayList<>();//create a player's hand
			for(int j=0;j<4;j++){//give 4 random card to players's hand
				int index = r.nextInt(leaderCards.size());
				playerLeaderCards.add(leaderCards.get(index));
				leaderCards.remove(index);
			}
			draftLeaderCards.add(playerLeaderCards);//put the players' hands in a arraylist
		}
		
		for(int k=0;k<4;k++){
			int playerOrder = 0;
			for(Player player : board.getPlayers()){//every player chose one card and then the cards rotate
				int numberOfArray = (k+playerOrder)%numberOfPlayers;
				leaderCards = draftLeaderCards.get(numberOfArray);
				LeaderCard chosen = Manager.chooseLeaderCard(player, leaderCards);
				leaderCards.remove(chosen);
				player.addLeadercard(chosen);
				playerOrder++;
			}
		}
		
	}

}
