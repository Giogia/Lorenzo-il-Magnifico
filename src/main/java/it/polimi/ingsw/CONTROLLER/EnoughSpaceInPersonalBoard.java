package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.FamilyMember;

public class EnoughSpaceInPersonalBoard implements Controller{
	
	public static boolean check(FamilyMember familyMember, DevelopmentCard developmentCard) throws Exception{
		DevelopmentCardType developmentCardType = developmentCard.developmentCardType;
		ArrayList<CardContainer> containers = familyMember.getPlayer().getPersonalBoard().getCardContainers();
		for (CardContainer containerCard : containers) {
			if (containerCard.getType().equals(developmentCardType)) {
				if(containerCard.getDevelopmentCards().size() < 6){
					return true;
				}
				throw new Exception("The Personal board is full");
			}
		}
		throw new Exception("ERROR - development card type not found");
	}

}
