package it.polimi.ingsw.CONTROLLER;

import java.util.HashMap;
import java.util.Map.Entry;

import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;

public class LeaderCardCardActivationConditionController {
	
	//check if player can activate a leader card 
	//the activation condition controlled is the number of development card of same type
	public static boolean check(Player player, HashMap<DevelopmentCardType, Integer> cardActivationCondition)  throws MyException{
		for(Entry<DevelopmentCardType, Integer> entry : cardActivationCondition.entrySet()){
			CardContainer cardContainer = player.getPersonalBoard().getCardContainer(entry.getKey());
			if(cardContainer.getDevelopmentCards().size()<entry.getValue())
				throw new MyException("You don't have enough "+ entry.getKey() +" cards \n");
		}
		return true;
	}
}
