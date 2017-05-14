package it.polimi.ingsw.GC_15;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.CARD.ContainerBuildingCard;
import it.polimi.ingsw.CARD.ContainerCard;
import it.polimi.ingsw.CARD.ContainerCharacterCard;
import it.polimi.ingsw.CARD.ContainerTerritoryCard;
import it.polimi.ingsw.CARD.ContainerVentureCard;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.CARD.OncePerRoundLeaderCard;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.FaithPoints;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.Servants;
import it.polimi.ingsw.RESOURCE.Stones;
import it.polimi.ingsw.RESOURCE.VictoryPoints;
import it.polimi.ingsw.RESOURCE.Wood;

public class PersonalBoard {
	private PersonalBonusTile personalBonusTile;
	private boolean excommunicationCubes[];
	private ArrayList<ContainerCard> developmentCards;
	private ArrayList<Resource> resources;
	private ArrayList<LeaderCard> activatedLeaderCards;
	private ArrayList<LeaderCard> oncePerRoundBonusLeaderCard;
	private ArrayList<Bonus> permanentBonus;
	
	public PersonalBoard() {
		activatedLeaderCards= new ArrayList<>();
		oncePerRoundBonusLeaderCard= new ArrayList<>();
		permanentBonus= new ArrayList<>();
		excommunicationCubes= new boolean[3];
		
		developmentCards.add(new ContainerTerritoryCard());
		developmentCards.add(new ContainerBuildingCard());
		developmentCards.add(new ContainerVentureCard());
		developmentCards.add(new ContainerCharacterCard());
		
		resources.add(new Coins());
		resources.add(new Wood());
		resources.add(new Stones());
		resources.add(new Servants());
		resources.add(new FaithPoints());
		resources.add(new MilitaryPoints());
		resources.add(new VictoryPoints());
	}
	
	public void putLeaderCard(LeaderCard leaderCard) {
		activatedLeaderCards.add(leaderCard);
	}
	
	//attivo l'effetto onePerRound della carta leader
	public void activateLeaderEffect(LeaderCard leaderCard){
		if(leaderCard.checkActivationCondition()){
			((OncePerRoundLeaderCard) leaderCard).activateOncePerRoundBonus();
		}
	}
	
	public void putDevelopmentCard(DevelopmentCard developmentCard){
		for(int i=0; i < developmentCards.size(); i++){
			if(developmentCards.get(i).getType() == developmentCard.type){
				developmentCards.get(i).add(developmentCard);
			}
		}
	}
	
	public void activateDevelopmentCard(DevelopmentCard developmentCard){
		for(int i = 0; i < developmentCard.secondaryEffect.size(); i++){
			//TODO
		}
	}
	
	public void activateBonusTiles(DevelopmentCardType developmentCardType){
		//TODO: chiamare il controller che verifica i requisiti e se son soddifatti chiama (dipende dal developmentCardType)
		// o il activateProductionBonus della personalbonustile
		// o il activateHarvestBonus della personalBonusTile
	}
	
	//ADD methods -> similar to setters
	public void addPermanentBonus(Bonus bonus){
		permanentBonus.add(bonus);
	}
	
	public void addOncePerRoundBonusLeaderCard(LeaderCard leaderCard){
		oncePerRoundBonusLeaderCard.add(leaderCard);
	}
	
	public void addActivatedLeaderCards(LeaderCard leaderCard){
		activatedLeaderCards.add(leaderCard);
	}
	
	public void setEcxommunicationCube(int period){
		excommunicationCubes[period]=true;
	}

	public void setPersonalBonusTile(PersonalBonusTile personalBonusTile) {
		this.personalBonusTile = personalBonusTile;
	}
}
