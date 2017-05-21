package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.ContainerCard;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.FamilyMember;

public class EnoughSpaceInPersonalBoard implements Controller{
	
	public static boolean check(FamilyMember familyMember, DevelopmentCard developmentCard){
		DevelopmentCardType developmentCardType = developmentCard.developmentCardType;
		ArrayList<ContainerCard> containers = familyMember.getPlayer().getPersonalBoard().getContainerCards();
		for (ContainerCard containerCard : containers) {
			if (containerCard.getType().equals(developmentCardType)) {
				if(containerCard.getDevelopmentCards().size() < 6) {
					return true;
				}
			}
		}
		return false;
	}

}
