package it.polimi.ingsw.HANDLER;

import java.util.ArrayList;
import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.Territory;
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
import it.polimi.ingsw.HANDLER.ADVANCED.ZoneFamilyMemberHandler;
import it.polimi.ingsw.RESOURCE.Resource;


public abstract class HarvestProductionAreaHandler {

	
	public static boolean abstractHandle(FamilyMember familyMember, ActionZone zone, Position position) throws MyException{
		if (position.equals(zone.getPosition(0))){
			if(!PositionAlreadyOccupiedController.check(position) &&
				!OccupiedYetBonusController.check(familyMember)){
				return false;
			}
		}
		if(ZoneOccupiedBySameColorController.check(zone, familyMember)){
			ArrayList<Resource> playerResources = cloneResources(familyMember.getPlayer());
			FamilyMember testFamilyMember = new FamilyMember(familyMember.getDice(), familyMember.getPlayer());
			
			ServantsHandler.handle(testFamilyMember, playerResources);
			checkPositionMalus(testFamilyMember, zone, position);
			ZoneFamilyMemberHandler.handle(zone, testFamilyMember);
			if(FamilyMemberValueController.check(testFamilyMember, position)){
				if(CheckBonusTileRequirementController.check(testFamilyMember, zone)){
					testFamilyMember.getPlayer().setFamilyMemberPosition(testFamilyMember, position);
					PassTurnController.lastMove(testFamilyMember.getPlayer());
					copyResource(testFamilyMember.getPlayer(), playerResources);
					getPersonalBonusTileBonus(testFamilyMember, zone);
					//start advanced
					ArrayList<DevelopmentCard> activableCards = getActivableCards(testFamilyMember, zone);
					ArrayList<DevelopmentCard> activatedCards = new ArrayList<>();
					activatedCards = chooseCards(activableCards,testFamilyMember,zone);
					return true;
				}
			}
		}
		return false;
		
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
	
	//advanced
	protected static ArrayList<DevelopmentCard> getActivableCards(FamilyMember familyMember, Zone zone){ //serve per le regole avanzate
	ArrayList<CardContainer> cardContainers= familyMember.getPlayer().getPersonalBoard().getCardContainers();
	ArrayList<DevelopmentCard> activableCards= new ArrayList<>();	
		for(CardContainer cardContainer : cardContainers){// cerca tra i container quello giusto
			if(zone instanceof HarvestArea){
				if(cardContainer.getType().equals(DevelopmentCardType.territory)){// prende il container giusto 
					for(DevelopmentCard card: cardContainer.getDevelopmentCards()){
						Territory territory = (Territory) card;
						if(territory.getActivationConditionHarvest()<=familyMember.getValue()){ //aggiunge quelle solo con valore maggiore
							activableCards.add(territory);
						}
					}
				}
			}
			if(zone instanceof ProductionArea){// prende il container giusto 
				if(cardContainer.getType().equals(DevelopmentCardType.building)){
					for(DevelopmentCard card: cardContainer.getDevelopmentCards()){
						Building building = (Building) card;
						if(building.getActivationConditionProduction()<=familyMember.getValue()){ //aggiunge quelle solo con valore maggiore
							activableCards.add(building);
						}
					}
				}
			} 
		}
		return activableCards;
	}
	
	//advanced
	protected static ArrayList<DevelopmentCard> chooseCards (ArrayList<DevelopmentCard> developmentCards,FamilyMember familyMember, Zone zone){
		ArrayList<DevelopmentCard> chosenCards = new ArrayList<>();
		for(DevelopmentCard card : developmentCards){
			if(zone instanceof HarvestArea){
				Territory territory = (Territory) card;
			}
		}
		return chosenCards;
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
	
	protected static ArrayList<Resource> cloneResources(Player player){
		ArrayList<Resource> playerResources = new ArrayList<>();
		for (Resource resource : player.getPersonalBoard().getResources()) {
			playerResources.add(resource.createClone());
		}
		return playerResources;
	}
	
	
	
	
	
	
	
}