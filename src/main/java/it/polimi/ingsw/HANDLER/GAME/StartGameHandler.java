package it.polimi.ingsw.HANDLER.GAME;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.ADVANCED.LeaderCardDraftHandler;
import it.polimi.ingsw.HANDLER.ADVANCED.PersonalBonusTileDraftHandler;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.minigame.GameProxy;

//set of instructions to start the game
public final class StartGameHandler {
	
	private static StartGameHandler istanza = null;
	
	private StartGameHandler() {}
	
	public static StartGameHandler getStartGameHandler() {
		if (istanza == null) {
            istanza = new StartGameHandler();
        }
        return istanza;
	}
	
	public static void handle(Board board) throws IOException{
		setRandomExcommunicationTiles(board.getGame().getData().getExcommunicationTiles(), board);
		chooseOrder(board);
		setPlayersResources(board);
		//setPersonalBonusTiles(board, board.getGame().getData().getPersonalBonusTiles());
		Game thisGame = board.getGame();
		GameProxy gameProxy = new GameProxy(thisGame);
		ConnectionManagerImpl.getConnectionManager().startGame(thisGame, gameProxy);
		PersonalBonusTileDraftHandler.handle(board);
		LeaderCardDraftHandler.handle(board);
		
	}
	
	
	private static void setRandomExcommunicationTiles(ArrayList<ExcommunicationTile> list, Board board){
		ExcommunicationTile[] excommunicationTiles = new ExcommunicationTile[3];
		for(int i=0;i<3;i++){
			ArrayList<ExcommunicationTile> periodExcommunicationTiles = findByPeriod(list, i+1);
			Random r= new Random();
			int index = r.nextInt(periodExcommunicationTiles.size());
			ExcommunicationTile chosenExcommunicationTile = periodExcommunicationTiles.get(index); //tile preso con indice a caso
			excommunicationTiles[i]= chosenExcommunicationTile; //guarda il periodo di quello scelto e lo mette in quella posizione dell'array
		}
		board.setExcommunicationTiles(excommunicationTiles);
	}
	
	private static ArrayList<ExcommunicationTile> findByPeriod(ArrayList<ExcommunicationTile> list, int period) {
		ArrayList<ExcommunicationTile> periodExcommunicationTiles = new ArrayList<>();
		for (ExcommunicationTile excommunicationTile : list) {
			if (excommunicationTile.period == period){
				periodExcommunicationTiles.add(excommunicationTile);
			}
		}
		return periodExcommunicationTiles;
	}

	private static void setPlayersResources(Board board){
		int i=0;
		for(Player player: board.getGame().getRoundOrder()){
			PersonalBoard personalBoard = player.getPersonalBoard();
			personalBoard.getResource(ResourceType.stones).addAmount(2);
			personalBoard.getResource(ResourceType.wood).addAmount(2);
			personalBoard.getResource(ResourceType.servants).addAmount(3);
			personalBoard.getResource(ResourceType.coins).addAmount(5+i);
			i++;	
		}
	}
	
	//choose the order randomly
	private static void chooseOrder(Board board) {
		ArrayList<Player> players = new ArrayList<>();
		Player[] gamePlayers = board.getGame().getPlayers();
		while (players.size() != gamePlayers.length){
			Random r = new Random();
			int order = r.nextInt(gamePlayers.length);
			if (!players.contains(gamePlayers[order])){
				players.add(gamePlayers[order]);
			}
		}
		board.getGame().setOrder(players);
	}

	
	public static void setPersonalBonusTiles(Board board,ArrayList<PersonalBonusTile> personalBonusTile){
		for(Player player: board.getPlayers()){
			player.getPersonalBoard().setPersonalBonusTile(personalBonusTile.get(0));
		}
	}
	
}

