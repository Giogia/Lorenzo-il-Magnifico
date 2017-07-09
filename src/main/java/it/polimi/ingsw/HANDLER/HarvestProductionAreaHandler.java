package it.polimi.ingsw.HANDLER;

import java.io.IOException;
import java.util.ArrayList;
import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.CONTROLLER.CheckBonusTileRequirementController;
import it.polimi.ingsw.CONTROLLER.FamilyMemberValueController;
import it.polimi.ingsw.CONTROLLER.OccupiedYetBonusController;
import it.polimi.ingsw.CONTROLLER.PositionAlreadyOccupiedController;
import it.polimi.ingsw.CONTROLLER.ResourceBonusCardController;
import it.polimi.ingsw.CONTROLLER.ZoneOccupiedBySameColorController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.HANDLER.ADVANCED.ZoneFamilyMemberHandler;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.Manager;

//set of instructions to place a family member on harvest or production areas
//lets choose and activate the cards' owned by a player
public abstract class HarvestProductionAreaHandler {

	public static boolean abstractHandle(FamilyMember familyMember, ActionZone zone, Position position) throws MyException, IOException, TimeExpiredException{
		if (position.equals(zone.getPositions()[0]) && !OccupiedYetBonusController.check(familyMember)){
			if (!PositionAlreadyOccupiedController.check(position)){
				return false;
			}
		}
		if(ZoneOccupiedBySameColorController.check(zone, familyMember)){
			ArrayList<Resource> playerResources = cloneResources(familyMember.getPlayer());
			FamilyMember testFamilyMember = new FamilyMember(familyMember.getDice(), familyMember.getPlayer());
			testFamilyMember.setValue(familyMember.getValue());
			ServantsHandler.handle(testFamilyMember, playerResources);
			checkPositionMalus(testFamilyMember, zone, position);
			ZoneFamilyMemberHandler.handle(zone, testFamilyMember);
			if(FamilyMemberValueController.check(testFamilyMember, position)){
				if(CheckBonusTileRequirementController.check(testFamilyMember, zone)){
					testFamilyMember.getPlayer().setFamilyMemberPosition(testFamilyMember, position);
					familyMember.getPlayer().getBoard().getPassTurnController().lastMove(testFamilyMember.getPlayer());
					copyResource(testFamilyMember.getPlayer(), playerResources);
					getPersonalBonusTileBonus(testFamilyMember, zone);
					//start advanced
					ArrayList<DevelopmentCard> activableCards = getActivableCards(testFamilyMember, zone,playerResources);
					if(activableCards.isEmpty())
						return true;
					ArrayList<Bonus> chosenEffects = chooseEffects(activableCards,familyMember,playerResources);
					for(Bonus chosenBonus : chosenEffects){
						ImmediateBonus bonus = (ImmediateBonus) chosenBonus;
						bonus.getImmediateBonus(familyMember.getPlayer());
					}
				}
				return true;
			}
		}
		return false;
	}
	

	private static void checkPositionMalus(FamilyMember familyMember, Zone zone, Position position) {
		Position[] zonePosition = zone.getPositions();
		int[] positionMalus;
		if (zone instanceof HarvestArea){
			positionMalus = familyMember.getPlayer().getBoard().getGame().getData().getHarvestAreaPositionBonus();
		}
		else {
			positionMalus = familyMember.getPlayer().getBoard().getGame().getData().getProductionAreaPositionBonus();
		}
		if (position.equals(zonePosition[0])){
			familyMember.addValue(positionMalus[0]);
		}
		else{
			familyMember.addValue(positionMalus[1]);
		}
	}
	
	
	protected static void getPersonalBonusTileBonus(FamilyMember familyMember,Zone zone) throws MyException, IOException, TimeExpiredException{
		ImmediateBonus personalBonusTileBonus = familyMember.getPlayer().getPersonalBoard().getPersonalBonusTile().getImmediateBonus(zone);
		personalBonusTileBonus.getImmediateBonus(familyMember.getPlayer());
	}
	
	//create a copy of player's resource
	protected static ArrayList<Resource> cloneResources(Player player){
		ArrayList<Resource> playerResources = new ArrayList<>();
		for (Resource resource : player.getPersonalBoard().getResources()) {
			playerResources.add(resource.createClone());
		}
		return playerResources;
	}
	
	// set an arraylist of resources on a player's resources
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
	
	//advanced
	//find the cards that can be activated
	protected static ArrayList<DevelopmentCard> getActivableCards(FamilyMember familyMember, Zone zone,ArrayList<Resource> resources){
		ArrayList<DevelopmentCard> activableCards= new ArrayList<>();	
		DevelopmentCardType developmentCardType = null;
		if(zone instanceof HarvestArea)			
			developmentCardType = DevelopmentCardType.territory;
		if(zone instanceof ProductionArea)
			developmentCardType = DevelopmentCardType.building;
		ArrayList<DevelopmentCard> cards = familyMember.getPlayer().getPersonalBoard().getCardContainer(developmentCardType).getDevelopmentCards();
		for(DevelopmentCard card: cards){
			if(zone instanceof HarvestArea){
				Territory territory = (Territory) card;
				if(territory.getActivationConditionHarvest()<=familyMember.getValue()){
					if(ResourceBonusCardController.check(territory.secondaryEffect, familyMember.getPlayer(),resources))//add only the ones with activable resource bonus
						activableCards.add(territory);
				}
			}
			if(zone instanceof ProductionArea){
				Building building = (Building) card;
				if(building.getActivationConditionProduction()<=familyMember.getValue()){//add only the ones with greatest values
					if(ResourceBonusCardController.check(building.secondaryEffect,familyMember.getPlayer(),resources))
						activableCards.add(building);
				}
			}
		}
		return activableCards;
	}
		
	//advanced
	//ask the user which effect should be activated
	protected static ArrayList<Bonus> chooseEffects (ArrayList<DevelopmentCard> activableCards,FamilyMember familyMember,ArrayList<Resource> resources) throws IOException, MyException, TimeExpiredException{
		ArrayList<Bonus> chosenEffects = new ArrayList<>();//the set of all chosen effects
		do{
			chosenEffects.clear();
			for(DevelopmentCard card : activableCards){
				ArrayList<Bonus> cardChosenEffects = Manager.chooseEffect(familyMember.getPlayer(),card);
				chosenEffects.addAll(cardChosenEffects);
			}
		}while(!ResourceBonusCardController.check(chosenEffects,familyMember.getPlayer(),resources));//check if it's possible to activate all the effects together
		return chosenEffects;
	}

	
	
	
	
	
}