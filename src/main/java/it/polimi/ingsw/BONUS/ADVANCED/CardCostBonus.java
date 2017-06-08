package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.CardType;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public abstract class CardCostBonus extends PermanentBonus {
	protected DevelopmentCardType cardType;
	protected ArrayList<Resource> resources;
	
	public CardCostBonus(DevelopmentCardType cardType, ArrayList<Resource> resources){
		super("CardCostBonus");
		this.cardType = cardType;
		this.resources = new ArrayList<>();
		this.resources.addAll(resources);
	}

	public DevelopmentCardType getCardType() {
		return cardType;
	}
	
	public ArrayList<Resource> getResources() {
		return resources;
	}
	

	public void addBonus(CardCostBonus cardCostBonus){
		for (Resource bonusResource : cardCostBonus.getResources()) {
			addResourceBonus(bonusResource);
		}
	}

	private void addResourceBonus(Resource bonusResource) {
		for (Resource resource : resources) {
			if (resource.getClass().equals(bonusResource.getClass())){
				resource.addAmount(bonusResource.getAmount());
				return;
			}
		}
		resources.add(bonusResource);
	}
	
}
