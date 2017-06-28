package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;

public class EnoughSpaceInPersonalBoard implements Controller{
	//check if player can take the development card seeing if he has 5 or less cards of same type 
	public static boolean check(FamilyMember familyMember, DevelopmentCard developmentCard) throws MyException{
		DevelopmentCardType developmentCardType = developmentCard.developmentCardType;
		ArrayList<CardContainer> containers = familyMember.getPlayer().getPersonalBoard().getCardContainers();
		for (CardContainer containerCard : containers) {
			if (containerCard.getType().equals(developmentCardType)) {
				if(containerCard.getDevelopmentCards().size() < 6){
					return true;
				}
				throw new MyException("The Personal board is full");
			}
		}
		throw new MyException("ERROR - development card type not found");
	}

}
