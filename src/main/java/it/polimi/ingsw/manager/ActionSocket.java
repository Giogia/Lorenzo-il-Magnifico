package it.polimi.ingsw.manager;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.RESOURCE.Resource;

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
		askForLeaderCards
	}
	
	private action a;
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
	private ArrayList<Color> availableColors;
	private ArrayList<LeaderCard> leaderCards;
	
	public ActionSocket(action a) {
		this.a = a;
	}
	
	public void setA(action a) {
		this.a = a;
	}
	
	public action getA() {
		return a;
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
	public ArrayList<Color> getAvailableColors() {
		return availableColors;
	}
	public void setAvailableColors(ArrayList<Color> availableColors) {
		this.availableColors = availableColors;
	}
	public void setPersonalBonusTiles(ArrayList<PersonalBonusTile> personalBonusTiles){
		this.personalBonusTiles = personalBonusTiles;
	}
	public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
		this.leaderCards = leaderCards;	
	}
}
