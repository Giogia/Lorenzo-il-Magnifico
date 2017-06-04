package it.polimi.ingsw.HANDLER;

import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.manager.Manager;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.Venture;
import it.polimi.ingsw.CONTROLLER.EnoughSpaceInPersonalBoard;
import it.polimi.ingsw.CONTROLLER.FamilyMemberValueController;
import it.polimi.ingsw.CONTROLLER.IsThereBonusController;
import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.CONTROLLER.PositionWithoutDevelopmentCardController;
import it.polimi.ingsw.CONTROLLER.ZoneAlreadyOccupiedController;
import it.polimi.ingsw.CONTROLLER.ZoneOccupiedBySameColorController;
import it.polimi.ingsw.CARD.Character;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ResourceBonus;

public class TowerHandler {

	public static boolean handle(FamilyMember familyMember, Tower zone, TowerFloor towerFloor) throws Exception{
		if (PositionWithoutDevelopmentCardController.check(towerFloor)){
			if (ZoneOccupiedBySameColorController.check(zone, familyMember)){
				if (EnoughSpaceInPersonalBoard.check(familyMember, towerFloor.getDevelopmentCard())){
					ArrayList<Resource> playerResources = new ArrayList<>();
					for (Resource resource : familyMember.getPlayer().getPersonalBoard().getResources()) {
						playerResources.add(resource.clone());
					}
					FamilyMember testFamilyMember = new FamilyMember(familyMember.getDice(), familyMember.getPlayer());
					ServantsHandler.handle(testFamilyMember, playerResources);
					if (FamilyMemberValueController.check(testFamilyMember, towerFloor)){
						if (IsThereBonusController.check(towerFloor)){
							ArrayList<ImmediateBonus> boardBonus = towerFloor.getBoardBonus();
							ArrayList<Resource> bonusResources = new ArrayList<>();
							for (ImmediateBonus immediateBonus : boardBonus) {
								ResourceBonus resourceBonus = (ResourceBonus) immediateBonus;
								bonusResources = resourceBonus.getResources();
							}
							add(playerResources, bonusResources);
						}
						if (!ZoneAlreadyOccupiedController.check(zone)){
							addOccupiedCost(playerResources);
						}
						if (checkZone(testFamilyMember, playerResources, towerFloor)){
							copyResource(testFamilyMember.getPlayer(), playerResources);
							testFamilyMember.getPlayer().setFamilyMemberPosition(testFamilyMember, towerFloor);
							PassTurnController.lastMove(testFamilyMember.getPlayer());
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private static boolean checkZone(FamilyMember familyMember, ArrayList<Resource> playerResources, TowerFloor towerFloor) throws Exception{
		if (towerFloor.getDevelopmentCard().developmentCardType.equals(DevelopmentCardType.territory)){
			if (!checkTerritories(familyMember)){
				return false;
			}
		}
		else if (towerFloor.getDevelopmentCard().developmentCardType.equals(DevelopmentCardType.venture)) {
			if (!checkVentures(familyMember, playerResources, towerFloor)){
				return false;
			}
		}
		else if (towerFloor.getDevelopmentCard().developmentCardType.equals(DevelopmentCardType.character)) {
			if (!checkCharacters(familyMember, playerResources, towerFloor)){
				return false;
			}
		}
		else if (towerFloor.getDevelopmentCard().developmentCardType.equals(DevelopmentCardType.building)) {
			if (!checkBuildings(familyMember, playerResources, towerFloor)){
				return false;
			}
		}
		return true;
	}

	private static boolean checkBuildings(FamilyMember familyMember, ArrayList<Resource> playerResources,
			TowerFloor towerFloor) throws Exception {
		Building buildingCard = (Building) towerFloor.getDevelopmentCard();
		ArrayList<Resource> cost = buildingCard.costs;
		if (FakeFamilyMemberHandler.getBoolean()){
			subOrZero(cost, FakeFamilyMemberHandler.getCost());
		}
		add(playerResources, neg(cost));
		if (checkResources(playerResources)){
			return true;
		}
		else{
			throw new Exception("You don't have enough resources!");
		}
	}


	private static boolean checkCharacters(FamilyMember familyMember, ArrayList<Resource> playerResources,
			TowerFloor towerFloor) throws Exception{
		Character characterCard = (Character) towerFloor.getDevelopmentCard();
		ArrayList<Resource> cost = new ArrayList<>();
		cost.add(characterCard.cost);
		if (FakeFamilyMemberHandler.getBoolean()){
			subOrZero(cost, FakeFamilyMemberHandler.getCost());
		}
		add(playerResources, neg(cost));
		if (checkResources(playerResources)){
			return true;
		}
		throw new Exception("You don't have enough resources!");
	}


	

	private static boolean checkVentures(FamilyMember familyMember, ArrayList<Resource> playerResources, TowerFloor towerFloor) throws Exception{
		Venture ventureCard = (Venture) towerFloor.getDevelopmentCard();
		ArrayList<Resource> cost = ventureCard.cost;
		//initialize the alternative cost to prevent null pointer exception
		MilitaryPoints militaryPoints = ventureCard.alternativeCost;
		ArrayList<Resource> alternativeCost = new ArrayList<>();
		alternativeCost.add(militaryPoints);
		int requirement = ventureCard.militaryPointRequirement;
		ArrayList<Resource> playerResources1 = playerResources;
		MilitaryPoints playerMilitaryPoints = (MilitaryPoints) familyMember.getPlayer().getPersonalBoard().getResource(ResourceType.militaryPoints);
		ArrayList<Resource> playerResources2 = playerResources;
		if (FakeFamilyMemberHandler.getBoolean()){
			subOrZero(cost, FakeFamilyMemberHandler.getCost());
			subOrZero(alternativeCost, FakeFamilyMemberHandler.getCost());
		}
		// If cost or alternativeCost are null, negate playerResource, in this way checkResources return false
		try {
			add(playerResources1, neg(cost));
		} catch (NullPointerException e) {
			playerResources1 = neg(playerResources1);
		}
		try {
		add(playerResources2, neg(alternativeCost));
		} catch (NullPointerException e) {
			playerResources2 = neg(playerResources2);
		}
		//this if else is in this way because if the cost of the ventureCard is military point, this cost is in requirement
		if (checkResources(playerResources1)){
			if (playerMilitaryPoints.getAmount() >= requirement){
				if (checkResources(playerResources2)){
					ArrayList<Resource> chooseCost = Manager.askForAlternativeCost(familyMember.getPlayer(), cost, alternativeCost);
					add(playerResources, neg(chooseCost));
					return true;
				}
			add(playerResources, neg(cost));
			return true;
			}
		}
		else if(playerMilitaryPoints.getAmount() >= requirement){
			if (checkResources(playerResources2))
				add(playerResources, neg(alternativeCost));
				return true;
		}
		throw new Exception("You don't have enough resources!");
	}


	private static boolean checkTerritories(FamilyMember familyMember) throws Exception{
		ArrayList<CardContainer> cardContainers = familyMember.getPlayer().getPersonalBoard().getCardContainers();
		for (CardContainer cardContainer : cardContainers) {
			if (cardContainer.getType().equals(DevelopmentCardType.territory)){
				int numberOfCards = cardContainer.getDevelopmentCards().size();
				int[] militaryRequirement = Game.getData().getMilitaryRequirement();
				MilitaryPoints playerMilitaryPoints = (MilitaryPoints) familyMember.getPlayer().getPersonalBoard().getResource(ResourceType.militaryPoints);
				int requirementAmount = militaryRequirement[numberOfCards];
				if (playerMilitaryPoints.getAmount() - requirementAmount < 0){
					throw new Exception("You don't have enough military points!");
				}
			}
		}
		return true;
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


	private static void addOccupiedCost(ArrayList<Resource> playerResources) {
		Coins occupiedCost = new Coins(3, 1);
		ArrayList<Resource> bonusResources = new ArrayList<>();
		bonusResources.add(occupiedCost);
		add(playerResources, bonusResources);
	}


	private static void add(ArrayList<Resource> playerResources, ArrayList<Resource> bonusResources) {
		for (Resource resource : bonusResources) {
			for (Resource playerResource : playerResources) {
				if (resource.getClass().equals(playerResource.getClass())){
					playerResource.addAmount(resource.getAmount());
				}
			}
		}
	}
	
	private static ArrayList<Resource> neg(ArrayList<Resource> resources){
		ArrayList<Resource> negResources = new ArrayList<>();
		for (Resource resource : resources) {
			negResources.add(resource.clone());
		}
		for (Resource negResource : negResources) {
			int amount = -negResource.getAmount();
			negResource.setAmount(amount);
		}
		return negResources;
	}
	
	
	
	private static boolean checkResources(ArrayList<Resource> resources){
		for (Resource resource : resources) {
			if (resource.getAmount() < 0) return false;
		}
		return true;
	}
	
	private static void subOrZero(ArrayList<Resource> cost, ArrayList<Resource> cost2) {
		add(cost, neg(cost2));
		for (Resource resource : cost) {
			if (resource.getAmount() < 0){
				resource.setAmount(0);
			}
		}
	}
}
