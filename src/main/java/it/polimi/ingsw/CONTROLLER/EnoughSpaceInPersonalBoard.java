package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.FamilyMember;

public class EnoughSpaceInPersonalBoard implements Controller{
	
	public static boolean check(FamilyMember familyMember, DevelopmentCard developmentCard){
		DevelopmentCardType developmentCardType = developmentCard.developmentCardType;
		ArrayList<CardContainer> containers = familyMember.getPlayer().getPersonalBoard().getCardContainers();
		for (CardContainer containerCard : containers) {
			if (containerCard.getType().equals(developmentCardType)) {
				return (containerCard.getDevelopmentCards().size() < 6);
			}
		}
		return false;
	}

}
