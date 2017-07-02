package it.polimi.ingsw.handler;

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
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.HANDLER.HarvestAreaHandler;
import it.polimi.ingsw.HANDLER.ProductionAreaHandler;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.RESOURCE.FaithPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.RESOURCE.Stones;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.manager.ConnectionManagerRmiServerImpl;
import it.polimi.ingsw.manager.User;
import it.polimi.ingsw.view.CliRmiView;

public class HarvestProductionAreaHandlerTest {

	@Test
	public void test1() throws MyException, IOException, TimeExpiredException {
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
		
		PersonalBonusTile personalBonusTile = new PersonalBonusTile(new AddResourceBonus(resources), new AddResourceBonus(resources), 1, 1);
		players[0].getPersonalBoard().setPersonalBonusTile(personalBonusTile);
		
		players[0].getPersonalBoard().getResource(ResourceType.servants).addAmount(4);
		
		Dice dice = new Dice(DiceColour.Black);
		dice.setValue(3);
		FamilyMember familyMember = new FamilyMember(dice, players[0]);
		
		int[] positionMalus = new int[2];
		
		when(dataFromFile.getHarvestAreaPositionBonus()).thenReturn(positionMalus);
		
		HarvestAreaHandler.handle(familyMember, board.getHarvestArea(), board.getHarvestArea().getPosition(0));
		
		assertEquals(3, players[0].getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.coins).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.wood).getAmount());
		assertEquals(3, players[0].getPersonalBoard().getResource(ResourceType.servants).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.stones).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.militaryPoints).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
		
		assertEquals(players[0], board.getPassTurnController().getLastMove());
		
		assertNotEquals(familyMember, board.getHarvestArea().getPosition(0).getFamilyMembers().get(0));
		
		assertEquals(4, board.getHarvestArea().getPosition(0).getFamilyMembers().get(0).getValue());
	
		
	}

	@Test
	public void test2() throws MyException, IOException, TimeExpiredException {
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
		
		PersonalBonusTile personalBonusTile = new PersonalBonusTile(new AddResourceBonus(resources), new AddResourceBonus(resources), 1, 1);
		
		ArrayList<Bonus> cardBonus = new ArrayList<>();
		cardBonus.add(new AddResourceBonus(resources));
		
		Territory territory = new Territory(null, 1, 1, null, cardBonus);
		Building building = new Building(null, 1, null, 1, null, cardBonus);
		

		ArrayList<Bonus> cardBonus2 = new ArrayList<>();
		ArrayList<Resource> resources2 = new ArrayList<>();
		resources2.add(new Stones(-1, 1));
		cardBonus2.add(new AddResourceBonus(resources2));
		Territory territory2 = new Territory(null, 1, 1, null, cardBonus2);
		Building building2 = new Building(null, 1, null, 1, null, cardBonus2);
		
		players[0].getPersonalBoard().getCardContainer(DevelopmentCardType.territory).add(territory);
		players[0].getPersonalBoard().getCardContainer(DevelopmentCardType.building).add(building);
		players[0].getPersonalBoard().getCardContainer(DevelopmentCardType.territory).add(territory2);
		players[0].getPersonalBoard().getCardContainer(DevelopmentCardType.building).add(building2);
		
		players[0].getPersonalBoard().setPersonalBonusTile(personalBonusTile);
		
		players[0].getPersonalBoard().getResource(ResourceType.servants).addAmount(4);
		
		Dice dice = new Dice(DiceColour.Black);
		dice.setValue(3);
		FamilyMember familyMember = new FamilyMember(dice, players[0]);
		
		int[] positionMalus = new int[2];
		
		when(dataFromFile.getHarvestAreaPositionBonus()).thenReturn(positionMalus);
		
		HarvestAreaHandler.handle(familyMember, board.getHarvestArea(), board.getHarvestArea().getPosition(0));
		
		assertEquals(6, players[0].getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.coins).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.wood).getAmount());
		assertEquals(3, players[0].getPersonalBoard().getResource(ResourceType.servants).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.stones).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.militaryPoints).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
		
		assertEquals(players[0], board.getPassTurnController().getLastMove());
		
		assertNotEquals(familyMember, board.getHarvestArea().getPosition(0).getFamilyMembers().get(0));
		
		assertEquals(4, board.getHarvestArea().getPosition(0).getFamilyMembers().get(0).getValue());
	
		
	}
	
	
	
	@Test
	public void test3() throws MyException, IOException, TimeExpiredException {
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
		
		PersonalBonusTile personalBonusTile = new PersonalBonusTile(new AddResourceBonus(resources), new AddResourceBonus(resources), 1, 1);
		players[0].getPersonalBoard().setPersonalBonusTile(personalBonusTile);
		
		players[0].getPersonalBoard().getResource(ResourceType.servants).addAmount(4);
		
		Dice dice = new Dice(DiceColour.Black);
		dice.setValue(3);
		FamilyMember familyMember = new FamilyMember(dice, players[0]);
		
		int[] positionMalus = new int[2];
		
		when(dataFromFile.getProductionAreaPositionBonus()).thenReturn(positionMalus);
		
		ProductionAreaHandler.handle(familyMember, board.getProductioArea(), board.getProductioArea().getPosition(0));
		
		assertEquals(3, players[0].getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.coins).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.wood).getAmount());
		assertEquals(3, players[0].getPersonalBoard().getResource(ResourceType.servants).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.stones).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.militaryPoints).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
		
		assertEquals(players[0], board.getPassTurnController().getLastMove());
		
		assertNotEquals(familyMember, board.getProductioArea().getPosition(0).getFamilyMembers().get(0));
		
		assertEquals(4, board.getProductioArea().getPosition(0).getFamilyMembers().get(0).getValue());
	
		
	}

	@Test
	public void test4() throws MyException, IOException, TimeExpiredException {
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
		
		PersonalBonusTile personalBonusTile = new PersonalBonusTile(new AddResourceBonus(resources), new AddResourceBonus(resources), 1, 1);
		
		ArrayList<Bonus> cardBonus = new ArrayList<>();
		cardBonus.add(new AddResourceBonus(resources));
		
		Territory territory = new Territory(null, 1, 1, null, cardBonus);
		Building building = new Building(null, 1, null, 1, null, cardBonus);
		

		ArrayList<Bonus> cardBonus2 = new ArrayList<>();
		ArrayList<Resource> resources2 = new ArrayList<>();
		resources2.add(new Stones(-1, 1));
		cardBonus2.add(new AddResourceBonus(resources2));
		Territory territory2 = new Territory(null, 1, 1, null, cardBonus2);
		Building building2 = new Building(null, 1, null, 1, null, cardBonus2);
		
		players[0].getPersonalBoard().getCardContainer(DevelopmentCardType.territory).add(territory);
		players[0].getPersonalBoard().getCardContainer(DevelopmentCardType.building).add(building);
		players[0].getPersonalBoard().getCardContainer(DevelopmentCardType.territory).add(territory2);
		players[0].getPersonalBoard().getCardContainer(DevelopmentCardType.building).add(building2);
		
		players[0].getPersonalBoard().setPersonalBonusTile(personalBonusTile);
		
		players[0].getPersonalBoard().getResource(ResourceType.servants).addAmount(4);
		
		Dice dice = new Dice(DiceColour.Black);
		dice.setValue(3);
		FamilyMember familyMember = new FamilyMember(dice, players[0]);
		
		int[] positionMalus = new int[2];
		
		when(dataFromFile.getProductionAreaPositionBonus()).thenReturn(positionMalus);

		ProductionAreaHandler.handle(familyMember, board.getProductioArea(), board.getProductioArea().getPosition(1));
		
		assertEquals(6, players[0].getPersonalBoard().getResource(ResourceType.faithPoints).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.coins).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.wood).getAmount());
		assertEquals(3, players[0].getPersonalBoard().getResource(ResourceType.servants).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.stones).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.militaryPoints).getAmount());
		assertEquals(0, players[0].getPersonalBoard().getResource(ResourceType.victoryPoints).getAmount());
		
		assertEquals(players[0], board.getPassTurnController().getLastMove());
		
		assertNotEquals(familyMember, board.getProductioArea().getPosition(1).getFamilyMembers().get(0));
		
		assertEquals(4, board.getProductioArea().getPosition(1).getFamilyMembers().get(0).getValue());
	
		
	}

}
