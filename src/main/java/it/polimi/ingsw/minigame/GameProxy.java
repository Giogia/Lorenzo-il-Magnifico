package it.polimi.ingsw.minigame;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class GameProxy implements Serializable{

	private BoardProxy boardProxy;
	private ArrayList<PlayerProxy> playerProxies = new ArrayList<>();
	private ArrayList<DiceProxy> diceProxies = new ArrayList<>();
	
	public GameProxy(Game game) {
		boardProxy = new BoardProxy(game.getBoard());
		for(Player player : game.getPlayers()){
			playerProxies.add(new PlayerProxy(player));
		}
	}

	public void setDiceProxies(ArrayList<Dice> dices) {
		for (Dice dice : dices) {
			diceProxies.add(new DiceProxy(dice));
		}
	}
	
	public ArrayList<DiceProxy> getDiceProxies() {
		return diceProxies;
	}
	
	public ArrayList<PlayerProxy> getPlayerProxies() {
		return playerProxies;
	}
	
	public PlayerProxy getPlayerProxy(Color color){
		for (PlayerProxy playerProxy : playerProxies) {
			if(playerProxy.getColor().equals(color))
				return playerProxy;
		}
		return null;
	}
	
	public void setcurrentPlayer(Color color){
		PlayerProxy currentPlayer = getPlayerProxy(color);
		ArrayList<PlayerProxy> currentPlayers = new ArrayList<>();
		currentPlayers.add(currentPlayer);
		playerProxies.remove(currentPlayer);
		currentPlayers.addAll(playerProxies);
		playerProxies = currentPlayers;
	}
	
	public BoardProxy getBoardProxy() {
		return boardProxy;
	}
}
