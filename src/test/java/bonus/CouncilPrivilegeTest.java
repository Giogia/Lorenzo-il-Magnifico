package bonus;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BONUS.AddResourceBonus;
import it.polimi.ingsw.BONUS.CouncilPrivilegeBonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.FaithPoints;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.RESOURCE.Servants;
import it.polimi.ingsw.RESOURCE.VictoryPoints;
import it.polimi.ingsw.RESOURCE.Wood;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.manager.ConnectionManagerRmiServerImpl;
import it.polimi.ingsw.manager.User;
import it.polimi.ingsw.view.CliRmiView;

public class CouncilPrivilegeTest {

	@Test
	public void onePrivilegeTest() throws MyException, IOException, TimeExpiredException {
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
		ArrayList<ResourceBonus> resourceBonus = new ArrayList<>();
		
		ArrayList<Resource> resources1 = new ArrayList<>();
		resources1.add(new Coins(3, 1));
		
		ArrayList<Resource> resources2 = new ArrayList<>();
		resources2.add(new MilitaryPoints(7, 1));
		
		ArrayList<Resource> resources3 = new ArrayList<>();
		resources3.add(new Wood(2, 1));
		resources3.add(new Servants(1, 1));
		
		ArrayList<Resource> resources4 = new ArrayList<>();
		resources4.add(new FaithPoints(4, 1));
		
		ArrayList<Resource> resources5 = new ArrayList<>();
		resources5.add(new VictoryPoints(1, 1));
		
		resourceBonus.add(new AddResourceBonus(resources1));
		resourceBonus.add(new AddResourceBonus(resources2));
		resourceBonus.add(new AddResourceBonus(resources3));
		resourceBonus.add(new AddResourceBonus(resources4));
		resourceBonus.add(new AddResourceBonus(resources5));
		
		when(dataFromFile.getCouncilPrivileges()).thenReturn(resourceBonus);
		
		CliRmiView cliRmiView = new CliRmiView();
		ConnectionManagerRmiServerImpl con1 = mock(ConnectionManagerRmiServerImpl.class);
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
		
		
		CouncilPrivilegeBonus councilPrivilegeBonus = new CouncilPrivilegeBonus(1);
		assertEquals("1 council privilege \n", councilPrivilegeBonus.getDescription());
		
		councilPrivilegeBonus.getImmediateBonus(players[0]);
		
		assertEquals(3, players[0].getPersonalBoard().getResource(ResourceType.coins).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.servants).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.wood).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.stones).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.militaryPoints).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
	}

	@Test
	public void threePrivilegeTest() throws MyException, IOException, TimeExpiredException {
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
		ArrayList<ResourceBonus> resourceBonus = new ArrayList<>();
		
		ArrayList<Resource> resources1 = new ArrayList<>();
		resources1.add(new Coins(3, 1));
		
		ArrayList<Resource> resources2 = new ArrayList<>();
		resources2.add(new MilitaryPoints(7, 1));
		
		ArrayList<Resource> resources3 = new ArrayList<>();
		resources3.add(new Wood(2, 1));
		resources3.add(new Servants(1, 1));
		
		ArrayList<Resource> resources4 = new ArrayList<>();
		resources4.add(new FaithPoints(4, 1));
		
		ArrayList<Resource> resources5 = new ArrayList<>();
		resources5.add(new VictoryPoints(1, 1));
		
		resourceBonus.add(new AddResourceBonus(resources1));
		resourceBonus.add(new AddResourceBonus(resources2));
		resourceBonus.add(new AddResourceBonus(resources3));
		resourceBonus.add(new AddResourceBonus(resources4));
		resourceBonus.add(new AddResourceBonus(resources5));
		
		when(dataFromFile.getCouncilPrivileges()).thenReturn(resourceBonus);
		
		CliRmiView cliRmiView = new CliRmiView();
		ConnectionManagerRmiServerImpl con1 = mock(ConnectionManagerRmiServerImpl.class);
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
		
		
		CouncilPrivilegeBonus councilPrivilegeBonus = new CouncilPrivilegeBonus(3);
		
		assertEquals("3 different council privileges \n", councilPrivilegeBonus.getDescription());
		
		councilPrivilegeBonus.getImmediateBonus(players[0]);
		
		assertEquals(3, players[0].getPersonalBoard().getResource(ResourceType.coins).getAmount());
		assertEquals(1, players[0].getPersonalBoard().getResource(ResourceType.servants).getAmount());
		assertEquals(2, players[0].getPersonalBoard().getResource(ResourceType.wood).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.stones).getAmount());
		assertEquals(7, players[0].getPersonalBoard().getResource(ResourceType.militaryPoints).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
	}

}
