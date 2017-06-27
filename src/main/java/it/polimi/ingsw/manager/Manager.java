package it.polimi.ingsw.manager;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.HarvestArea;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.HANDLER.PassTurnHandler;
import it.polimi.ingsw.HANDLER.ResetFamilyMemberValueHandler;
import it.polimi.ingsw.HANDLER.ADVANCED.ActivateLeaderCardHandler;
import it.polimi.ingsw.HANDLER.ADVANCED.DiscardLeaderCardHandler;
import it.polimi.ingsw.HANDLER.ADVANCED.PermanentFamilyMemberBonusHandler;
import it.polimi.ingsw.HANDLER.ADVANCED.UseLeaderCardEffectHandler;
import it.polimi.ingsw.HANDLER.GAME.ActionHandler;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class Manager{
	private static int choice = 0;
	private Manager instance;
	
	private Manager() { }
	
	public Manager getManager() {
		if (instance == null){
			instance = new Manager();
		}
		return instance;
	}

	//begins the turn of the player 
	public static void startTurn(Player player, ArrayList<Player> playersInGame) throws IOException{
		ConnectionManagerImpl.startTurn(player, playersInGame);
		//if player is connected, ask question. Else go ahead with next player
		User user = ConnectionManagerImpl.getConnectionManager().findUserByPlayer(player);
		if(!ConnectionManagerImpl.getConnectionManager().getUsersDisconnected().contains(user)){ 
			turnChoice(player);
		}else{
			player.getBoard().getPassTurnController().lastMove(player);//also if he isn't connected, he has done the action!
		}
	}

	private static void turnChoice(Player player) throws IOException{
		while(true){
			int choice;
			try {
				choice = ConnectionManagerImpl.getConnectionManager().turnChoice(player);
				switch (choice) {
				case 1:
					actionManager(player);
					break;
				
				case 2:
					leaderCardManager(player);
					break;
			
				case 3:
					activationLeaderCardEffectManager(player);
					break;
					
				case 4:
					askForInformation(player);
					break;
			
				case 5:
					if (PassTurnHandler.handle(player)){
						ConnectionManagerImpl.cancelTimer(player);
						ResetFamilyMemberValueHandler.handle(player);
						PermanentFamilyMemberBonusHandler.handle(player);
						return;
					}
					//tell to player he can't pass turn
					ConnectionManagerImpl.cantPassTurn(player);
					break;
					
				default:
					ConnectionManagerImpl.integerError(player);
				}
			} catch (TimeExpiredException e) {
				ConnectionManagerImpl.cancelTimer(player);
				ConnectionManagerImpl.timeExpired(player);
				player.getBoard().getPassTurnController().lastMove(player);
				ResetFamilyMemberValueHandler.handle(player);
				PermanentFamilyMemberBonusHandler.handle(player);
				return;
			}
		}
	}

	private static void leaderCardManager(Player player) throws IOException, TimeExpiredException {
		int index = chooseLeaderCard(player, player.getLeaderCardInHand());
		if(index==player.getLeaderCardInHand().size())
			return;
		LeaderCard chosenCard = player.getLeaderCardInHand().get(index);
		int choice = ConnectionManagerImpl.getConnectionManager().LeaderCardActionChoice(player);
		try{
			switch (choice) {
			case 1:
				ActivateLeaderCardHandler.handle(player,chosenCard);
				return;
			case 2:
				DiscardLeaderCardHandler.handle(player,chosenCard);
				return;
			case 3:
				return;
			default:
				ConnectionManagerImpl.integerError(player);
			}
		}
		catch(MyException exc){			
			ConnectionManagerImpl.catchException(exc.getMessage(), player);
		}
	}
	
	private static void activationLeaderCardEffectManager(Player player) throws IOException, TimeExpiredException {
		try{
			ArrayList<LeaderCard> leaderCards = player.getPersonalBoard().getOncePerRoundBonusLeaderCard();
			
			do{
				choice= chooseLeaderCard(player, leaderCards);
			}while(!hasAnsweredWell(1, leaderCards.size(), choice, player));
			if (choice == leaderCards.size())
				return;
			LeaderCard chosenCard = leaderCards.get(choice);
			UseLeaderCardEffectHandler.handle(player, chosenCard);
		}
		catch(MyException exc){			
			ConnectionManagerImpl.catchException(exc.getMessage(), player);
		}
		
	}

	private static void askForInformation(Player player) throws IOException, TimeExpiredException {
		Player[] players = player.getBoard().getPlayers();
		String[] names = new String[players.length];
		for (int i = 0; i < players.length; i++) {
			names[i] = players[i].getName();
		}
		do{
			choice = ConnectionManagerImpl.getConnectionManager().askForInformation(player, names);
		}while(! hasAnsweredWell(1, players.length +1, choice, player));
		
		if (choice == players.length + 1){
			return;
		}
		PersonalBoard personalBoard = players[choice - 1].getPersonalBoard();
		ConnectionManagerImpl.showPersonalBoard(player, personalBoard);
	}

	private static boolean hasAnsweredWell(int i, int j, int choice, Player player) throws RemoteException, IOException {
		if(i <= choice && choice <= j){
			return true;
		}else{
			ConnectionManagerImpl.getConnectionManager().integerError(player);//tell player he has answered wrong 
			return false;
		}
	}

	private static void actionManager(Player player) throws IOException, TimeExpiredException {
		PassTurnController passTurnController = player.getBoard().getPassTurnController();
		if (passTurnController.getLastMove() != null){
			if (passTurnController.getLastMove().equals(player)){
				ConnectionManagerImpl.moveAlreadyDone(player);
				return;
			}
		}
		Board board = player.getBoard();
		while(true){
			do{
				choice = ConnectionManagerImpl.getConnectionManager().chooseZone(player);
			}while(! hasAnsweredWell(1, 9, choice, player));
			
			try{
				switch (choice) {
				case 1:
					if (zoneManager(player, board.getTower(DevelopmentCardType.territory))){
						return;
					}
					break;
				case 2:
					if (zoneManager(player, board.getTower(DevelopmentCardType.character))){
						return;
					}
					break;
				case 3:
					if (zoneManager(player, board.getTower(DevelopmentCardType.building))){
						return;
					}
					break;
				case 4:
					if (zoneManager(player, board.getTower(DevelopmentCardType.venture))){
						return;
					}
					break;
				case 5:
					if (zoneManager(player, board.getCouncilPalace())){
						return;
					}
					break;
				case 6:
					if (zoneManager(player, board.getHarvestArea())){
						return;
					}
					break;
				case 7:
					if (zoneManager(player, board.getProductioArea())){
						return;
					}
					break;
				case 8:
					if (zoneManager(player, board.getMarket())){
						return;
					}
					break;
				case 9:	
					return;
				}	
			}
			catch(MyException exc){			
				ConnectionManagerImpl.catchException(exc.getMessage(), player);
			}
		}
		
	}
	
	
	/* Le posizioni vanno da 1 alla posizione massima
	 * Se ritorna posizione massima + 1, significa che devi tornare indietro
	 */
	private static boolean zoneManager(Player player, Zone zone) throws MyException, IOException, TimeExpiredException {
		Position[] positions = zone.getPositions();
		do{
			choice = ConnectionManagerImpl.getConnectionManager().choosePosition(player, positions);
		}while(! hasAnsweredWell(1, positions.length+1, choice, player));
		
		if (choice == positions.length + 1){
			return false;
		} else {
			return familyMemberManager(player, zone, positions[choice - 1]);
		}
	}
	
	//Come prima, se il connectionManager ritorna numero di familiari +1, torna indietro, altrimenti usa quel familiare
	private static boolean familyMemberManager(Player player, Zone zone, Position position) throws MyException, IOException, TimeExpiredException{
		ArrayList<FamilyMember> familyMembers = player.getFamilyMembers();
		do{
			choice = ConnectionManagerImpl.getConnectionManager().chooseFamilyMember(player, familyMembers);
		}while(! hasAnsweredWell(1, familyMembers.size() + 1, choice, player));
		
		if (choice == familyMembers.size() + 1){
			return false;
		}
		else {
			return ActionHandler.handle(familyMembers.get(choice-1), zone, position);
		}
	}

	public static ArrayList<Resource> askForAlternativeCost(Player player, ArrayList<Resource> costs,
			ArrayList<Resource> alternativeCosts) throws IOException, TimeExpiredException {
		do{
			choice = ConnectionManagerImpl.getConnectionManager().askForAlternativeCost(player, costs, alternativeCosts);
		}while(!hasAnsweredWell(1, 2, choice, player));
		if (choice == 1){
			return costs;
		}
		else return alternativeCosts;
	}

	public static ResourceBonus getCouncilPrivilege(Player player, ArrayList<ResourceBonus> councilPrivileges) throws IOException, TimeExpiredException {
		do{
			choice = ConnectionManagerImpl.getConnectionManager().askForCouncilPrivilege(player, councilPrivileges);	
		}while(!hasAnsweredWell(1, councilPrivileges.size(), choice, player));
		return councilPrivileges.get(choice-1);
	}
	
	public static int askForServants(Player player) throws IOException, TimeExpiredException {
		int numberOfServants = player.getPersonalBoard().getResource(ResourceType.servants).getAmount();
		do{
			choice = ConnectionManagerImpl.getConnectionManager().askForServants(player, numberOfServants);
		}while(!hasAnsweredWell(0, numberOfServants, choice, player));
		return choice;
	}

	public static Position askForAction(FamilyMember familyMember, ActionZone zone, Board board) throws IOException, TimeExpiredException {
		zone = getBoardZone(zone, board);
		Player player = familyMember.getPlayer();
		Position[] zonePositions = zone.getPositions();
		
		do{
			choice = ConnectionManagerImpl.getConnectionManager().chooseActionPosition(player, zonePositions);
		}while(!hasAnsweredWell(1, zonePositions.length, choice, player));
		return zonePositions[choice - 1];
	}

	private static ActionZone getBoardZone(ActionZone zone, Board board) {
		if (zone instanceof Tower){
			DevelopmentCardType developmentCardType = ((Tower) zone).getDevelopmentCardType();
			zone = board.getTower(developmentCardType);
		}
		else if (zone instanceof ProductionArea){
			zone = board.getProductioArea();
		}
		else{
			zone = board.getHarvestArea();
		}
		return zone;
	}

	
	public static boolean askForExcommunication(Player player, ExcommunicationTile excommunicationTile) throws IOException, TimeExpiredException{
		do{
			choice = ConnectionManagerImpl.getConnectionManager().askForExcommunication(player, excommunicationTile);
		}while(! hasAnsweredWell(1, 2, choice, player));
		
		if ( choice == 1){//player wants to be excommunicated
			return true;
		}
		return false;
	}
	
	public static LeaderCard choiceLeaderCardToCopy(Player activatedPlayer, LeaderCard leaderCard) throws IOException, MyException, TimeExpiredException {
		ArrayList<LeaderCard> leaderCardsToChoice = new ArrayList<>();
		Player[] players = activatedPlayer.getBoard().getPlayers();
		for (Player player : players) {
			ArrayList<LeaderCard> activatedLeaderCard = player.getPersonalBoard().getActivatedLeaderCards();
			for (LeaderCard leaderCard2 : activatedLeaderCard) {
				if (!leaderCard2.equals(leaderCard)){
					leaderCardsToChoice.add(leaderCard2);
				}
			}
		}
		if (leaderCardsToChoice.isEmpty()){
			return null;
		}
		do{
			choice = ConnectionManagerImpl.getConnectionManager().chooseLeaderCard(activatedPlayer, leaderCardsToChoice);
		}while(!hasAnsweredWell(1, leaderCardsToChoice.size() + 1, choice, activatedPlayer));
		
		if (choice == leaderCardsToChoice.size() + 1){
			return null;
		}
		return leaderCardsToChoice.get(choice - 1);
		
	}

	public static ActionZone askForZone(ArrayList<ActionZone> actionZones, Player player) throws IOException, TimeExpiredException {
		do{
			choice = ConnectionManagerImpl.getConnectionManager().askForZone(actionZones, player);
		}while(!hasAnsweredWell(1, actionZones.size(), choice, player));
		return actionZones.get(choice - 1);
	}
	
	public static ArrayList<Bonus> chooseEffect(Player player, DevelopmentCard developmentCard) throws IOException, TimeExpiredException{
		do{
			choice= ConnectionManagerImpl.getConnectionManager().chooseEffect(player,developmentCard);
		}while(!hasAnsweredWell(1, developmentCard.secondaryEffect.size(), choice, player));
		ArrayList<Bonus> bonusChoosen = new ArrayList<>();
		int counter = 0;
		for(int i=0; i<developmentCard.secondaryEffect.size();i++){
			if(developmentCard.secondaryEffect.get(i) instanceof ResourceBonus ){
				counter++;
				if(choice == counter){
				bonusChoosen.add(developmentCard.secondaryEffect.get(i));
				i++;
				while(i<developmentCard.secondaryEffect.size() && !(developmentCard.secondaryEffect.get(i) instanceof ResourceBonus)){
					bonusChoosen.add(developmentCard.secondaryEffect.get(i));
					i++;
					}
				}
			}
		}
		return bonusChoosen;
	}
	
	public static PersonalBonusTile askForPersonalBonusTile(Player player, ArrayList<PersonalBonusTile> personalBonusTiles) throws IOException{
		do{
			try {
				ConnectionManagerImpl.startTimer(player);
				choice = ConnectionManagerImpl.getConnectionManager().choosePersonalBonusTile(player, personalBonusTiles);
			} catch (TimeExpiredException e) {
				choice = 1;
			}
			ConnectionManagerImpl.cancelTimer(player);
 		}while(!hasAnsweredWell(1, personalBonusTiles.size(), choice, player));
		
		PersonalBonusTile tileChoosen = personalBonusTiles.get(choice - 1);
		return tileChoosen;
	}
	
	public static int chooseLeaderCard(Player player,ArrayList<LeaderCard> leaderCards) throws IOException, TimeExpiredException { //return i if i-th element of array is chosen
		do{
			choice = ConnectionManagerImpl.getConnectionManager().chooseLeaderCard(player, leaderCards);
		}while(!hasAnsweredWell(1, leaderCards.size() , choice, player));
		return choice-1;
	}

	public static int draftLeaderCard(Player player, ArrayList<LeaderCard> leaderCards) throws IOException{
		do{
			ConnectionManagerImpl.startTimer(player);
			try {
				choice = ConnectionManagerImpl.getConnectionManager().draftLeaderCard(player, leaderCards);
			} catch (TimeExpiredException e) {
				choice = 1;
			}
			ConnectionManagerImpl.cancelTimer(player);
		}while(!hasAnsweredWell(1, leaderCards.size(), choice, player));
		return choice - 1;
	}
}
