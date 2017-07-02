package it.polimi.ingsw.handler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.HANDLER.PassTurnHandler;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class PassTurnHandlerTest {

	@Test
	public void test() {
		Game game = mock(Game.class);
		Player[] players = new Player[4];
		players[0] = new Player("Gigi", Color.BLUE);
		players[1] = new Player("Fabbio", Color.RED);
		players[2] = new Player("Luca", Color.GREEN);
		players[3] = new Player("Paolo", Color.YELLOW);
		game.setPlayers(players);
		DataFromFile dataFromFile = mock(DataFromFile.class);
		when(game.getData()).thenReturn(dataFromFile);
		ArrayList<ArrayList<ImmediateBonus>> bonus = new ArrayList<>();
		for (int i = 0; i < 4; i++){
			ArrayList<ImmediateBonus> imBonus = new ArrayList<>();
			bonus.add(imBonus);
		}
		ArrayList<ImmediateBonus> bonusArray = new ArrayList<>();
		when(dataFromFile.getCouncilPalaceBonus()).thenReturn(bonusArray);
		for (DevelopmentCardType developmentCardType : DevelopmentCardType.values()) {
			when(dataFromFile.getTowerPositionBonus(developmentCardType)).thenReturn(bonus);
		}
		when(dataFromFile.getMarketPositionBonus()).thenReturn(bonus);
		when(game.getPlayers()).thenReturn(players);
		Board board = new Board(game);
		for (Player player : players) {
			player.setBoard(board);
		}
		
		assertTrue(PassTurnHandler.handle(players[0]));
		
		assertTrue(PassTurnHandler.handle(players[0]));
		
		ArrayList<FamilyMember> familyMembers = new ArrayList<>();
		Dice dice = new Dice(DiceColour.White);
		dice.setValue();
		familyMembers.add(new FamilyMember(dice, players[1]));
		players[1].setFamilyMember(familyMembers);
		
		assertFalse(PassTurnHandler.handle(players[1]));
		
		ArrayList<FamilyMember> familyMembers2 = new ArrayList<>();
		Dice dice2 = new Dice(DiceColour.Neutral);
		familyMembers2.add(new FamilyMember(dice2, players[2]));
		players[2].setFamilyMember(familyMembers2);
		players[2].getPersonalBoard().getResource(ResourceType.servants).addAmount(4);
		
		assertFalse(PassTurnHandler.handle(players[2]));
	}

}
