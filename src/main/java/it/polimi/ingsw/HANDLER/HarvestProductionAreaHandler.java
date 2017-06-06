package it.polimi.ingsw.HANDLER;

import java.util.ArrayList;
import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CONTROLLER.CheckBonusTileRequirementController;
import it.polimi.ingsw.CONTROLLER.FamilyMemberValueController;
import it.polimi.ingsw.CONTROLLER.OccupiedYetBonusController;
import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.CONTROLLER.PositionAlreadyOccupiedController;
import it.polimi.ingsw.CONTROLLER.ZoneOccupiedBySameColorController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;


public abstract class HarvestProductionAreaHandler {

	
	public static boolean abstractHandle(FamilyMember familyMember, Zone zone, Position position) throws MyException{
		if (position.equals(zone.getPosition(0))){
			if(!PositionAlreadyOccupiedController.check(position) &&
				!OccupiedYetBonusController.check(familyMember)){
				return false;
			}
		}
		if(ZoneOccupiedBySameColorController.check(zone, familyMember)){
			ArrayList<Resource> playerResources = new ArrayList<>();
			for (Resource resource : familyMember.getPlayer().getPersonalBoard().getResources()) {
				playerResources.add(resource.clone());
			}
			FamilyMember testFamilyMember = new FamilyMember(familyMember.getDice(), familyMember.getPlayer());
			ServantsHandler.handle(testFamilyMember, playerResources);
			checkPositionMalus(testFamilyMember, zone, position);
			if(FamilyMemberValueController.check(testFamilyMember, position)){
				if(CheckBonusTileRequirementController.check(testFamilyMember, zone)){
					testFamilyMember.getPlayer().setFamilyMemberPosition(testFamilyMember, position);
					PassTurnController.lastMove(testFamilyMember.getPlayer());
					copyResource(testFamilyMember.getPlayer(), playerResources);
					getPersonalBonusTileBonus(testFamilyMember, zone);
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
	

	private static void checkPositionMalus(FamilyMember familyMember, Zone zone, Position position) {
		Position[] zonePosition = zone.getPositions();
		int[] positionMalus;
		if (zone instanceof HarvestArea){
			positionMalus = Game.getData().getHarvestAreaPositionBonus();
		}
		else {
			positionMalus = Game.getData().getProductionAreaPositionBonus();
		}
		if (position.equals(zonePosition[0])){
			familyMember.addValue(positionMalus[0]);
		}
		else{
			familyMember.addValue(positionMalus[1]);
		}
	}
	

	protected static ArrayList<DevelopmentCard> getCards(FamilyMember familyMember, Zone zone){ //serve per le regole avanzate
	ArrayList<CardContainer> cardContainers= familyMember.getPlayer().getPersonalBoard().getCardContainers();
		for(CardContainer cardcontainer : cardContainers){
			if(zone instanceof HarvestArea){
				if(cardcontainer.getType().equals(DevelopmentCardType.territory)){
					return cardcontainer.getDevelopmentCards();
				}
			}
			if(zone instanceof ProductionArea){
				if(cardcontainer.getType().equals(DevelopmentCardType.building)){
					return cardcontainer.getDevelopmentCards();
				}
			}
		}
		return null;
	}
	
	protected static void getPersonalBonusTileBonus(FamilyMember familyMember,Zone zone) throws MyException{
		ImmediateBonus personalBonusTileBonus = familyMember.getPlayer().getPersonalBoard().getPersonalBonusTile().getImmediateBonus(zone);
		personalBonusTileBonus.getImmediateBonus(familyMember.getPlayer());
	}
	
	private static void copyResource(Player player, ArrayList<Resource> copiedResources) {
		ArrayList<Resource> playerResources = player.getPersonalBoard().getResources();
		for (Resource playerResource : playerResources) {
			for (Resource copiedResource : copiedResources) {
				if (copiedResource.getResourceType().equals(playerResource.getResourceType())){
					playerResource.setAmount(copiedResource.getAmount());
				}
			}
		}
	}
	
	
}