package bonus;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BONUS.ADVANCED.OrderBonus;
import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.HANDLER.ADVANCED.OrderBonusHandler;

public class OrderBonusTest {

	@Test
	public void test() {
		boolean[] order = new boolean[4];
		order[0] = true;
		order[1] = true;
		
		OrderBonus orderBonus = new OrderBonus(order);
		
		assertEquals("You skip :\naction number0\naction number1\nand you do it at the end of the turn \n", orderBonus.getDescription());

	
		Player fndao = new Player(null, Color.GREEN);
		
		orderBonus.getPermanentBonus(fndao);
		
		assertEquals(orderBonus, fndao.getPersonalBoard().getPermanentBonus().get(0));
		
		boolean[] skipAction = new boolean[4];
		skipAction[0] = true;
		skipAction[2] = true;
		
		OrderBonus orderBonus2 = new OrderBonus(skipAction);
		
		orderBonus2.getPermanentBonus(fndao);
		
		OrderBonus playerBonus = (OrderBonus) fndao.getPersonalBoard().getPermanentBonus().get(0);
		
		assertTrue(playerBonus.getSkipAction()[0]);
		assertTrue(playerBonus.getSkipAction()[1]);
		assertTrue(playerBonus.getSkipAction()[2]);
		assertFalse(playerBonus.getSkipAction()[3]);
		
		Board board = mock(Board.class);
		Game game = mock(Game.class);
		fndao.setBoard(board);
		when(board.getGame()).thenReturn(game);
		PassTurnController passTurnController = new PassTurnController();
		when(board.getPassTurnController()).thenReturn(passTurnController);
		
		assertFalse(OrderBonusHandler.handle(fndao, 0));
		assertFalse(OrderBonusHandler.handle(fndao, 1));
		assertFalse(OrderBonusHandler.handle(fndao, 2));
		assertTrue(OrderBonusHandler.handle(fndao, 3));
	}

}
