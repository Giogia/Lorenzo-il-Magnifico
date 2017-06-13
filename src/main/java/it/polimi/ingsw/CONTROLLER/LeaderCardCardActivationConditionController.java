package it.polimi.ingsw.CONTROLLER;

import java.util.HashMap;

import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;

public class LeaderCardCardActivationConditionController {

	
	public static boolean check(Player player, HashMap<DevelopmentCardType, Integer> cardActivationCondition)  throws MyException{
		for(DevelopmentCardType developmentCardType : cardActivationCondition.keySet()){
			CardContainer cardContainer = player.getPersonalBoard().getCardContainer(developmentCardType);
			if(cardContainer.getDevelopmentCards().size()<cardActivationCondition.get(developmentCardType))
				throw new MyException("You don't have enough "+ developmentCardType +" cards \n");
		}
		return true;
	}
}
