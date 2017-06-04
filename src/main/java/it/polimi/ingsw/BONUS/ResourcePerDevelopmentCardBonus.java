package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class ResourcePerDevelopmentCardBonus extends AddResourceBonus {
	private DevelopmentCardType developmentCardType;
	
	public ResourcePerDevelopmentCardBonus(ArrayList<Resource> resources, DevelopmentCardType developmentCardType) {
		super(resources);
		this.developmentCardType = developmentCardType;
	}
	
	@Override
	public void getImmediateBonus(Player player) {
		CardContainer cardContainer = player.getPersonalBoard().getCardContainer(developmentCardType);
		int size = cardContainer.getDevelopmentCards().size();
		for (Resource resource : resources) {
			resource.multAmount(size);
		}
		super.getImmediateBonus(player);
	}
	
	@Override
	public String getDescription() {
		String description = "for Each " + developmentCardType.name() + " card you receive: \n";
		description = description + super.getDescription();
		return description;
	}
	
	@Override
	public ResourceBonus clone() {
		ResourcePerDevelopmentCardBonus newBonus = new ResourcePerDevelopmentCardBonus(this.resources, this.developmentCardType);
		return newBonus;
	}
}
