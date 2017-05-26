package it.polimi.ingsw.HANDLER.GAME;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.VaticanReport;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class StartGameHandler {
	
	public void handle(Board board,VaticanReport vaticanReport){
	 //setRandomExcommunicationTiles(DataFromFile.getExcommunicationTiles(),vaticanReport);
	//TODO dividere i tiles per periodi e poi sistemare questo metodo
		SetPlayeraResources(board);
		ChooseOrder(board);
	}
	
	
	public void setRandomExcommunicationTiles(ArrayList<ExcommunicationTile> list, VaticanReport vaticanReport){
		ExcommunicationTile[] excommunicationTiles = new ExcommunicationTile[3];
		for(int i=0;i<3;i++){
			int index = (int) (Math.random() * list.size());
			excommunicationTiles[i]= list.get(index);
		}
		vaticanReport.setExcommunicationTiles(excommunicationTiles);
	}
	
	private void SetPlayeraResources(Board board){
		for(Player player: board.getPlayers()){
			int i=0;
			PersonalBoard personalBoard = player.getPersonalBoard();
			personalBoard.getResource(ResourceType.STONES).addAmount(2);
			personalBoard.getResource(ResourceType.WOOD).addAmount(2);
			personalBoard.getResource(ResourceType.SERVANTS).addAmount(3);
			personalBoard.getResource(ResourceType.STONES).addAmount(5+i);
			i++;	
		}
	}
	
		
	private void ChooseOrder(Board board) {
		for(Player player: board.getPlayers()){
			int i = (int) (Math.random()*board.getPlayers().length); //estrae un numero a caso da 1 a 4
			while(!board.getRoundOrder().getPlayer(i).equals(null)){ //se la posizione e' gia occupata passa a quella dopo
				i++;
			}
			board.getRoundOrder().setplayer(player, i);
		}	
	}

	
}

