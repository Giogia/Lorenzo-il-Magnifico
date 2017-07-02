package bonus;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.BONUS.ResourceValueBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentAddResourceBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentMultResourceBonus;
import it.polimi.ingsw.BONUS.ADVANCED.ResourcePerMissedExcommunicationBonus;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.RESOURCE.FaithPoints;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.Servants;

public class PermanentResourceBonusTest {

	@Test
	public void addTest() {
		Player player = new Player(null, Color.RED);
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new FaithPoints(2, 1));
		resources.add(new Servants(7, 1));
		
		PermanentAddResourceBonus permanentAddResourceBonus = new PermanentAddResourceBonus(resources);
		
		assertEquals("PermanentAddResourceBonus", permanentAddResourceBonus.getSubsubtype());
		assertEquals("Add: 2 Faith Points\n7 Servants\n each time you get that resource", permanentAddResourceBonus.getDescription());
	
		permanentAddResourceBonus.getPermanentBonus(player);
	
		assertEquals(permanentAddResourceBonus, player.getPersonalBoard().getPermanentBonus().get(0));
		
		PermanentAddResourceBonus permanentAddResourceBonus2 = permanentAddResourceBonus.createClone();
		
		assertEquals(permanentAddResourceBonus.getResources(), permanentAddResourceBonus2.getResources());
		
		ArrayList<Resource> resources2 = new ArrayList<>();
		resources2.add(new Servants(2, 1));
		resources2.add(new MilitaryPoints(3, 1));
		PermanentAddResourceBonus permanentAddResourceBonus3 = new PermanentAddResourceBonus(resources2);
		
		permanentAddResourceBonus3.getPermanentBonus(player);
		
		PermanentAddResourceBonus playerBonus = (PermanentAddResourceBonus) player.getPersonalBoard().getPermanentBonus().get(0);
		
		assertEquals(2, playerBonus.getResources().get(0).getAmount());
		assertEquals(9, playerBonus.getResources().get(1).getAmount());
		assertEquals(3, playerBonus.getResources().get(2).getAmount());
	}
	
	@Test
	public void valueTest() {
		Player player = new Player(null, Color.RED);
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new FaithPoints(2, 1));
		resources.add(new Servants(7, 1));
		
		ResourceValueBonus resourceValueBonus = new ResourceValueBonus(resources);
		
		assertEquals("ResourceValueBonus", resourceValueBonus.getSubsubtype());
		assertEquals("For each FaithPoints you have to pay 2\nFor each Servants you have to pay 7\n", resourceValueBonus.getDescription());
	
		resourceValueBonus.getPermanentBonus(player);
	
		assertEquals(resourceValueBonus, player.getPersonalBoard().getPermanentBonus().get(0));
		
		ResourceValueBonus resourceValueBonus2= resourceValueBonus.createClone();
		
		assertEquals(resourceValueBonus.getResources(), resourceValueBonus2.getResources());
		
		ArrayList<Resource> resources2 = new ArrayList<>();
		resources2.add(new Servants(2, 1));
		resources2.add(new MilitaryPoints(3, 1));
		ResourceValueBonus resourceValueBonus3 = new ResourceValueBonus(resources2);
		
		resourceValueBonus3.getPermanentBonus(player);
		
		ResourceValueBonus playerBonus = (ResourceValueBonus) player.getPersonalBoard().getPermanentBonus().get(0);
		
		assertEquals(2, playerBonus.getResources().get(0).getAmount());
		assertEquals(7, playerBonus.getResources().get(1).getAmount());
		assertEquals(3, playerBonus.getResources().get(2).getAmount());
	}
	
	
	@Test
	public void multTest() {
		Player player = new Player(null, Color.RED);
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new FaithPoints(2, 1));
		resources.add(new Servants(7, 1));
		
		PermanentMultResourceBonus permanentMultResourceBonus = new PermanentMultResourceBonus(resources);
		
		assertEquals("PermanentMultResourceBonus", permanentMultResourceBonus.getSubsubtype());
		assertEquals("Mult for: 2 Faith Points\n7 Servants\n each time you get that resource", permanentMultResourceBonus.getDescription());
	
		permanentMultResourceBonus.getPermanentBonus(player);
	
		assertEquals(permanentMultResourceBonus, player.getPersonalBoard().getPermanentBonus().get(0));
		
		PermanentMultResourceBonus permanentMultResourceBonus2 = permanentMultResourceBonus.createClone();
		
		assertEquals(permanentMultResourceBonus.getResources(), permanentMultResourceBonus2.getResources());
		
		ArrayList<Resource> resources2 = new ArrayList<>();
		resources2.add(new Servants(2, 1));
		resources2.add(new MilitaryPoints(3, 1));

		PermanentMultResourceBonus permanentMultResourceBonus3 = new PermanentMultResourceBonus(resources2);
		
		permanentMultResourceBonus3.getPermanentBonus(player);
		
		PermanentMultResourceBonus playerBonus = (PermanentMultResourceBonus) player.getPersonalBoard().getPermanentBonus().get(0);
		
		assertEquals(2, playerBonus.getResources().get(0).getAmount());
		assertEquals(14, playerBonus.getResources().get(1).getAmount());
		assertEquals(3, playerBonus.getResources().get(2).getAmount());
	}
	
	@Test
	public void excommunicationTest() {
		Player player = new Player(null, Color.RED);
		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new FaithPoints(2, 1));
		resources.add(new Servants(7, 1));
		
		ResourcePerMissedExcommunicationBonus resourcePerMissedExcommunicationBonus = new ResourcePerMissedExcommunicationBonus(resources);
		
		assertEquals("ResourcePerMissedExcommunicationBonus", resourcePerMissedExcommunicationBonus.getSubsubtype());
		assertEquals("when you miss the excommunication you get: \n2 Faith Points\n7 Servants\n", resourcePerMissedExcommunicationBonus.getDescription());
	
		resourcePerMissedExcommunicationBonus.getPermanentBonus(player);
	
		assertEquals(resourcePerMissedExcommunicationBonus, player.getPersonalBoard().getPermanentBonus().get(0));
		
		ResourcePerMissedExcommunicationBonus resourcePerMissedExcommunicationBonus2 = resourcePerMissedExcommunicationBonus.createClone();
		
		assertEquals(resourcePerMissedExcommunicationBonus.getResources(), resourcePerMissedExcommunicationBonus2.getResources());
		
		ArrayList<Resource> resources2 = new ArrayList<>();
		resources2.add(new Servants(2, 1));
		resources2.add(new MilitaryPoints(3, 1));

		ResourcePerMissedExcommunicationBonus resourcePerMissedExcommunicationBonus3 = new ResourcePerMissedExcommunicationBonus(resources2);
		
		resourcePerMissedExcommunicationBonus3.getPermanentBonus(player);
		
		ResourcePerMissedExcommunicationBonus playerBonus = (ResourcePerMissedExcommunicationBonus) player.getPersonalBoard().getPermanentBonus().get(0);
		
		assertEquals(2, playerBonus.getResources().get(0).getAmount());
		assertEquals(9, playerBonus.getResources().get(1).getAmount());
		assertEquals(3, playerBonus.getResources().get(2).getAmount());
	}

}
