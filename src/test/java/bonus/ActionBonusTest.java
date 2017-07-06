package bonus;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.BONUS.ActionBonus;
import it.polimi.ingsw.BONUS.AddResourceBonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.Cli.CliRmiView;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.manager.ConnectionManagerRmiServerImpl;
import it.polimi.ingsw.manager.User;
import it.polimi.ingsw.minigame.Update;

public class ActionBonusTest {

	@Test
	public void test() throws MyException, IOException, TimeExpiredException {
		HashMap<ActionZone, Integer> actionMap = new HashMap<>();
		ActionZone actionZone = new Tower(DevelopmentCardType.character);
		actionMap.put(actionZone, 5);
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new Coins(2, 1));
		ActionBonus actionBonus = new ActionBonus(actionMap, resources);
		
		assertEquals("you can do an action in: \nTower character whose value is 5\nhas a bonus of: \n2 Coins", actionBonus.getDescription());
		
		ActionBonus actionBonus2 = new ActionBonus(actionMap, null);
		
		assertEquals("you can do an action in: \nTower character whose value is 5\n", actionBonus2.getDescription());
		
		Player gigi = new Player("Gigi", Color.RED);
		Player player = new Player("Player", Color.GREEN);
		Player[] players = new Player[2];
		players[0] = gigi;
		players[1] = player;
		Game game = mock(Game.class);
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
		players[0].setBoard(board);
		players[1].setBoard(board);
		Character character = new Character("Character", new Coins(3, 1) , 1, null, null);
		Tower characterTower = board.getTower(DevelopmentCardType.character);
		for (Position position : characterTower.getPositions()) {
			((TowerFloor) position).setDevelopmentCard(character);
		}
		ConnectionManagerRmiServerImpl con1 = mock(ConnectionManagerRmiServerImpl.class);
		CliRmiView cliRmiView = new CliRmiView();
		ArrayList<User> users = new ArrayList<>();
		for(int i = 0; i < 2; i++){
			User user = mock(User.class);
			user.setPlayer(players[i]);
			users.add(user);
			Update.getInstance().addUser(user);
			when(user.getConnectionType()).thenReturn(true);
			when(user.getCliRmi()).thenReturn(cliRmiView);
			when(user.getPlayer()).thenReturn(players[i]);
			when(user.getConnectionManagerRmiServerImpl()).thenReturn(con1);
		}
		ConnectionManagerImpl.getUsersInGame().addAll(users);
		when(con1.getIsAvailable()).thenReturn(true);
		when(con1.getStringReceived()).thenReturn("1");
		
		gigi.getPersonalBoard().getResource(ResourceType.servants).addAmount(20);
		gigi.getPersonalBoard().getResource(ResourceType.coins).addAmount(5);
		actionBonus.getImmediateBonus(gigi);
		
		assertEquals(character, gigi.getPersonalBoard().getCardContainer(DevelopmentCardType.character).getDevelopmentCards().get(0));
		assertEquals(4, gigi.getPersonalBoard().getResource(ResourceType.coins).getAmount());
	}

}
