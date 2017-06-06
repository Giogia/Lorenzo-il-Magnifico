package it.polimi.ingsw.HANDLER.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.CardCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class CardCostHandler {
	
	public static void handle(ArrayList<Resource> cost, Player player, DevelopmentCardType developmentCardType){
		try{
			ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
			CardCostBonus cardCostBonus = null;
			for (PermanentBonus permanentBonus : playerBonus) {
				cardCostBonus = controlBonus(cardCostBonus, permanentBonus, developmentCardType);
			}
			ArrayList<Resource> bonus = cardCostBonus.getResources();
			for (Resource resource : bonus) {
				for (Resource costResource : cost) {
					if (resource.getClass().equals(costResource.getClass())){
						addOrZero(costResource, resource);
					}
				}
			}
		} catch (Exception e){
			
		}
	}

	private static void addOrZero(Resource costResource, Resource resource) {
		costResource.addAmount(resource.getAmount());
		if (costResource.getAmount() < 0){
			costResource.setAmount(0);
		}
	}

	private static CardCostBonus controlBonus(CardCostBonus cardCostBonus, PermanentBonus permanentBonus,
			DevelopmentCardType developmentCardType) {
		if (permanentBonus instanceof CardCostBonus){
			if (((CardCostBonus) permanentBonus).getCardType().equals(developmentCardType)){
				cardCostBonus = (CardCostBonus) permanentBonus;
			}
		}
		return cardCostBonus;
	}

}
