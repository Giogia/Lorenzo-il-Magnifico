package it.polimi.ingsw.GC_15;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.FaithPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.RESOURCE.Wood;
import it.polimi.ingsw.BONUS.*;

public class PersonalBoardTest {


	@Test
	public void createResourcesTest() {
		PersonalBoard personalBoard = new PersonalBoard();
		
		Coins coins = new Coins(0,1);
		FaithPoints faithPoints = new FaithPoints(0, 1);
		Wood wood = new Wood(0,1);
		
		
		assertEquals(coins.getValue(),personalBoard.getResource(ResourceType.coins).getValue());
		assertEquals(coins.getAmount(),personalBoard.getResource(ResourceType.coins).getAmount());
		
		assertEquals(faithPoints.getValue(),personalBoard.getResource(ResourceType.faithPoints).getValue());
		assertEquals(faithPoints.getAmount(),personalBoard.getResource(ResourceType.faithPoints).getAmount());
		
		assertEquals(wood.getValue(),personalBoard.getResource(ResourceType.wood).getValue());
		assertEquals(wood.getAmount(),personalBoard.getResource(ResourceType.wood).getAmount());
	}
	
	public void setDevelopmentCardTest() {
		PersonalBoard personalBoard = new PersonalBoard();
		
		String name = new String("gigi");
		ArrayList<Bonus> bonus = new ArrayList<>();
		ArrayList<ImmediateBonus> immediateBonus = new ArrayList<>();
		ArrayList<Resource> resources = new ArrayList<>();
		
		Building developmentCard = new Building(name, 1, resources, 2, immediateBonus, bonus);
		
		personalBoard.putDevelopmentCard(developmentCard);
		
		assertEquals(developmentCard, personalBoard.getCardContainer(DevelopmentCardType.building).getDevelopmentCard(name));
	}

}
