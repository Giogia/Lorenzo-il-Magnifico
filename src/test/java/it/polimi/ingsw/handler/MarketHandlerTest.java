package it.polimi.ingsw.handler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BONUS.AddResourceBonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.Cli.CliRmiView;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.HANDLER.MarketHandler;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.RESOURCE.FaithPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.RESOURCE.VictoryPoints;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.manager.ConnectionManagerRmiServerImpl;
import it.polimi.ingsw.manager.User;

public class MarketHandlerTest {

	@Test
	public void test() throws MyException, IOException, TimeExpiredException {
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
			ArrayList<Resource> resources = new ArrayList<>();
			resources.add(new VictoryPoints(8, 1));
			imBonus.add(new AddResourceBonus(resources));
			bonus.add(imBonus);
		}
		ArrayList<ImmediateBonus> bonusArray = new ArrayList<>();
		
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new FaithPoints(3, 1));
		bonusArray.add(new AddResourceBonus(resources));
		
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
		CliRmiView cliRmiView = new CliRmiView();		
		ConnectionManagerRmiServerImpl con1 = mock(ConnectionManagerRmiServerImpl.class);
		ArrayList<User> users = new ArrayList<>();
		for(int i = 0; i < 4; i++){
			User user = mock(User.class);
			user.setPlayer(players[i]);
			users.add(user);
			when(user.getConnectionManagerRmiServerImpl()).thenReturn(con1);
			when(user.getConnectionType()).thenReturn(true);
			when(user.getCliRmi()).thenReturn(cliRmiView);
			when(user.getPlayer()).thenReturn(players[i]);
		}
		ConnectionManagerImpl.getUsersInGame().addAll(users);
		
		when(con1.getIsAvailable()).thenReturn(true);
		when(con1.getStringReceived()).thenReturn("0");
		
		Dice dice = new Dice(DiceColour.Orange);
		dice.setValue(1);
		FamilyMember familyMember = new FamilyMember(dice, players[0]);
		
		MarketHandler.handle(familyMember, board.getMarket().getPosition(2));
		

		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.coins).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.wood).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.servants).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.stones).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.militaryPoints).getAmount());
		assertEquals(8, players[0].getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
		
		assertEquals(players[0], board.getPassTurnController().getLastMove());
		
		assertNotEquals(familyMember, board.getMarket().getPosition(2).getFamilyMembers().get(0));
		
		assertEquals(familyMember.getValue(), board.getMarket().getPosition(2).getFamilyMembers().get(0).getValue());
	
	}

}
