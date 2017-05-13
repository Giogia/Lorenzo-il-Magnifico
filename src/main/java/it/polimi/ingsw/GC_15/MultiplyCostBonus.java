package it.polimi.ingsw.GC_15;

import java.util.ArrayList;

public class MultiplyCostBonus implements PermanentBonus{
	private CardType cardType;
	private ArrayList<Resource> resources; 
	
	public MultiplyCostBonus(CardType cardType, ArrayList<Resource> resources) {
		this.cardType = cardType;
		this.resources = new ArrayList<>();
		this.resources.addAll(resources);	
	}

	@Override
	public void getPermanentBonus(Player player) {
		player.addPermanentBonus(this);
	}

}