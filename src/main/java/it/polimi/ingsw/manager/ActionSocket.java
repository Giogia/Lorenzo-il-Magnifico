package it.polimi.ingsw.manager;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.manager.ActionSocket.action;
import it.polimi.ingsw.minigame.DevelopmentCardProxy;
import it.polimi.ingsw.minigame.GameProxy;
import it.polimi.ingsw.minigame.PositionProxy;
import it.polimi.ingsw.minigame.ResourceProxy;
import it.polimi.ingsw.minigame.TowerFloorProxy;

//includes all the actions and parameters to send in a socket connection
public class ActionSocket implements Serializable{
	public enum action{
		chooseName,
		chooseColor,
		startTurn,
		roundBegins,
		turnChoice,
		moveAlreadyDone,
		chooseZone,
		cantPassTurn,
		choosePosition,
		chooseFamilyMember,
		askForAlternativeCost,
		askForCouncilPrivilege,
		askForServants,
		askForInformation,
		showPersonalBoard,
		hasWon,
		askForAction,
		askForActionPosition,
		showDices,
		catchException, 
		askForPersonalBonusTile,
		askForLeaderCards, 
		askForLeaderCardAction,
		draftLeaderCards, 
		askForExcommunication,
		askForCardEffect,
		notYourTurn, 
		wrongInput, 
		integerError, 
		leftGame,
		askForUsername, 
		reconnectedToGame, 
		usernameHasAlreadyChosen, 
		timeExpired, 
		startGame,
		towerFloorOccupied, 
		positionOccupied, 
		updateResources,
	}
	
	private action action;
	private Position[] positions;
	private ArrayList<PersonalBonusTile> personalBonusTiles;
	private ArrayList<FamilyMember> familyMembers;
	private ArrayList<Resource> costs;
	private ArrayList<Resource> alternativeCosts;
	private ArrayList<ResourceBonus> bonus;
	private int numberOfServants;
	private String[] playersName;
	private PersonalBoard personalBoard;
	private String message; //it can be or a message of a exception or the name of the winner
	private ArrayList<ActionZone> zones;
	private ArrayList<Dice> dices;
	private String[] availableColors;
	private ArrayList<LeaderCard> leaderCards;
	private ArrayList<ExcommunicationTile> excommunicationTiles;
	private DevelopmentCard developmentCard;
	private String playerName;
	private Game game;
	private GameProxy gameProxy;
	private TowerFloorProxy towerFloorProxy;
	private DevelopmentCardProxy developmentCardProxy;
	private PositionProxy positionProxy;
	private ArrayList<ResourceProxy> resourceProxies;
	private Color color;
	
	public ActionSocket(action a) {
		this.action = a;
	}
	
	public void setAction(action a) {
		this.action = a;
	}
	
	public action getAction() {
		return action;
	}
	public Position[] getPositions() {
		return positions;
	}
	public void setPositions(Position[] positions) {
		this.positions = positions;
	}
	public ArrayList<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}
	public void setFamilyMembers(ArrayList<FamilyMember> familyMembers) {
		this.familyMembers = familyMembers;
	}
	public ArrayList<Resource> getCosts() {
		return costs;
	}
	public void setCosts(ArrayList<Resource> costs) {
		this.costs = costs;
	}
	public ArrayList<Resource> getAlternativeCosts() {
		return alternativeCosts;
	}
	public void setAlternativeCosts(ArrayList<Resource> alternativeCosts) {
		this.alternativeCosts = alternativeCosts;
	}
	public ArrayList<ResourceBonus> getBonus() {
		return bonus;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setBonus(ArrayList<ResourceBonus> bonus) {
		this.bonus = bonus;
	}
	public int getNumberOfServants() {
		return numberOfServants;
	}
	public void setNumberOfServants(int numberOfServants) {
		this.numberOfServants = numberOfServants;
	}
	public String[] getPlayersName() {
		return playersName;
	}
	public void setPlayersName(String[] playersName) {
		this.playersName = playersName;
	}
	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}
	public void setPersonalBoard(PersonalBoard personalBoard) {
		this.personalBoard = personalBoard;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList<ActionZone> getZones() {
		return zones;
	}
	public void setZones(ArrayList<ActionZone> zones) {
		this.zones = zones;
	}
	public ArrayList<Dice> getDices() {
		return dices;
	}
	public void setDices(ArrayList<Dice> dices) {
		this.dices = dices;
	}
	public String[] getAvailableColors() {
		return availableColors;
	}
	public void setAvailableColors(String[] availableColors) {
		this.availableColors = availableColors;
	}
	public void setPersonalBonusTiles(ArrayList<PersonalBonusTile> personalBonusTiles){
		this.personalBonusTiles = personalBonusTiles;
	}
	public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
		this.leaderCards = leaderCards;	
	}
	public ArrayList<LeaderCard> getLeaderCards() {
		return leaderCards;
	}
	public ArrayList<PersonalBonusTile> getPersonalBonusTiles() {
		return personalBonusTiles;
	}
	
	public void setExcommunicationTiles(ArrayList<ExcommunicationTile> excommunicationTiles) {
		this.excommunicationTiles = excommunicationTiles;
	}
	
	public ArrayList<ExcommunicationTile> getExcommunicationTiles() {
		return excommunicationTiles;
	}
	
	public DevelopmentCard getDevelopmentCard() {
		return developmentCard;
	}
	public void setDevelopmentCardEffect(DevelopmentCard developmentCard) {
		this.developmentCard = developmentCard;
	}

	public void setGame(Game thisGame) {
		this.game= thisGame;
	}
	
	public Game getGame() {
		return game;
	}

	public void setGameProxy(GameProxy gameProxy) {
		this.gameProxy = gameProxy;
	}
	
	public GameProxy getGameProxy() {
		return gameProxy;
	}

	public void setTowerFloorProxy(TowerFloorProxy towerFloorProxy) {
		this.towerFloorProxy = towerFloorProxy;
	}

	public void setDevelopmentCardProxy(DevelopmentCardProxy developmentCardProxy) {
		this.developmentCardProxy = developmentCardProxy;
	}
	
	public DevelopmentCardProxy getDevelopmentCardProxy() {
		return developmentCardProxy;
	}
	
	public TowerFloorProxy getTowerFloorProxy() {
		return towerFloorProxy;
	}
	
	public void setPositionProxy(PositionProxy positionProxy) {
		this.positionProxy = positionProxy;
	}
	
	public PositionProxy getPositionProxy() {
		return positionProxy;
	}
	
	public ArrayList<ResourceProxy> getResourceProxies() {
		return resourceProxies;
	}
	
	public void setResourceProxies(ArrayList<ResourceProxy> resourceProxies) {
		this.resourceProxies = resourceProxies;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
