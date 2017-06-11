package it.polimi.ingsw.HANDLER.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.AddCardCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.CardCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.MultiplyCardCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class CardCostHandler {
	
	
	public static void handle(ArrayList<Resource> cost, Player player, DevelopmentCardType developmentCardType){
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if(playerBonus!=null && !playerBonus.isEmpty()){
			CardCostBonus[] cardCostBonus = new CardCostBonus[2];
			for (PermanentBonus permanentBonus : playerBonus) {
				cardCostBonus = controlBonus(cardCostBonus, permanentBonus, developmentCardType);
			}
			if(cardCostBonus[0]!=null){
				ArrayList<Resource> addBonus = cardCostBonus[0].getResources();
				for (Resource resource : addBonus) {
					for (Resource costResource : cost) {
						if (resource.getClass().equals(costResource.getClass())){
							addOrZero(costResource, resource);
						}
					}
				}
			} 
			if(cardCostBonus[1]!=null){
				ArrayList<Resource> multBonus = cardCostBonus[0].getResources();
				for (Resource resource : multBonus) {
					for (Resource costResource : cost) {
						if (resource.getClass().equals(costResource.getClass())){
							costResource.multAmount(resource.getAmount());
						}
					}
				}
			}
		}
	}

	private static void addOrZero(Resource costResource, Resource resource) {
		costResource.addAmount(resource.getAmount());
		if (costResource.getAmount() < 0){
			costResource.setAmount(0);
		}
	}

	private static CardCostBonus[] controlBonus(CardCostBonus[] cardCostBonus, PermanentBonus permanentBonus,
			DevelopmentCardType developmentCardType) {
		if (permanentBonus instanceof AddCardCostBonus){
			if (((CardCostBonus) permanentBonus).getCardType().equals(developmentCardType)){
				cardCostBonus[0] = (AddCardCostBonus) permanentBonus;
			}
		}
		if (permanentBonus instanceof MultiplyCardCostBonus){
			if (((CardCostBonus) permanentBonus).getCardType().equals(developmentCardType)){
				cardCostBonus[1] = (MultiplyCardCostBonus) permanentBonus;
			}
		}
		return cardCostBonus;
	}

}
