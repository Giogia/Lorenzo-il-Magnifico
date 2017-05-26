package it.polimi.ingsw.HANDLER.GAME;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;

import it.polimi.ingsw.RESOURCE.ResourceType;

public class EndGameHandler {

	public ArrayList<Player> handle(Board board){
		
		transformResourcesIntoPoints(board);
		transformMilitaryPoints(board);
		//dai i punti vittoria carte territorio e personaggio
		//dai i punti vittoria fede
		return getWinner(board);
	}

	private void transformResourcesIntoPoints(Board board) {
		for(Player player : board.getPlayers()){
			int amount = 0;
			PersonalBoard personalBoard = player.getPersonalBoard();
			amount += personalBoard.getResource(ResourceType.COINS).getAmount();
			amount += personalBoard.getResource(ResourceType.STONES).getAmount();
			amount += personalBoard.getResource(ResourceType.WOOD).getAmount();
			amount += personalBoard.getResource(ResourceType.SERVANTS).getAmount();
			player.getPersonalBoard().getResource(ResourceType.VICTORYPOINTS).addAmount(amount/5); //sum 1 for every 5 other resources 
		}
			
	}
	
	private ArrayList<Player> getWinner(Board board ){
		ArrayList<Player> winners = new ArrayList<Player>();
		int maxVictoryPoints =0;
		for(Player player: board.getPlayers()){
			int victoryPoints = player.getPersonalBoard().getResource(ResourceType.VICTORYPOINTS).getAmount();
			if(victoryPoints >= maxVictoryPoints){
				maxVictoryPoints = victoryPoints; //calculat the max amount of victorypoints
			}
		};
		for(Player player: board.getPlayers()){
			if(player.getPersonalBoard().getResource(ResourceType.VICTORYPOINTS).getAmount()==maxVictoryPoints){
				winners.add(player); //add the player to the arraylist if they have the max amount of victoryPoints
			}
		};
		return winners; //return all the players with the max amount of victory points
	}
	
	
	private void transformMilitaryPoints(Board board){
		ArrayList<Player> firstPos = new ArrayList<Player>();
		ArrayList<Player> secondPos = new ArrayList<Player>();
		int maxMilitaryPoints =0;
		int soCloseMilitaryPoints =0;
		for(Player player: board.getPlayers()){
			int militaryPoints = player.getPersonalBoard().getResource(ResourceType.VICTORYPOINTS).getAmount();
			if(militaryPoints > maxMilitaryPoints){
				soCloseMilitaryPoints = maxMilitaryPoints; //calculate the second higher amount of military points
				maxMilitaryPoints = militaryPoints; //calculate the max amount of victorypoints
			}
		};
		for(Player player: board.getPlayers()){
			if(player.getPersonalBoard().getResource(ResourceType.MILITARYPOINTS).getAmount()==maxMilitaryPoints){
				firstPos.add(player); //add the player to the arraylist if they have the max amount of militaryPoints
			}
			if(player.getPersonalBoard().getResource(ResourceType.MILITARYPOINTS).getAmount()==soCloseMilitaryPoints){
				firstPos.add(player); //add the player to the arraylist if they have the second higher amount of victoryPoints
			}
		};
		for(Player player: firstPos){//add 5 vicotry points to the winners
			player.getPersonalBoard().getResource(ResourceType.VICTORYPOINTS).addAmount(5);	
		};
		if(firstPos.size()==1){// add 2 victory points to the second ones if there hasn't been a tie of winners
			for(Player player: secondPos){
				player.getPersonalBoard().getResource(ResourceType.VICTORYPOINTS).addAmount(2);
			}
		}
	}
	
	private void transformVenturePoints(Board board){
		for(Player player : board.getPlayers()){ //per ogni giocatore
			for(CardContainer cardContainer :player.getPersonalBoard().getCardContainers()){// find ventures in cardcontainer
				if(cardContainer.getType().equals(DevelopmentCardType.VENTURE)){
					for(DevelopmentCard venture : cardContainer.getDevelopmentCards()){// for every venture
						//activate the secondary effect
					}	
				}
			}
		}
	}
	
}
