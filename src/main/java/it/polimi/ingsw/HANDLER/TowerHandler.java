package it.polimi.ingsw.HANDLER;

import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.GAME.DataFromFile;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCardType;

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
						if (towerFloor.getDevelopmentCard().developmentCardType.equals(DevelopmentCardType.TERRITORY)){
							ArrayList<CardContainer> cardContainers = familyMember.getPlayer().getPersonalBoard().getCardContainers();
							for (CardContainer cardContainer : cardContainers) {
								if (cardContainer.type.equals(DevelopmentCardType.TERRITORY)){
									int numberOfCards = cardContainer.getDevelopmentCards().size();
									MilitaryPoints[] requirement = DataFromFile.getMilitaryRequirement();
									MilitaryPoints playerMilitaryPoints = (MilitaryPoints) familyMember.getPlayer().getPersonalBoard().getResource(ResourceType.MILITARYPOINTS);
									int requirementAmount = requirement[numberOfCards].getAmount()*(-1);
									playerMilitaryPoints.addAmount(requirementAmount);
									if (playerMilitaryPoints.getAmount() < 0){
										return false;
									}
								}
							}
							if (!ZoneAlreadyOccupiedController.check(zone)){
								addOccupiedCost(playerResources);
							}
							familyMember.getPlayer().setFamilyMemberPosition(familyMember, towerFloor);
							return true;
						}
					}
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
}
