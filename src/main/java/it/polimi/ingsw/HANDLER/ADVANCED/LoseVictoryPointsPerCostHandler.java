package it.polimi.ingsw.HANDLER.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.ADVANCED.LoseVictoryPointsPerCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.Venture;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;

//sets the bonus that makes a player lose victory points every time he pay a card cost
public class LoseVictoryPointsPerCostHandler {
	
	public static void handle(Player player){
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null && !playerBonus.isEmpty()){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof LoseVictoryPointsPerCostBonus){
					modifyResources(player, (LoseVictoryPointsPerCostBonus) permanentBonus);
				}
			}
		}
	}

	private static void modifyResources(Player player, LoseVictoryPointsPerCostBonus loseVictoryPointsPerCostBonus) {
		DevelopmentCardType developmentCardType = loseVictoryPointsPerCostBonus.getDevelopmentCardType();
		ArrayList<Resource> bonusResources = loseVictoryPointsPerCostBonus.getResources();
		CardContainer cardContainer = player.getPersonalBoard().getCardContainer(developmentCardType);
		int amountOfCosts = calculateCost(cardContainer, bonusResources, developmentCardType);
		player.getPersonalBoard().getResource(ResourceType.victoryPoints).addAmount(-amountOfCosts);
	}

	private static int calculateCost(CardContainer cardContainer, ArrayList<Resource> bonusResources,
			DevelopmentCardType developmentCardType) {
		if (developmentCardType.equals(DevelopmentCardType.territory))
		return 0;
		else {
			ArrayList<DevelopmentCard> cards = cardContainer.getDevelopmentCards();
			int amountOfCosts = 0;
			ArrayList<Resource> cardCost = new ArrayList<>();
			if (developmentCardType.equals(DevelopmentCardType.building)){
				for (DevelopmentCard developmentCard : cards) {
					cardCost.addAll(((Building) developmentCard).getCosts());
				}
			}
			else if (developmentCardType.equals(DevelopmentCardType.character)){
				for (DevelopmentCard developmentCard : cards) {
					Coins cost = ((Character) developmentCard).getCost(); 
					cardCost.add(cost);
				}
			}
			else if (developmentCardType.equals(DevelopmentCardType.venture)){
				for (DevelopmentCard developmentCard : cards) {
					MilitaryPoints militaryPoints = ((Venture) developmentCard).getAlternativeCost();
					ArrayList<Resource> cost = ((Venture) developmentCard).getCost();
					if (cost != null)
						cardCost.addAll(cost);
					if (militaryPoints != null)
						cardCost.add(militaryPoints);
				}
			}
			for (Resource resource : bonusResources) {
				Resource cardResource = findCardResource(cardCost, resource);
				if (cardResource != null){
					amountOfCosts = amountOfCosts + (cardResource.getAmount()/resource.getAmount());
				}
			}
			return amountOfCosts;
		}
	}

	private static Resource findCardResource(ArrayList<Resource> cardCost, Resource resource) {
		for (Resource cardResource : cardCost) {
			if (resource.getResourceType().equals(cardResource.getResourceType()))
				return cardResource;
		}
		return null;
	}

}
