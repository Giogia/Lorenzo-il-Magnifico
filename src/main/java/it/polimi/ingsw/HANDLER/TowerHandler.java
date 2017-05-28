package it.polimi.ingsw.HANDLER;

import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.HANDLER.GAME.Manager;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.Venture;
import it.polimi.ingsw.CARD.Character;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.BONUS.ADVANCED.OccupiedTowerCostBonus;
import it.polimi.ingsw.CONTROLLER.*;

public class TowerHandler {

	public static boolean handle(FamilyMember familyMember, Tower zone, TowerFloor towerFloor) {
		if (PositionAlreadyOccupiedController.check(towerFloor)){
			if (ZoneOccupiedBySameColorController.check(zone, familyMember)){
				if (EnoughSpaceInPersonalBoard.check(familyMember, towerFloor.getDevelopmentCard())){
					if (FamilyMemberValueController.check(familyMember, towerFloor)){
						ArrayList<Resource> playerResources = familyMember.getPlayer().getPersonalBoard().getResources();
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
						if (checkZone(familyMember, playerResources, towerFloor)){
							copyResource(familyMember.getPlayer(), playerResources);
							familyMember.getPlayer().setFamilyMemberPosition(familyMember, towerFloor);
							PassTurnController.lastMove(familyMember.getPlayer());
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private static boolean checkZone(FamilyMember familyMember, ArrayList<Resource> playerResources, TowerFloor towerFloor){
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
			TowerFloor towerFloor) {
		Building buildingCard = (Building) towerFloor.getDevelopmentCard();
		ArrayList<Resource> cost = buildingCard.costs;
		add(playerResources, neg(cost));
		if (checkResources(playerResources)){
			return true;
		}
		else{
			return false;
		}
	}


	private static boolean checkCharacters(FamilyMember familyMember, ArrayList<Resource> playerResources,
			TowerFloor towerFloor) {
		Character characterCard = (Character) towerFloor.getDevelopmentCard();
		ArrayList<Resource> cost = new ArrayList<>();
		cost.add(characterCard.cost);
		add(playerResources, neg(cost));
		if (checkResources(playerResources)){
			return true;
		}
		return false;
	}


	private static boolean checkVentures(FamilyMember familyMember, ArrayList<Resource> playerResources, TowerFloor towerFloor) {
		Venture ventureCard = (Venture) towerFloor.getDevelopmentCard();
		ArrayList<Resource> cost = ventureCard.cost;
		ArrayList<Resource> alternativeCost = ventureCard.alternativeCost;
		ArrayList<Resource> playerResources1 = playerResources;
		ArrayList<Resource> playerResources2 = playerResources;
		add(playerResources1, neg(cost));
		add(playerResources2, neg(alternativeCost));
		if (checkResources(playerResources1)){
			if (checkResources(playerResources2)){
				ArrayList<Resource> chooseCost = Manager.askForAlternativeCost(cost, alternativeCost);
				add(playerResources, neg(chooseCost));
				return true;
			}
			else{
				add(playerResources, neg(cost));
			}
		}
		else if(checkResources(playerResources2)){
			add(playerResources, neg(alternativeCost));
			return true;
		}
		return false;
	}


	private static boolean checkTerritories(FamilyMember familyMember){
		ArrayList<CardContainer> cardContainers = familyMember.getPlayer().getPersonalBoard().getCardContainers();
		for (CardContainer cardContainer : cardContainers) {
			if (cardContainer.type.equals(DevelopmentCardType.territory)){
				int numberOfCards = cardContainer.getDevelopmentCards().size();
				int[] militaryRequirement = Game.getData().getMilitaryRequirement();
				MilitaryPoints playerMilitaryPoints = (MilitaryPoints) familyMember.getPlayer().getPersonalBoard().getResource(ResourceType.militaryPoints);
				int requirementAmount = militaryRequirement[numberOfCards];
				playerMilitaryPoints.addAmount(requirementAmount);
				if (playerMilitaryPoints.getAmount() < 0){
					return false;
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
				if (resource.getResourceType().equals(playerResource.getResourceType())){
					playerResource.addAmount(resource.getAmount());
				}
			}
		}
	}
	
	private static ArrayList<Resource> neg(ArrayList<Resource> resources){
		for (Resource resource : resources) {
			int amount = -resource.getAmount();
			resource.setAmount(amount);
		}
		return resources;
	}
	
	
	
	private static boolean checkResources(ArrayList<Resource> resources){
		for (Resource resource : resources) {
			if (resource.getAmount() < 0) return false;
		}
		return true;
	}
}
