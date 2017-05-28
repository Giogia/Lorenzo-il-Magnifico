package it.polimi.ingsw.HANDLER.GAME;

import java.util.ArrayList;
import java.util.Random;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.VaticanReport;
import it.polimi.ingsw.RESOURCE.ResourceType;

public final class StartGameHandler {
	
	private static StartGameHandler istanza = null;
	
	private StartGameHandler() {}
	
	public static StartGameHandler getStartGameHandler() {
		if (istanza == null) {
            istanza = new StartGameHandler();
        }
        return istanza;
	}
	
	public static void handle(Board board){
		setRandomExcommunicationTiles(DataFromFile.getExcommunicationTiles());
		SetPlayersResources(board);
		ChooseOrder(board);
	}
	
	
	private static void setRandomExcommunicationTiles(ArrayList<ExcommunicationTile> list){
		ExcommunicationTile[] excommunicationTiles = new ExcommunicationTile[3];
		for(int i=0;i<3;i++){
			Random r= new Random();
			int index = r.nextInt(list.size());
			ExcommunicationTile chosenExcommunicationTile = list.get(index); //tile preso con indice a caso
			excommunicationTiles[chosenExcommunicationTile.period-1]= chosenExcommunicationTile; //guarda il periodo di quello scelto e lo mette in quella posizione dell'array
		}
		VaticanReport.setExcommunicationTiles(excommunicationTiles);
	}
	
	private static void SetPlayersResources(Board board){
		int i=0;
		for(Player player: board.getPlayers()){
			PersonalBoard personalBoard = player.getPersonalBoard();
			personalBoard.getResource(ResourceType.STONES).addAmount(2);
			personalBoard.getResource(ResourceType.WOOD).addAmount(2);
			personalBoard.getResource(ResourceType.SERVANTS).addAmount(3);
			personalBoard.getResource(ResourceType.STONES).addAmount(5+i);
			i++;	
		}
	}
	
		
	private static void ChooseOrder(Board board) {
		for(Player player: board.getPlayers()){
			Random r = new Random();
			int i =r.nextInt(board.getPlayers().length); //estrae un numero a caso da 1 a 4
			while(board.getRoundOrder().getPlayer(i)!=null){ //se la posizione e' gia occupata passa a quella dopo
				i++;
			}
			board.getRoundOrder().setplayer(player, i);
		}	
	}

	
}

