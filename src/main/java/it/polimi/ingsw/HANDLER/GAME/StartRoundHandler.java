package it.polimi.ingsw.HANDLER.GAME;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.CARD.Venture;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;

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
	
	public static void handle(int period, Player[] players, Board board){
		ArrayList<Dice> dices = rollDices();
		for (Player player : players) {
			setFamilyMembersValue(dices, player);
		}
		setCards(board, period);
	}
	
	
	
	
	//Create new dices
	private static ArrayList<Dice> rollDices(){
		ArrayList<DiceColour> colours = new ArrayList<>();
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
	private static ArrayList<DevelopmentCard> getDevelopmentCards(DevelopmentCardType developmentCardType, int period){
		if (developmentCardType.equals(DevelopmentCardType.BUILDING)){
			return DataFromFile.getBuildingsForPeriod(period);
		} else if (developmentCardType.equals(DevelopmentCardType.CHARACTER)){
			return DataFromFile.getCharactersForPeriod(period);
		} else if (developmentCardType.equals(DevelopmentCardType.TERRITORY)){
			return DataFromFile.getTerritoriesForPeriod(period);
		} else// (developmentCardType.equals(DevelopmentCardType.VENTURE)){
			return DataFromFile.getVenturesForPeriod(period);
		//}
		
	}
	
	
	//For each tower set the randomly a card. At the end of this operation return to DataFromFile the unused cards. 
	private static void setCards(Board board, int period){
		Tower[] towers = board.getTowers();
		for (Tower tower : towers) {
			DevelopmentCardType developmentCardType = tower.getDevelopmentCardType();
			ArrayList<DevelopmentCard> developmentCards = getDevelopmentCards(developmentCardType, period);
			TowerFloor[] towerFloors = tower.getPositions();
			for (TowerFloor towerFloor : towerFloors) {
				int randomCard = (int) Math.random()*developmentCards.size();
				DevelopmentCard developmentCard = developmentCards.get(randomCard);
				towerFloor.setDevelopmentCard(developmentCard);
				developmentCards.remove(randomCard);
			}
			DataFromFile.lastCards(developmentCardType, period, developmentCards);
		}
	}
	
	
	

}
