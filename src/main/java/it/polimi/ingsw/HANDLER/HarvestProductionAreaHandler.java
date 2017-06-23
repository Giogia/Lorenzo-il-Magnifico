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
import it.polimi.ingsw.HANDLER.ADVANCED.ZoneFamilyMemberHandler;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.Manager;


public abstract class HarvestProductionAreaHandler {

	public static boolean abstractHandle(FamilyMember familyMember, ActionZone zone, Position position) throws MyException, IOException{
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
					getPersonalBonusTileBonus(testFamilyMember, zone);
					copyResource(testFamilyMember.getPlayer(), playerResources);
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
	
	
	protected static void getPersonalBonusTileBonus(FamilyMember familyMember,Zone zone) throws MyException, IOException{
		ImmediateBonus personalBonusTileBonus = familyMember.getPlayer().getPersonalBoard().getPersonalBonusTile().getImmediateBonus(zone);
		personalBonusTileBonus.getImmediateBonus(familyMember.getPlayer());
	}
	
	
	protected static ArrayList<Resource> cloneResources(Player player){
		ArrayList<Resource> playerResources = new ArrayList<>();
		for (Resource resource : player.getPersonalBoard().getResources()) {
			playerResources.add(resource.createClone());
		}
		return playerResources;
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
	
	//advanced
	protected static ArrayList<DevelopmentCard> getActivableCards(FamilyMember familyMember, Zone zone,ArrayList<Resource> resources){ //serve per le regole avanzate
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
					if(ResourceBonusCardController.check(territory.secondaryEffect, familyMember.getPlayer(),resources))//aggiunge solo quelle con un resource bonus attivabile
						activableCards.add(territory);
				}
			}
			if(zone instanceof ProductionArea){
				Building building = (Building) card;
				if(building.getActivationConditionProduction()<=familyMember.getValue()){//aggiunge quelle solo con valore maggiore
					if(ResourceBonusCardController.check(building.secondaryEffect,familyMember.getPlayer(),resources))
						activableCards.add(building);
				}
			}
		}
		return activableCards;
	}
		
	//advanced
	protected static ArrayList<Bonus> chooseEffects (ArrayList<DevelopmentCard> activableCards,FamilyMember familyMember,ArrayList<Resource> resources) throws IOException, MyException{
		ArrayList<Bonus> chosenEffects = new ArrayList<>();//mappazzone
		do{
			chosenEffects = new ArrayList<>();
			for(DevelopmentCard card : activableCards){
				ArrayList<Bonus> cardChosenEffects = Manager.chooseEffect(familyMember.getPlayer(),card);
				chosenEffects.addAll(cardChosenEffects);
			}
		}while(!ResourceBonusCardController.check(chosenEffects,familyMember.getPlayer(),resources));//controlla che tutti gli effetti funzionino insieme
		return chosenEffects;
	}

	
	
	
	
	
}