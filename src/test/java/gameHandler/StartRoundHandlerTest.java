package gameHandler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.CARD.Venture;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.HANDLER.GAME.StartRoundHandler;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.manager.User;
import it.polimi.ingsw.view.CliRmiView;

public class StartRoundHandlerTest {

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
		
		ArrayList<DevelopmentCard> buildingsPeriod1 = new ArrayList<>();
		ArrayList<DevelopmentCard> buildingsPeriod2 = new ArrayList<>();
		ArrayList<DevelopmentCard> buildingsPeriod3 = new ArrayList<>();
		Building buildingPer1 = new Building("building period 1", 0, null, 1, null, null);
		Building buildingPer2 = new Building("building period 2", 0, null, 2, null, null);
		Building buildingPer3 = new Building("building period 3", 0, null, 3, null, null);
		
		ArrayList<DevelopmentCard> charactersPeriod1 = new ArrayList<>();
		ArrayList<DevelopmentCard> charactersPeriod2 = new ArrayList<>();
		ArrayList<DevelopmentCard> charactersPeriod3 = new ArrayList<>();
		Character characterPer1 = new Character("character period 1", null, 1, null, null);
		Character characterPer2 = new Character("character period 2", null, 2, null, null);
		Character characterPer3 = new Character("character period 3", null, 3, null, null);
		
		ArrayList<DevelopmentCard> territoriesPeriod1 = new ArrayList<>();
		ArrayList<DevelopmentCard> territoriesPeriod2 = new ArrayList<>();
		ArrayList<DevelopmentCard> territoriesPeriod3 = new ArrayList<>();
		Territory territoryPer1 = new Territory("territory period 1", 1, 0, null, null);
		Territory territoryPer2 = new Territory("territory period 2", 2, 0, null, null);
		Territory territoryPer3 = new Territory("territory period 3", 3, 0, null, null);
		
		ArrayList<DevelopmentCard> venturesPeriod1 = new ArrayList<>();
		ArrayList<DevelopmentCard> venturesPeriod2 = new ArrayList<>();
		ArrayList<DevelopmentCard> venturesPeriod3 = new ArrayList<>();
		ArrayList<Resource> cost = new ArrayList<>();
		cost.add(new Coins(0, 1));
		Venture venturePer1 = new Venture("venture period 1", 0, cost, null, 1, null, null);
		Venture venturePer2 = new Venture("venture period 2", 0, cost, null, 2, null, null);
		Venture venturePer3 = new Venture("venture period 3", 0, cost, null, 3, null, null);
	
		for (int i = 0; i< 4; i++){
			buildingsPeriod1.add(buildingPer1);
			buildingsPeriod2.add(buildingPer2);
			buildingsPeriod3.add(buildingPer3);
			
			charactersPeriod1.add(characterPer1);
			charactersPeriod2.add(characterPer2);
			charactersPeriod3.add(characterPer3);
			
			venturesPeriod1.add(venturePer1);
			venturesPeriod2.add(venturePer2);
			venturesPeriod3.add(venturePer3);
			
			territoriesPeriod1.add(territoryPer1);
			territoriesPeriod2.add(territoryPer2);
			territoriesPeriod3.add(territoryPer3);
		}
		
		when(dataFromFile.getBuildingsForPeriod(1)).thenReturn(buildingsPeriod1);
		when(dataFromFile.getBuildingsForPeriod(2)).thenReturn(buildingsPeriod2);
		when(dataFromFile.getBuildingsForPeriod(3)).thenReturn(buildingsPeriod3);
		
		when(dataFromFile.getCharactersForPeriod(1)).thenReturn(charactersPeriod1);
		when(dataFromFile.getCharactersForPeriod(2)).thenReturn(charactersPeriod2);
		when(dataFromFile.getCharactersForPeriod(3)).thenReturn(charactersPeriod3);
		
		when(dataFromFile.getVenturesForPeriod(1)).thenReturn(venturesPeriod1);
		when(dataFromFile.getVenturesForPeriod(2)).thenReturn(venturesPeriod2);
		when(dataFromFile.getVenturesForPeriod(3)).thenReturn(venturesPeriod3);
		
