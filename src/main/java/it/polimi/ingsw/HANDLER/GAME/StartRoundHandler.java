package it.polimi.ingsw.HANDLER.GAME;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.manager.ConnectionManagerImpl;

public class StartRoundHandler {
	private static StartRoundHandler instance;
	// Singleton Class
	private StartRoundHandler(){
	}
	
	public static synchronized StartRoundHandler getStartRoundHandler() {
		if (instance== null){
			instance = new StartRoundHandler();
		}
		return instance;
	}
	
	public static void handle(int period, Player[] players, Board board) throws IOException{
		ArrayList<Dice> dices = rollDices(board);
		for (Player player : players) {
			setFamilyMembersValue(dices, player);
		}
		setCards(board, period);
	}
	

	
	//Create new dices
	private static ArrayList<Dice> rollDices(Board board) throws IOException{
		ArrayList<DiceColour> colours = new ArrayList<>();
		Player[] players = board.getPlayers();
		colours.add(DiceColour.Black);
		colours.add(DiceColour.Orange);
		colours.add(DiceColour.White);
		colours.add(DiceColour.Neutral);
		ArrayList<Dice> dices = new ArrayList<>();
		for (DiceColour diceColour : colours) {
			Dice dice = new Dice(diceColour);
			if (!diceColour.equals(DiceColour.Neutral)){
				dice.setValue();
			}
			dices.add(dice);
		}

		ConnectionManagerImpl.getConnectionManager().showDices(players, dices);
		return dices;
	}
	
	
	//Create new FamilyMembers
	private static void setFamilyMembersValue(ArrayList<Dice> dices, Player player){
		ArrayList<FamilyMember> familyMembers = new ArrayList<>();
		for (Dice dice : dices) {
			FamilyMember familyMember = new FamilyMember(dice, player);
			familyMembers.add(familyMember);
		}
		player.setFamilyMember(familyMembers);
	}
	
	//Get the cards from DataFromFile
	private static ArrayList<DevelopmentCard> getDevelopmentCards(DevelopmentCardType developmentCardType, int period, Board board){
		if (developmentCardType.equals(DevelopmentCardType.building)){
			return board.getGame().getData().getBuildingsForPeriod(period);
		} else if (developmentCardType.equals(DevelopmentCardType.character)){
			return board.getGame().getData().getCharactersForPeriod(period);
		} else if (developmentCardType.equals(DevelopmentCardType.territory)){
			return board.getGame().getData().getTerritoriesForPeriod(period);
		} else// (developmentCardType.equals(DevelopmentCardType.VENTURE)){
			return board.getGame().getData().getVenturesForPeriod(period);
		//}
		
	}
	
	
	//For each tower set randomly a card.
	// At the end of this operation return to DataFromFile the unused cards using the method lastCards
	private static void setCards(Board board, int period){
		Tower[] towers = board.getTowers();
		for (Tower tower : towers) {
			DevelopmentCardType developmentCardType = tower.getDevelopmentCardType();
			ArrayList<DevelopmentCard> developmentCards = getDevelopmentCards(developmentCardType, period, board);
			TowerFloor[] towerFloors = (TowerFloor[]) tower.getPositions();
			for (TowerFloor towerFloor : towerFloors) {
				Random r = new Random();
				int randomCard = r.nextInt(developmentCards.size());
				DevelopmentCard developmentCard = developmentCards.get(randomCard);
				towerFloor.setDevelopmentCard(developmentCard);
				developmentCards.remove(randomCard);
			}
			board.getGame().getData().lastCards(developmentCardType, period, developmentCards);
		}
	}
}
