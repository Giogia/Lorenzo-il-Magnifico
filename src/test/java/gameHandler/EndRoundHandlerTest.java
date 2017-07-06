package gameHandler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.CouncilPalace;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ADVANCED.CanFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.CARD.OncePerRoundLeaderCard;
import it.polimi.ingsw.Cli.CliRmiView;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.GC_15.RoundOrder;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.HANDLER.GAME.EndRoundHandler;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.manager.ConnectionManagerRmiServerImpl;
import it.polimi.ingsw.manager.User;

public class EndRoundHandlerTest {

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
		
		for (int i = 1; i < 4; i++) {
			players[i].getPersonalBoard().getResource(ResourceType.faithPoints).setAmount(5);
		}
		players[1].getPersonalBoard().getResource(ResourceType.faithPoints).addAmount(20);
		ArrayList<PermanentBonus> malus = new ArrayList<>();
		CanFamilyMemberBonus canFamilyMemberBonus = new CanFamilyMemberBonus(true, new HashMap<>());
		malus.add(canFamilyMemberBonus);
		ExcommunicationTile[] excommunicationTiles = new ExcommunicationTile[3];
		excommunicationTiles[0] = new ExcommunicationTile(1, 1, malus);
		excommunicationTiles[1] = new ExcommunicationTile(1, 2, malus);
		excommunicationTiles[2] = new ExcommunicationTile(1, 3, malus);
		
		board.setExcommunicationTiles(excommunicationTiles);

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
		
		ConnectionManagerRmiServerImpl con1 = mock(ConnectionManagerRmiServerImpl.class);
		ConnectionManagerRmiServerImpl con2 = mock(ConnectionManagerRmiServerImpl.class);
		
		for (int i = 0; i < 3; i++){
			when(users.get(i).getConnectionManagerRmiServerImpl()).thenReturn(con1);
		}
		when(users.get(3).getConnectionManagerRmiServerImpl()).thenReturn(con2);
		
		RoundOrder roundOrder = new RoundOrder();
		ArrayList<Player> playerOrder = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			playerOrder.add(players[i]);
		}
		roundOrder.setPlayers(playerOrder);
		
		CouncilPalace councilPalace = board.getCouncilPalace();
		Dice dice = new Dice(DiceColour.Orange);
		dice.setValue();
		FamilyMember familyMember1 = new FamilyMember(dice, players[3]);
		FamilyMember familyMember2 = new FamilyMember(dice, players[1]);
		Position councilPalacePosition = councilPalace.getPosition(0);
		councilPalacePosition.addFamilyMember(familyMember1);
		councilPalacePosition.addFamilyMember(familyMember2);
		
		ArrayList<Player> newPlayerOrder = new ArrayList<>();
		newPlayerOrder.add(players[3]);
		newPlayerOrder.add(players[1]);
		newPlayerOrder.add(players[0]);
		newPlayerOrder.add(players[2]);
		
		when(con1.getIsAvailable()).thenReturn(true);
		when(con1.getStringReceived()).thenReturn("1");
		when(con2.getIsAvailable()).thenReturn(true);
		when(con2.getStringReceived()).thenReturn("2");
		
		int[] fromFaithToVictoryPoints = new int[16];
		fromFaithToVictoryPoints[5] = 99;
		fromFaithToVictoryPoints[15] = 101;
		when(dataFromFile.getFromFaithPointsToVictoryPoints()).thenReturn(fromFaithToVictoryPoints);
		when(dataFromFile.getMinimumFaithPoints(1)).thenCallRealMethod();

		ArrayList<Player> skipped = new ArrayList<>();
		when(game.getSkipActionPlayers()).thenReturn(skipped);
		
		LeaderCard leaderCard = new OncePerRoundLeaderCard("LeaderCard", null, null, null, 0);
		players[0].getPersonalBoard().getActivatedLeaderCards().add(leaderCard);
		
		
		EndRoundHandler.handle(board, roundOrder, 2);
		
		
		assertEquals(canFamilyMemberBonus, players[0].getPersonalBoard().getPermanentBonus().get(0));
		assertEquals(canFamilyMemberBonus, players[3].getPersonalBoard().getPermanentBonus().get(0));
		assertTrue(players[1].getPersonalBoard().getPermanentBonus().isEmpty());
		assertTrue(players[2].getPersonalBoard().getPermanentBonus().isEmpty());
		
		assertTrue(board.getCouncilPalace().getPosition(0).getFamilyMembers().isEmpty());
		
		assertEquals(0, players[1].getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		assertEquals(101, players[1].getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
		
		assertEquals(0, players[2].getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		assertEquals(99, players[2].getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());

		assertEquals(5, players[3].getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		assertEquals(0, players[3].getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
		
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
		
		for(int i = 0; i < 4; i++){
			assertEquals(newPlayerOrder.get(i), roundOrder.getPlayers().get(i));
		}
		
		assertEquals(null, board.getPassTurnController().getLastMove());
		
		assertEquals(leaderCard, players[0].getPersonalBoard().getOncePerRoundBonusLeaderCard().get(0));
	}

}
