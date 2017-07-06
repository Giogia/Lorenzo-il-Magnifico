package gameHandler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.Cli.CliRmiView;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.GC_15.RoundOrder;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.HANDLER.GAME.StartGameHandler;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.manager.User;

public class StartGameHandlerTest {

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
		ArrayList<PermanentBonus> permanentBonus = new ArrayList<>();
		ExcommunicationTile ex1 = new ExcommunicationTile(1, 1, permanentBonus);
		ExcommunicationTile ex2 = new ExcommunicationTile(1, 2, permanentBonus);
		ExcommunicationTile ex3 = new ExcommunicationTile(1, 3, permanentBonus);
		ArrayList<ExcommunicationTile> excommunicationTiles = new ArrayList<>();
		excommunicationTiles.add(ex1);
		excommunicationTiles.add(ex2);
		excommunicationTiles.add(ex3);
		PersonalBonusTile personalBonusTile = new PersonalBonusTile();
		ArrayList<PersonalBonusTile> personalBonusTiles = new ArrayList<>();
		for (int i = 0; i < 4 ; i++){
			personalBonusTiles.add(personalBonusTile);
		}
		when(dataFromFile.getPersonalBonusTiles()).thenReturn(personalBonusTiles);
		CliRmiView cliRmiView = new CliRmiView();
		ArrayList<User> users = new ArrayList<>();
		for(int i = 0; i < 4; i++){
			User user = new User(cliRmiView);
			user.setPlayer(players[i]);
			users.add(user);
		}
		ConnectionManagerImpl.getUsersInGame().addAll(users);
		when(dataFromFile.getExcommunicationTiles()).thenReturn(excommunicationTiles);
		RoundOrder roundOrder = new RoundOrder();
		ArrayList<Player> playerOrder = new ArrayList<>();
		for (Player player : players) {
			playerOrder.add(player);
		}
		roundOrder.setPlayers(playerOrder);
		when(game.getOrder()).thenReturn(roundOrder);
		when(game.getRoundOrder()).thenReturn(playerOrder);
		when(board.getGame().getOrder()).thenReturn(roundOrder);
		when(game.getBoard()).thenReturn(board);
		
		try {
			StartGameHandler.handle(board);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assertEquals(ex1, board.getExcommunicationTiles()[0]);
		assertEquals(ex2, board.getExcommunicationTiles()[1]);
		assertEquals(ex3, board.getExcommunicationTiles()[2]);
		
		for (int i = 0; i < 4; i++) {
			assertEquals(personalBonusTile, players[i].getPersonalBoard().getPersonalBonusTile());
			assertEquals(2, players[i].getPersonalBoard().getResource(ResourceType.wood).getAmount());	
			assertEquals(2, players[i].getPersonalBoard().getResource(ResourceType.stones).getAmount());
			assertEquals(3, players[i].getPersonalBoard().getResource(ResourceType.servants).getAmount());
			assertEquals(5+i, players[i].getPersonalBoard().getResource(ResourceType.coins).getAmount());
		}
	}

}
