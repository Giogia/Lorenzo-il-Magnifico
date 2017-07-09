package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.CardType;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

//abstract class for every bonus that modify the cost of a development card
public abstract class CardCostBonus extends PermanentBonus {
	protected DevelopmentCardType cardType;
	protected ArrayList<Resource> resources;
	private String subsubtype;
	
	public CardCostBonus(DevelopmentCardType cardType, ArrayList<Resource> resources, String type){
		super("CardCostBonus");
		this.cardType = cardType;
		this.resources = new ArrayList<>();
		this.resources.addAll(resources);
		subsubtype = type;
	}

	public String getSubsubtype() {
		return subsubtype;
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
	
	public String getDescription(){
		StringBuilder description = new StringBuilder();
		for(Resource resource : resources){
			description.append(resource.getDescription() +"\n");
		}
		return description.toString();
	}
}
