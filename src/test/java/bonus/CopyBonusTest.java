package bonus;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BONUS.AddResourceBonus;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ADVANCED.CopyBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentAddResourceBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.CARD.OncePerRoundLeaderCard;
import it.polimi.ingsw.CARD.PermanentLeaderCard;
import it.polimi.ingsw.Cli.CliRmiView;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.manager.ConnectionManagerRmiServerImpl;
import it.polimi.ingsw.manager.User;

public class CopyBonusTest {

	@Test
	public void oncePerRoundTest() throws IOException, MyException, TimeExpiredException {
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

		ConnectionManagerRmiServerImpl con1 = mock(ConnectionManagerRmiServerImpl.class);
		CliRmiView cliRmiView = new CliRmiView();
		ArrayList<User> users = new ArrayList<>();
		for(int i = 0; i < 4; i++){
			User user = mock(User.class);
			user.setPlayer(players[i]);
			users.add(user);
			when(user.getConnectionType()).thenReturn(true);
			when(user.getCliRmi()).thenReturn(cliRmiView);
			when(user.getPlayer()).thenReturn(players[i]);
			when(user.getConnectionManagerRmiServerImpl()).thenReturn(con1);
		}
		ConnectionManagerImpl.getUsersInGame().addAll(users);

		when(con1.getIsAvailable()).thenReturn(true);
		when(con1.getStringReceived()).thenReturn("1");
		
		CopyBonus copyBonus = new CopyBonus();
		ArrayList<Bonus> cardBonus = new ArrayList<>();
		cardBonus.add(copyBonus);
		OncePerRoundLeaderCard copyLeaderCard = new OncePerRoundLeaderCard("Copy", cardBonus, null, null, 0);
		
		assertEquals("You can copy the effect of a Leader Card", copyBonus.getDescription());
		
		ArrayList<Bonus> copiedBonus = new ArrayList<>();
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new Coins(2, 1));
		copiedBonus.add(new AddResourceBonus(resources));
		OncePerRoundLeaderCard copiedCard = new OncePerRoundLeaderCard("Copied", copiedBonus, null, null, 0);
		
		players[0].getPersonalBoard().getActivatedLeaderCards().add(copiedCard);
		
		players[1].getPersonalBoard().getActivatedLeaderCards().add(copyLeaderCard);
		
		copyBonus.getImmediateBonus(players[1]);
		
		LeaderCard playerLeaderCard = players[1].getPersonalBoard().getActivatedLeaderCards().get(0);
		
		assertNotEquals(copyLeaderCard, playerLeaderCard);
		
