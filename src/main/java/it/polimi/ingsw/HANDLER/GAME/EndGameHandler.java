package it.polimi.ingsw.HANDLER.GAME;

import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.manager.ConnectionManagerImpl;

public class EndGameHandler {
	
private static EndGameHandler istanza = null;
	
	private EndGameHandler() {}
	
	public static EndGameHandler getEndGameHandler() {
		if (istanza == null) {
            istanza = new EndGameHandler();
        }
        return istanza;
	}

	public static void handle(Board board) throws RemoteException{
		
		transformResourcesIntoPoints(board);
		transformMilitaryPoints(board);
		transformCardIntoPoints(board, DevelopmentCardType.territory);
		transformCardIntoPoints(board, DevelopmentCardType.character);
		//dai i punti vittoria fede
		ConnectionManagerImpl.hasWon(getWinner(board));
	}

	private static void transformResourcesIntoPoints(Board board) {
		for(Player player : board.getPlayers()){
			int amount = 0;
			PersonalBoard personalBoard = player.getPersonalBoard();
			amount += personalBoard.getResource(ResourceType.coins).getAmount();
			amount += personalBoard.getResource(ResourceType.stones).getAmount();
			amount += personalBoard.getResource(ResourceType.wood).getAmount();
			amount += personalBoard.getResource(ResourceType.servants).getAmount();
			player.getPersonalBoard().getResource(ResourceType.victoryPoints).addAmount(amount/5); //sum 1 for every 5 other resources 
		}
			
	}
	
	private static void transformCardIntoPoints(Board board,DevelopmentCardType developmentCardType){
		for(Player player : board.getGame().getRoundOrder()){
			for(CardContainer cardcontainer: player.getPersonalBoard().getCardContainers()){
				if(cardcontainer.getType().equals(developmentCardType)){
					int numberOfCards = cardcontainer.getDevelopmentCards().size();
					int[] victoryPointsPerCard= board.getGame().getData().getVictoryPointsPerCard(developmentCardType); //TODO prendere l'array giusto con la codifica
					player.getPersonalBoard().getResource(ResourceType.victoryPoints).addAmount(victoryPointsPerCard[numberOfCards]);
				}
			}
		}
	}
	
	//TODO: check!
	private static String getWinner(Board board){
		int maxVictoryPoints =-1; //se tutti i giocatori totalizzassero zero punti deve vincere il primo in ordine di turno
		Player winner = null;
		for(Player player: board.getPlayers()){
			int victoryPoints = player.getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount();
			if(victoryPoints > maxVictoryPoints){// fa un ciclo in ordine dell'ultimo turno e ogni volta che uno supera vince
			winner = player;
			}
		}
		return winner.getName(); 
	}
	
	
	private static void transformMilitaryPoints(Board board){
		ArrayList<Player> firstPos = new ArrayList<Player>();
		ArrayList<Player> secondPos = new ArrayList<Player>();
		int maxMilitaryPoints =0; 
		int soCloseMilitaryPoints =0;
		for(Player player: board.getPlayers()){
			int militaryPoints = player.getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount();
			if(militaryPoints > maxMilitaryPoints){ // only major otherwise a tie will put soclose equal to max
				soCloseMilitaryPoints = maxMilitaryPoints; //calculate the second higher amount of military points
				maxMilitaryPoints = militaryPoints; //calculate the max amount of victorypoints
			}
		}
		for(Player player: board.getPlayers()){
			if(player.getPersonalBoard().getResource(ResourceType.militaryPoints).getAmount()==maxMilitaryPoints){
				firstPos.add(player); //add the player to the arraylist if they have the max amount of militaryPoints
			}
			if(player.getPersonalBoard().getResource(ResourceType.militaryPoints).getAmount()==soCloseMilitaryPoints){
				firstPos.add(player); //add the player to the arraylist if they have the second higher amount of victoryPoints
			}
		}
		for(Player player: firstPos){//add 5 vicotry points to the winners
			player.getPersonalBoard().getResource(ResourceType.victoryPoints).addAmount(5);	
		}
		if(firstPos.size()==1){// add 2 victory points to the second ones if there hasn't been a tie of winners
			for(Player player: secondPos){
				player.getPersonalBoard().getResource(ResourceType.victoryPoints).addAmount(2);
			}
		}
	}
	
	private static void transformVenturePoints(Board board){
		for(Player player : board.getPlayers()){ //per ogni giocatore
			for(CardContainer cardContainer :player.getPersonalBoard().getCardContainers()){// find ventures in cardcontainer
				if(cardContainer.getType().equals(DevelopmentCardType.venture)){
					for(DevelopmentCard venture : cardContainer.getDevelopmentCards()){// for every venture
						//activate the secondary effect
					}	
				}
			}
		}
	}
	
}
