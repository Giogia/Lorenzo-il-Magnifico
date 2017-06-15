package it.polimi.ingsw.HANDLER;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
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
import it.polimi.ingsw.CONTROLLER.ResourceBonusCardController;
import it.polimi.ingsw.CONTROLLER.ZoneOccupiedBySameColorController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.ADVANCED.ZoneFamilyMemberHandler;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.Manager;


public abstract class HarvestProductionAreaHandler {

	public static boolean abstractHandle(FamilyMember familyMember, ActionZone zone, Position position) throws MyException, IOException{
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
					familyMember.getPlayer().getBoard().getPassTurnController().lastMove(testFamilyMember.getPlayer());
					copyResource(testFamilyMember.getPlayer(), playerResources);
					getPersonalBonusTileBonus(testFamilyMember, zone);
					//start advanced
					ArrayList<DevelopmentCard> activableCards = getActivableCards(testFamilyMember, zone,playerResources);
					ArrayList<Bonus> chosenEffects = chooseEffects(activableCards,familyMember,playerResources);
					if(!chosenEffects.isEmpty()){
						for(Bonus chosenBonus : chosenEffects){
							ImmediateBonus bonus = (ImmediateBonus) chosenBonus;
							bonus.getImmediateBonus(familyMember.getPlayer());
						}
					}
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
	
	//advanced
	protected static ArrayList<DevelopmentCard> getActivableCards(FamilyMember familyMember, Zone zone,ArrayList<Resource> resources){ //serve per le regole avanzate
	ArrayList<CardContainer> cardContainers= familyMember.getPlayer().getPersonalBoard().getCardContainers();
	ArrayList<DevelopmentCard> activableCards= new ArrayList<>();	
		for(CardContainer cardContainer : cardContainers){// cerca tra i container quello giusto
			if(zone instanceof HarvestArea){
				if(cardContainer.getType().equals(DevelopmentCardType.territory)){// prende il container giusto 
					for(DevelopmentCard card: cardContainer.getDevelopmentCards()){
						Territory territory = (Territory) card;
						if(territory.getActivationConditionHarvest()<=familyMember.getValue()){//aggiunge quelle solo con valore maggiore
							if(ResourceBonusCardController.check(territory.secondaryEffect, familyMember.getPlayer(),resources))//aggiunge solo quelle con un resource bonus attivabile
								activableCards.add(territory);
						}
					}
				}
			}
			if(zone instanceof ProductionArea){// prende il container giusto 
				if(cardContainer.getType().equals(DevelopmentCardType.building)){
					for(DevelopmentCard card: cardContainer.getDevelopmentCards()){
						Building building = (Building) card;
						if(building.getActivationConditionProduction()<=familyMember.getValue()){//aggiunge quelle solo con valore maggiore
							if(ResourceBonusCardController.check(building.secondaryEffect,familyMember.getPlayer(),resources) || 
								ResourceBonusCardController.check(building.tertiaryEffect,familyMember.getPlayer(),resources))//aggiunge solo quelle con almeno un resource bonus attivabile
								activableCards.add(building);
						}
					}
				}
			} 
		}
		return activableCards;
	}

	//advanced
	protected static ArrayList<Bonus> chooseEffects (ArrayList<DevelopmentCard> activableCards,FamilyMember familyMember,ArrayList<Resource> resources) throws IOException{

		ArrayList<Bonus> chosenEffects = new ArrayList<>();//mappazzone
		do{
			chosenEffects = new ArrayList<>();
			for(DevelopmentCard card : activableCards){
				ArrayList<Bonus> cardChosenEffects = Manager.chooseEffect(familyMember.getPlayer(),card);
				if(cardChosenEffects!=null)//it's null if the user didn't choose to activate these effects
					chosenEffects.addAll(cardChosenEffects);
			}
		}while(ResourceBonusCardController.check(chosenEffects,familyMember.getPlayer(),resources));//controlla che tutti gli effetti funzionino insieme
		return chosenEffects;
	}



	protected static void getPersonalBonusTileBonus(FamilyMember familyMember,Zone zone) throws MyException, IOException{
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