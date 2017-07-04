package bonus;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.HarvestArea;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BONUS.AddResourceBonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PositionFamilyMemberBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.HANDLER.ADVANCED.ZoneFamilyMemberHandler;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.RESOURCE.FaithPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.manager.ConnectionManagerRmiServerImpl;
import it.polimi.ingsw.manager.User;
import it.polimi.ingsw.view.CliRmiView;

public class PositionFamilyMemberBonusTest {

	@Test
	public void test() throws MyException {
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
		
		
		HashMap<ActionZone, Integer> positionBonus = new HashMap<>();
		Tower tower = new Tower(DevelopmentCardType.building);
		HarvestArea harvestArea = new HarvestArea();
		ProductionArea productionArea = new ProductionArea();
		
		positionBonus.put(productionArea, 2);
		positionBonus.put(harvestArea, -3);
		positionBonus.put(tower, 5);
		
		HashMap<ActionZone, Integer> positionBonus2 = new HashMap<>();
		positionBonus2.put(productionArea, 2);
		
		PositionFamilyMemberBonus positionFamilyMemberBonus = new PositionFamilyMemberBonus(positionBonus);
		
		assertEquals(positionBonus, positionFamilyMemberBonus.getPositionBonus());
		
		PositionFamilyMemberBonus positionFamilyMemberBonus2 = new PositionFamilyMemberBonus(positionBonus2);

		assertEquals("Increase permanently the value of your family members of: \n2 if you go to Production Area\n", positionFamilyMemberBonus2.getDescription());
		
		positionFamilyMemberBonus2.getPermanentBonus(players[0]);
		
		assertEquals(positionFamilyMemberBonus2, players[0].getPersonalBoard().getPermanentBonus().get(0));
		
		positionFamilyMemberBonus.getPermanentBonus(players[0]);
		
		Dice dice = new Dice(DiceColour.Orange);
		dice.setValue(4);
		FamilyMember familyMember = new FamilyMember(dice, players[0]);
		ZoneFamilyMemberHandler.handle(board.getHarvestArea(), familyMember);
		
		assertEquals(1, familyMember.getValue());

		ZoneFamilyMemberHandler.handle(board.getProductioArea(), familyMember);
		
		assertEquals(5, familyMember.getValue());
		
		ZoneFamilyMemberHandler.handle(board.getTower(DevelopmentCardType.building), familyMember);
		
		assertEquals(10, familyMember.getValue());
		
		positionFamilyMemberBonus.getPermanentBonus(players[0]);
		
		PositionFamilyMemberBonus playerBonus = (PositionFamilyMemberBonus) players[0].getPersonalBoard().getPermanentBonus().get(0);
		
	}

}
