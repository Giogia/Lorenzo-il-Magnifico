package it.polimi.ingsw.handler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.HANDLER.ServantsHandler;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.manager.ConnectionManagerRmiServerImpl;
import it.polimi.ingsw.manager.User;
import it.polimi.ingsw.view.CliRmiView;

public class ServantsHandlerTest {

	@Test
	public void test1() throws IOException, TimeExpiredException {
		Player player = new Player(null, Color.BLUE);
		CliRmiView cliRmiView = new CliRmiView();
		ConnectionManagerRmiServerImpl connectionManagerRmiServerImpl = mock(ConnectionManagerRmiServerImpl.class);
		User user = mock(User.class);
		when(user.getConnectionType()).thenReturn(true);
		when(user.getCliRmi()).thenReturn(cliRmiView);
		when(user.getPlayer()).thenReturn(player);
		when(user.getConnectionManagerRmiServerImpl()).thenReturn(connectionManagerRmiServerImpl);
		ConnectionManagerImpl.getUsersInGame().add(user);
		
		player.getPersonalBoard().getResource(ResourceType.servants).addAmount(45);
		
		when(connectionManagerRmiServerImpl.getIsAvailable()).thenReturn(true);
		when(connectionManagerRmiServerImpl.getStringReceived()).thenReturn("0");
		
		Dice dice = new Dice(DiceColour.Orange);
		dice.setValue(4);
		FamilyMember familyMember = new FamilyMember(dice, player);
		ArrayList<FamilyMember> familyMembers = new ArrayList<>();
		familyMembers.add(familyMember);
		player.setFamilyMember(familyMembers);
		
		assertTrue(ServantsHandler.handle(familyMember, player.getPersonalBoard().getResources()));
		assertEquals(4, familyMember.getValue());
	}
	
	
	
	@Test
	public void test2() throws IOException, TimeExpiredException {
		Player player = new Player(null, Color.BLUE);
		CliRmiView cliRmiView = new CliRmiView();
		ConnectionManagerRmiServerImpl connectionManagerRmiServerImpl = mock(ConnectionManagerRmiServerImpl.class);
		User user = mock(User.class);
		when(user.getConnectionType()).thenReturn(true);
		when(user.getCliRmi()).thenReturn(cliRmiView);
		when(user.getPlayer()).thenReturn(player);
		when(user.getConnectionManagerRmiServerImpl()).thenReturn(connectionManagerRmiServerImpl);
		ConnectionManagerImpl.getUsersInGame().add(user);
		
		player.getPersonalBoard().getResource(ResourceType.servants).addvalue(45);
		player.getPersonalBoard().getResource(ResourceType.servants).addAmount(45);;
		
		when(connectionManagerRmiServerImpl.getIsAvailable()).thenReturn(true);
		when(connectionManagerRmiServerImpl.getStringReceived()).thenReturn("3");
		
		Dice dice = new Dice(DiceColour.Orange);
		dice.setValue(4);
		FamilyMember familyMember = new FamilyMember(dice, player);
		ArrayList<FamilyMember> familyMembers = new ArrayList<>();
		familyMembers.add(familyMember);
		player.setFamilyMember(familyMembers);
		
		assertTrue(ServantsHandler.handle(familyMember, player.getPersonalBoard().getResources()));
		assertEquals(4, familyMember.getValue());
		assertEquals(42, player.getPersonalBoard().getResource(ResourceType.servants).getAmount());
	}

}
