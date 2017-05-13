package it.polimi.ingsw.BONUS;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.CARD.CardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class AddCostBonus implements PermanentBonus{
	private CardType cardType;
	private ArrayList<Resource> resources; 
	
	public AddCostBonus(CardType cardType, ArrayList<Resource> resources) {
		this.cardType = cardType;
		this.resources = new ArrayList<>();
		this.resources.addAll(resources);	
	}

	@Override
	public void getPermanentBonus(Player player) {
		player.addPermanentBonus(this);
	}

}