		when(dataFromFile.getTerritoriesForPeriod(1)).thenReturn(territoriesPeriod1);
		when(dataFromFile.getTerritoriesForPeriod(2)).thenReturn(territoriesPeriod2);
		when(dataFromFile.getTerritoriesForPeriod(3)).thenReturn(territoriesPeriod3);

		CliRmiView cliRmiView = new CliRmiView();
		ArrayList<User> users = new ArrayList<>();
		for(int i = 0; i < 4; i++){
			User user = new User(cliRmiView);
			user.setPlayer(players[i]);
			users.add(user);
		}
		ConnectionManagerImpl.getUsersInGame().addAll(users);
		
		StartRoundHandler.handle(1, players, board);
		
		for (Player player1 : players) {
			for (Player player2 : players) {
				ArrayList<FamilyMember> familyMembers1 = player1.getFamilyMembers();
				for (FamilyMember familyMember : familyMembers1) {
					FamilyMember familyMember2 = findFamilyMember(familyMember, player2);
					assertEquals(familyMember.getValue(), familyMember2.getValue());
				}
 			}
		}
		
		Tower buildingTower = board.getTower(DevelopmentCardType.building);
		Tower territoryTower = board.getTower(DevelopmentCardType.territory);
		Tower ventureTower = board.getTower(DevelopmentCardType.venture);
		Tower characterTower = board.getTower(DevelopmentCardType.character);
		
		for (Position position : buildingTower.getPositions()) {
			assertEquals(buildingPer1, ((TowerFloor) position).getDevelopmentCard());
		}
		for (Position position : territoryTower.getPositions()) {
			assertEquals(territoryPer1, ((TowerFloor) position).getDevelopmentCard());
		}
		for (Position position : ventureTower.getPositions()) {
			assertEquals(venturePer1, ((TowerFloor) position).getDevelopmentCard());
		}
		for (Position position : characterTower.getPositions()) {
			assertEquals(characterPer1, ((TowerFloor) position).getDevelopmentCard());
		}
		
		
		
		
		
		StartRoundHandler.handle(2, players, board);

		buildingTower = board.getTower(DevelopmentCardType.building);
		territoryTower = board.getTower(DevelopmentCardType.territory);
		ventureTower = board.getTower(DevelopmentCardType.venture);
		characterTower = board.getTower(DevelopmentCardType.character);
		
		for (Position position : buildingTower.getPositions()) {
			assertEquals(buildingPer2, ((TowerFloor) position).getDevelopmentCard());
		}
		for (Position position : territoryTower.getPositions()) {
			assertEquals(territoryPer2, ((TowerFloor) position).getDevelopmentCard());
		}
		for (Position position : ventureTower.getPositions()) {
			assertEquals(venturePer2, ((TowerFloor) position).getDevelopmentCard());
		}
		for (Position position : characterTower.getPositions()) {
			assertEquals(characterPer2, ((TowerFloor) position).getDevelopmentCard());
		}
		
		
		
		StartRoundHandler.handle(3, players, board);

		buildingTower = board.getTower(DevelopmentCardType.building);
		territoryTower = board.getTower(DevelopmentCardType.territory);
		ventureTower = board.getTower(DevelopmentCardType.venture);
		characterTower = board.getTower(DevelopmentCardType.character);
		
		for (Position position : buildingTower.getPositions()) {
			assertEquals(buildingPer3, ((TowerFloor) position).getDevelopmentCard());
		}
		for (Position position : territoryTower.getPositions()) {
			assertEquals(territoryPer3, ((TowerFloor) position).getDevelopmentCard());
		}
		for (Position position : ventureTower.getPositions()) {
			assertEquals(venturePer3, ((TowerFloor) position).getDevelopmentCard());
		}
		for (Position position : characterTower.getPositions()) {
			assertEquals(characterPer3, ((TowerFloor) position).getDevelopmentCard());
		}
	}

	private FamilyMember findFamilyMember(FamilyMember familyMember, Player player2) {
		ArrayList<FamilyMember> familyMembers = player2.getFamilyMembers();
		for (FamilyMember familyMember2 : familyMembers) {
			if (familyMember.getDice().getDiceColour().equals(familyMember2.getDice().getDiceColour()))
				return familyMember2;
		}
		return null;
	}

}
