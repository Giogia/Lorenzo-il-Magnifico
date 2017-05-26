package it.polimi.ingsw.HANDLER;

import java.util.ArrayList;
import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CONTROLLER.*;
import it.polimi.ingsw.GC_15.FamilyMember;


public abstract class HarvestProductionAreaHandler {

	
	public static boolean abstractHandle(FamilyMember familyMember, Zone zone, Position position){
		if(!PositionAlreadyOccupiedController.check(position) &&
			!OccupiedYetBonusController.check(familyMember)){
			return false;
		}
		if(ZoneOccupiedBySameColorController.check(zone, familyMember)){
			if(FamilyMemberValueController.check(familyMember, position)){
				if(CheckBonusTileRequirementController.check(familyMember, zone)){
					familyMember.getPlayer().setFamilyMemberPosition(familyMember, position);
					getPersonalBonusTileBonus(familyMember, zone);
					return true;
				}
			}
		}
		return false;
		/*ArrayList<DevelopmentCard> cardsToActivate = MvcController.chooseCards(getCards(familyMember, zone)); //chiede le carte al giocatore
		while(ActivateCardsController(cardsToActivate, familyMember.getPlayer())== false){ //le richiede finche non puo' attivarle
			cardsToActivate = MvcController.chooseCards(getCards(familyMember, zone));
		} */
	}
	
	protected static ArrayList<DevelopmentCard> getCards(FamilyMember familyMember, Zone zone){ //serve per le regole avanzate
	ArrayList<CardContainer> cardContainers= familyMember.getPlayer().getPersonalBoard().getCardContainers();
		for(CardContainer cardcontainer : cardContainers){
			if(zone instanceof HarvestArea){
				if(cardcontainer.type.equals(DevelopmentCardType.TERRITORY)){
					return cardcontainer.getDevelopmentCards();
				}
			}
			if(zone instanceof ProductionArea){
				if(cardcontainer.type.equals(DevelopmentCardType.BUILDING)){
					return cardcontainer.getDevelopmentCards();
				}
			}
		}
		return null;
	}
	
	protected static void getPersonalBonusTileBonus(FamilyMember familyMember,Zone zone){
		ImmediateBonus personalBonusTileBonus = familyMember.getPlayer().getPersonalBoard().getPersonalBonusTile().getImmediateBonus(zone);
		personalBonusTileBonus.getImmediateBonus(familyMember.getPlayer());
	}
	
	
	
	
}