		assertEquals(copiedBonus, playerLeaderCard.getBonus());
		
	}

	
	@Test
	public void permanentTest() throws IOException, MyException, TimeExpiredException {
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

		ConnectionManagerRmiServerImpl con1 = mock(ConnectionManagerRmiServerImpl.class);
		CliRmiView cliRmiView = new CliRmiView();
		ArrayList<User> users = new ArrayList<>();
		for(int i = 0; i < 4; i++){
			User user = mock(User.class);
			user.setPlayer(players[i]);
			users.add(user);
			when(user.getConnectionType()).thenReturn(true);
			when(user.getCliRmi()).thenReturn(cliRmiView);
			when(user.getPlayer()).thenReturn(players[i]);
			when(user.getConnectionManagerRmiServerImpl()).thenReturn(con1);
		}
		ConnectionManagerImpl.getUsersInGame().addAll(users);

		when(con1.getIsAvailable()).thenReturn(true);
		when(con1.getStringReceived()).thenReturn("1");
		
		CopyBonus copyBonus = new CopyBonus();
		ArrayList<Bonus> cardBonus = new ArrayList<>();
		cardBonus.add(copyBonus);
		OncePerRoundLeaderCard copyLeaderCard = new OncePerRoundLeaderCard("Copy", cardBonus, null, null, 0);
		
		assertEquals("You can copy the effect of a Leader Card", copyBonus.getDescription());
		
		ArrayList<Bonus> copiedBonus = new ArrayList<>();
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new Coins(2, 1));
		copiedBonus.add(new PermanentAddResourceBonus(resources));
		PermanentLeaderCard copiedCard = new PermanentLeaderCard("Copied", null, null, copiedBonus, 0);
		
		players[0].getPersonalBoard().getActivatedLeaderCards().add(copiedCard);
		
		players[1].getPersonalBoard().getActivatedLeaderCards().add(copyLeaderCard);
		
		copyBonus.getImmediateBonus(players[1]);
		
		LeaderCard playerLeaderCard = players[1].getPersonalBoard().getActivatedLeaderCards().get(0);
		
		assertNotEquals(copyLeaderCard, playerLeaderCard);
		
		assertEquals(copiedBonus, playerLeaderCard.getBonus());
		
		assertEquals(copiedBonus.get(0), players[1].getPersonalBoard().getPermanentBonus().get(0));
	}

	
	@Test
	public void nullTest() throws IOException, MyException, TimeExpiredException {
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

		ConnectionManagerRmiServerImpl con1 = mock(ConnectionManagerRmiServerImpl.class);
		CliRmiView cliRmiView = new CliRmiView();
		ArrayList<User> users = new ArrayList<>();
		for(int i = 0; i < 4; i++){
			User user = mock(User.class);
			user.setPlayer(players[i]);
			users.add(user);
			when(user.getConnectionType()).thenReturn(true);
			when(user.getCliRmi()).thenReturn(cliRmiView);
			when(user.getPlayer()).thenReturn(players[i]);
			when(user.getConnectionManagerRmiServerImpl()).thenReturn(con1);
		}
		ConnectionManagerImpl.getUsersInGame().addAll(users);

		when(con1.getIsAvailable()).thenReturn(true);
		when(con1.getStringReceived()).thenReturn("1");
		
		CopyBonus copyBonus = new CopyBonus();
		ArrayList<Bonus> cardBonus = new ArrayList<>();
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new Coins(2, 1));
		cardBonus.add(new AddResourceBonus(resources));
		cardBonus.add(copyBonus);
		OncePerRoundLeaderCard copyLeaderCard = new OncePerRoundLeaderCard("Copy", cardBonus, null, null, 0);
		
		assertEquals("You can copy the effect of a Leader Card", copyBonus.getDescription());
		
		players[1].getPersonalBoard().getActivatedLeaderCards().add(copyLeaderCard);
		
		copyBonus.getImmediateBonus(players[1]);
		
		LeaderCard playerLeaderCard = players[1].getPersonalBoard().getActivatedLeaderCards().get(0);
		
		assertEquals(copyLeaderCard, playerLeaderCard);
		
	}
	
	@Test
	public void lastTest() throws IOException, MyException, TimeExpiredException {
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

		ConnectionManagerRmiServerImpl con1 = mock(ConnectionManagerRmiServerImpl.class);
		CliRmiView cliRmiView = new CliRmiView();
		ArrayList<User> users = new ArrayList<>();
		for(int i = 0; i < 4; i++){
			User user = mock(User.class);
			user.setPlayer(players[i]);
			users.add(user);
			when(user.getConnectionType()).thenReturn(true);
			when(user.getCliRmi()).thenReturn(cliRmiView);
			when(user.getPlayer()).thenReturn(players[i]);
			when(user.getConnectionManagerRmiServerImpl()).thenReturn(con1);
		}
		ConnectionManagerImpl.getUsersInGame().addAll(users);

		when(con1.getIsAvailable()).thenReturn(true);
		when(con1.getStringReceived()).thenReturn("1");
		
		CopyBonus copyBonus = new CopyBonus();
		
		assertEquals("You can copy the effect of a Leader Card", copyBonus.getDescription());
		
		copyBonus.getImmediateBonus(players[1]);
		
		assertTrue(players[1].getPersonalBoard().getActivatedLeaderCards().isEmpty());
		
	}
}
