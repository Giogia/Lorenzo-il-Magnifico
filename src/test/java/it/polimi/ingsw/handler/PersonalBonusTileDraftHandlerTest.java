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
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.GC_15.RoundOrder;
import it.polimi.ingsw.HANDLER.ADVANCED.PersonalBonusTileDraftHandler;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.RESOURCE.FaithPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.manager.ConnectionManagerRmiServerImpl;
import it.polimi.ingsw.manager.User;
import it.polimi.ingsw.view.CliRmiView;

public class PersonalBonusTileDraftHandlerTest {

	@Test
	public void test() throws IOException {
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
		when(con1.getStringReceived()).thenReturn("1");
		
		PersonalBonusTile personalBonusTile = new PersonalBonusTile(new AddResourceBonus(resources), new AddResourceBonus(resources), 0, 0);
		ArrayList<PersonalBonusTile> personalBonusTiles = new ArrayList<>();
		for (int i = 0; i < 4; i++){
			personalBonusTiles.add(personalBonusTile);
		}
		
		when(dataFromFile.getPersonalBonusTiles()).thenReturn(personalBonusTiles);
		ArrayList<Player> playerOrder = new ArrayList<>();
		for (int i = 0; i < 4; i++){
			playerOrder.add(players[i]);
		}
		RoundOrder roundOrder = new RoundOrder();
		roundOrder.setPlayers(playerOrder);
		when(game.getOrder()).thenReturn(roundOrder);
		when(game.getRoundOrder()).thenReturn(playerOrder);
		
		PersonalBonusTileDraftHandler.handle(board);
		assertEquals(personalBonusTile, players[0].getPersonalBoard().getPersonalBonusTile());
		assertEquals(personalBonusTile, players[1].getPersonalBoard().getPersonalBonusTile());
		assertEquals(personalBonusTile, players[2].getPersonalBoard().getPersonalBonusTile());
		assertEquals(personalBonusTile, players[3].getPersonalBoard().getPersonalBonusTile());
		assertTrue(personalBonusTiles.isEmpty());
	}

}
