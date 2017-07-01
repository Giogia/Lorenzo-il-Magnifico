package gameHandler;

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
import it.polimi.ingsw.BONUS.ADVANCED.LoseVictoryPointsPerResourceBonus;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.CARD.Venture;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.HANDLER.GAME.EndGameHandler;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.RESOURCE.Servants;
import it.polimi.ingsw.RESOURCE.Stones;
import it.polimi.ingsw.RESOURCE.VictoryPoints;
import it.polimi.ingsw.RESOURCE.Wood;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.manager.User;
import it.polimi.ingsw.view.CliRmiView;

public class EndGameHandlerTest {

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
		
		ArrayList<Player> playerOrder = new ArrayList<>();
		for(int i = 0; i < 4; i++){
			playerOrder.add(players[i]);
		}
		when(game.getRoundOrder()).thenReturn(playerOrder);
		
		players[0].getPersonalBoard().getResource(ResourceType.coins).addAmount(2);
		players[0].getPersonalBoard().getResource(ResourceType.wood).addAmount(3);
		players[0].getPersonalBoard().getResource(ResourceType.stones).addAmount(4);
		players[0].getPersonalBoard().getResource(ResourceType.servants).addAmount(2);
		//player[0] 2 punti vittoria
		
		Building building = new Building(null, 0, null, 1, null, null);
		Territory territory = new Territory(null, 1, 0, null, null);
		
		players[1].getPersonalBoard().getCardContainer(DevelopmentCardType.building).add(building);
		players[1].getPersonalBoard().getCardContainer(DevelopmentCardType.building).add(building);
		players[1].getPersonalBoard().getCardContainer(DevelopmentCardType.territory).add(territory);
		players[1].getPersonalBoard().getCardContainer(DevelopmentCardType.territory).add(territory);
		players[1].getPersonalBoard().getResource(ResourceType.militaryPoints).addAmount(5);
		
		int[] array1 = new int[7];
		array1[0] = 0;
		array1[1] = 0;
		array1[2] = 3;
		when(dataFromFile.getVictoryPointsPerCard(DevelopmentCardType.building)).thenReturn(array1);
		when(dataFromFile.getVictoryPointsPerCard(DevelopmentCardType.territory)).thenReturn(array1);
		when(dataFromFile.getVictoryPointsPerCard(DevelopmentCardType.character)).thenReturn(array1);
		when(dataFromFile.getFromFaithPointsToVictoryPoints()).thenReturn(array1);
		//player[1] 6 punti vittoria
		
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new VictoryPoints(7, 1));
		AddResourceBonus addResourceBonus = new AddResourceBonus(resources);
		ArrayList<Bonus> secondaryEffect = new ArrayList<>();
		secondaryEffect.add(addResourceBonus);
		Venture venture = new Venture(null, 0, new ArrayList<>(), null, 1, null, secondaryEffect);
		players[2].getPersonalBoard().getCardContainer(DevelopmentCardType.venture).add(venture);
		players[2].getPersonalBoard().getResource(ResourceType.militaryPoints).addAmount(5);
		//player[2] 7 punti vittoria
		
		players[3].getPersonalBoard().getResource(ResourceType.coins).addAmount(20);
		players[3].getPersonalBoard().getResource(ResourceType.wood).addAmount(30);
		players[3].getPersonalBoard().getResource(ResourceType.stones).addAmount(40);
		players[3].getPersonalBoard().getResource(ResourceType.servants).addAmount(20);
		players[3].getPersonalBoard().getResource(ResourceType.militaryPoints).addAmount(15);
		//player[3] 22 punti vittoria
		
		ArrayList<Resource> malus = new ArrayList<>();
		malus.add(new Coins(1, 1));
		malus.add(new Servants(1, 1));
		malus.add(new Stones(1, 1));
		malus.add(new Wood(1, 1));
		LoseVictoryPointsPerResourceBonus loseVictoryPointsPerResourceBonus = new LoseVictoryPointsPerResourceBonus(malus);
		players[3].getPersonalBoard().getPermanentBonus().add(loseVictoryPointsPerResourceBonus);
		//player[3] -88 punti vittoria
		
		CliRmiView cliRmiView = new CliRmiView();
		ArrayList<User> users = new ArrayList<>();
		for(int i = 0; i < 4; i++){
			User user = mock(User.class);
			user.setPlayer(players[i]);
			users.add(user);
			when(user.getConnectionType()).thenReturn(true);
			when(user.getCliRmi()).thenReturn(cliRmiView);
			when(user.getPlayer()).thenReturn(players[i]);
		}
		ConnectionManagerImpl.getUsersInGame().addAll(users);
		
		int[] victoryPointsFromMilitary = new int[2];
		victoryPointsFromMilitary[0] = 17;
		victoryPointsFromMilitary[1] = 2;
		when(dataFromFile.getFromMilitaryPointsToVictoryPoints()).thenReturn(victoryPointsFromMilitary);
		
		EndGameHandler.handle(board);
		
		assertEquals(2, players[0].getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
		assertEquals(5, players[1].getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
		assertEquals(9, players[2].getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
		assertEquals(-71, players[3].getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
	}

}
