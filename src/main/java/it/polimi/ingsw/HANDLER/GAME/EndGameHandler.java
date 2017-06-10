package it.polimi.ingsw.HANDLER.GAME;

import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CONTROLLER.EndGameCardController;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.ADVANCED.LoseVictoryPointsPerResourceHandler;
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

	public static void handle(Board board) throws RemoteException, MyException{
		
		transformResourcesIntoPoints(board);
		transformMilitaryPoints(board);
		transformFaithPoints(board);
		transformCardIntoPoints(board, DevelopmentCardType.territory);
		transformCardIntoPoints(board, DevelopmentCardType.character);
		transformVentureIntoPoints(board);
		excommunicationMalus(board);
		ConnectionManagerImpl.hasWon(getWinner(board));
	}

	private static void excommunicationMalus(Board board) {
		for (Player player : board.getPlayers()) {
			LoseVictoryPointsPerResourceHandler.handle(player);
		}
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
			if (EndGameCardController.check(player, developmentCardType)){
				CardContainer cardContainer = player.getPersonalBoard().getCardContainer(developmentCardType);
				int numberOfCards = cardContainer.getDevelopmentCards().size();
				int[] victoryPointsPerCard= board.getGame().getData().getVictoryPointsPerCard(developmentCardType); 
				player.getPersonalBoard().getResource(ResourceType.victoryPoints).addAmount(victoryPointsPerCard[numberOfCards]);
			}
		}
	}
	

	private static void transformVentureIntoPoints(Board board) throws MyException,RemoteException {
		for(Player player : board.getPlayers()){
			if(EndGameCardController.check(player, DevelopmentCardType.venture)){
				CardContainer cardContainer = player.getPersonalBoard().getCardContainer(DevelopmentCardType.venture);
				for (DevelopmentCard ventureCard : cardContainer.getDevelopmentCards()) {
					for (Bonus bonus : ventureCard.secondaryEffect) {
					ImmediateBonus immediateBonus = (ImmediateBonus) bonus;
					immediateBonus.getImmediateBonus(player);
					}
				}
			}
		}
	}
	
	
	private static String getWinner(Board board){
		int maxVictoryPoints = -1; //se tutti i giocatori totalizzassero zero punti deve vincere il primo in ordine di turno
		Player winner = null;
		for(Player player: board.getGame().getRoundOrder()){
			int victoryPoints = player.getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount();
			if(maxVictoryPoints==-1){
				maxVictoryPoints = victoryPoints;
				winner= player;
			}
			else if(victoryPoints > maxVictoryPoints){// fa un ciclo in ordine dell'ultimo turno e ogni volta che uno supera vince
			winner = player;
			}
		}
		return winner.getName(); 
	}
	
	private static void transformFaithPoints(Board board){
		for(Player player: board.getPlayers()){
			int faithPoints = player.getPersonalBoard().getResource(ResourceType.faithPoints).getAmount();
			int[] fromFaithPointsToVictoryPoints = board.getGame().getData().getFromFaithPointsToVictoryPoints();
			player.getPersonalBoard().getResource(ResourceType.faithPoints).addAmount(fromFaithPointsToVictoryPoints[faithPoints]);
		}
	}
	
	private static void transformMilitaryPoints(Board board){
		ArrayList<Player> firstPos = new ArrayList<Player>();
		ArrayList<Player> secondPos = new ArrayList<Player>();
		int[] fromMilitaryPointsToVictoryPoints = board.getGame().getData().getFromMilitaryPointsToVictoryPoints();
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
			player.getPersonalBoard().getResource(ResourceType.victoryPoints).addAmount(fromMilitaryPointsToVictoryPoints[0]);	
		}
		if(firstPos.size()==1){// add 2 victory points to the second ones if there hasn't been a tie of winners
			for(Player player: secondPos){
				player.getPersonalBoard().getResource(ResourceType.victoryPoints).addAmount(fromMilitaryPointsToVictoryPoints[1]);
			}
		}
	}
}
