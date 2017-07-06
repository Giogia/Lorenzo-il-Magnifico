package it.polimi.ingsw.handler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;
import org.mockito.Mock;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.BONUS.AddResourceBonus;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.Cli.CliRmi;
import it.polimi.ingsw.Cli.CliRmiCallback;
import it.polimi.ingsw.Cli.CliRmiView;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.HANDLER.ServantsHandler;
import it.polimi.ingsw.HANDLER.TowerHandler;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.RESOURCE.Servants;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.manager.ConnectionManagerRmiServerImpl;
import it.polimi.ingsw.manager.Manager;
import it.polimi.ingsw.manager.User;

public class TowerHandlerTest {


	@Test
	public void test() throws Exception{
		Player gigi = mock(Player.class);
		Player player = mock(Player.class);
		Player[] players = new Player[2];
		players[0] = gigi;
		players[1] = player;
		Game game = mock(Game.class);
		game.setPlayers(players);
		Board board = mock(Board.class);
		Dice dice = new Dice(DiceColour.White);
		dice.setValue();
		FamilyMember familyMember = new FamilyMember(dice, player);
		ArrayList<ArrayList<ImmediateBonus>> positionBonus = new ArrayList<>();
		for (int i = 0; i < 4; i++){
			ArrayList<Resource> resources = new ArrayList<>();
			ImmediateBonus bonus = new AddResourceBonus(resources);
			ArrayList<ImmediateBonus> arrayBonus = new ArrayList<>();
			arrayBonus.add(bonus);
			positionBonus.add(arrayBonus);
		}
		DevelopmentCardType developmentCardType = DevelopmentCardType.territory;
		when(board.getGame()).thenReturn(game);
		DataFromFile dataFromFile = mock(DataFromFile.class);
		when(game.getData()).thenReturn(dataFromFile);
		when(dataFromFile.getTowerPositionBonus(developmentCardType)).thenReturn(positionBonus);
		Tower tower = new Tower(developmentCardType, board);
		TowerFloor towerFloor = (TowerFloor)tower.getPosition(0);
		ArrayList<ImmediateBonus> immediateEffect =null;
		ArrayList<Bonus> secondaryEffect = null;
		Territory territory = new Territory("name", 1, 2, immediateEffect, secondaryEffect);
		towerFloor.setDevelopmentCard(territory);
		User user = mock(User.class);
		CliRmiView cliRmi = mock(CliRmiView.class);
		when(user.getCliRmi()).thenReturn(cliRmi);
		ConnectionManagerImpl connectionManagerImpl = mock(ConnectionManagerImpl.class);
		PersonalBoard personalBoard = mock(PersonalBoard.class);
		Servants servants = mock(Servants.class);
		when(player.getPersonalBoard()).thenReturn(personalBoard);
		when(personalBoard.getResource(ResourceType.servants)).thenReturn(servants);
		when(servants.getAmount()).thenReturn(0);
		int numberOfServants = player.getPersonalBoard().getResource(ResourceType.servants).getAmount();
		when(connectionManagerImpl.askForServants(player, numberOfServants)).thenReturn(0);
		ArrayList<CardContainer> cardContainers = new ArrayList<>();
		CardContainer cardContainer = mock(CardContainer.class);
		cardContainers.add(cardContainer);
		when(personalBoard.getCardContainers()).thenReturn(cardContainers);
		when(cardContainer.getType()).thenReturn(developmentCardType);
		ArrayList<DevelopmentCard> developmentCards = new ArrayList<>();
		ConnectionManagerImpl.getUsersInGame().add(user);
		when(cardContainer.getDevelopmentCards()).thenReturn(developmentCards);
		when(user.getPlayer()).thenReturn(player);
		ConnectionManagerRmiServerImpl connectionManagerRmiServerImpl = mock(ConnectionManagerRmiServerImpl.class);
		
		when(user.getConnectionType()).thenReturn(true);
		when(user.getConnectionManagerRmiServerImpl()).thenReturn(connectionManagerRmiServerImpl);
		when(connectionManagerRmiServerImpl.getIsAvailable()).thenReturn(true);
		when(connectionManagerRmiServerImpl.getStringReceived()).thenReturn("0");
		int[] militaryPoints =  new int[6];
		for(int i= 0; i<6; i++){
			militaryPoints[i] = 0;
		}
		when(player.getBoard()).thenReturn(board);
		MilitaryPoints milPoints = new MilitaryPoints(1, 1);
		when(personalBoard.getResource(ResourceType.militaryPoints)).thenReturn(milPoints);
		when(dataFromFile.getMilitaryRequirement()).thenReturn(militaryPoints);
		PassTurnController passTurnController = mock(PassTurnController.class);
		when(board.getPassTurnController()).thenReturn(passTurnController);
		
		assertEquals(true, TowerHandler.handle(familyMember, tower, towerFloor));
		
	}

}
