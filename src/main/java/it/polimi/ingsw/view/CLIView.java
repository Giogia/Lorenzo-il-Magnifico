package it.polimi.ingsw.view;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.CONTROLLER.PassTurnController;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.HANDLER.PassTurnHandler;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class CLIView implements View{
	Scanner scanner;


	@Override
	public void startTurn(Player player) {
		System.out.println(player.getName() + " è il tuo turno!");
	}

	@Override
	public int turnChoice() {
		while(true){
			System.out.println("1) Posiziona familiare \n 2) Attiva carta leader \n 3) Scarta carta leader \n 4) Attiva "
					+ "l'effetto di una carta leader \n 5) Statistiche \n 6) Passa il turno");
			scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			if (choice < 1 || choice > 6){
				System.out.println("Input errato. Scegliere nuovamente");
			}
			else {
				return choice;
			}
		}
	}

	@Override
	public void moveAlreadyDone() {
		System.out.println("Azione già effettuata");
	}

	@Override
	public int chooseZone(Board board) {
		while (true){
			System.out.println("1) Torre Territori \n 2) Torre Personaggi \n 3) Torre Edifici \n 4) Torre Imprese \n" + 
					"5) Palazzo del Consiglio \n 6) Zona Raccolto \n 7) Zona Produzione \n 8) Mercato \n 9) Torna indietro");
			scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			if (choice < 1 || choice > 9){
				System.out.println("Input errato. Scegliere nuovamente");
			}
			else{
				return choice;
			}
		}
	}

	@Override
	public int choosePosition(Position[] positions) {
		while (true){
			for (int counter = 1; counter <= positions.length; counter ++) {
				String message = counter + ") " + positions[counter - 1].getDescription();
				System.out.println(message);
			}
			int lastChoice = positions.length + 1;
			String lastMessage = lastChoice + ") Torna indietro";
			System.out.println(lastMessage);
			scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			if (choice < 1 || choice > lastChoice){
				System.out.println("Input errato. Scegliere nuovamente");
			}
			else {
				return choice;
			}
		}
	}

	@Override
	public int chooseFamilyMember(ArrayList<FamilyMember> familyMembers) {
		while (true) {
			for (int counter = 1; counter <= familyMembers.size(); counter++){
				String message = counter + ") " + familyMembers.get(counter - 1).getDescription();
				System.out.println(message);
			}
			int lastChoice = familyMembers.size() + 1;
			String lastMessage =  lastChoice + ") Torna indietro";
			System.out.println(lastMessage);
			scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			if (choice < 1 || choice > lastChoice){
				System.out.println("Input errato. Scegliere nuovamente");
			}
			else {
				return choice;
			}
		}
	}

	@Override
	public int askForAlternativeCost(ArrayList<Resource> cost, ArrayList<Resource> alternativeCost) {
		while (true){
			System.out.println("1) Primo costo");
			for (Resource resource : cost) {
				System.out.println(resource.getDescription());
			}
			System.out.println("2) Secondo costo");
			for (Resource resource : alternativeCost) {
				System.out.println(resource.getDescription());
			}
			System.out.println("Scegli un costo");
			scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			if (choice < 1 || choice > 2){
				System.out.println("Input errato. Scegliere nuovamente");
			}
			else {
				return choice;
			}
		}
	}

	@Override
	public int askForCouncilPrivilege(ArrayList<ResourceBonus> councilPrivileges) {
		while (true){
			for (int counter = 1; counter <= councilPrivileges.size(); counter++){
				String message = counter + ") " + councilPrivileges.get(counter - 1).getDescription();
				System.out.println(message);
			}
			System.out.println("Scegli il tuo Privilegio");
			scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			if (choice < 1 || choice > councilPrivileges.size()){
				System.out.println("Input errato. Scegliere nuovamente");
			}
			else {
				return choice;
			}
		}
	}

	@Override
	public int askForServants(int numberOfServants) {
		while (true){
			System.out.println("Hai " + numberOfServants + " servitori. Quanti vuoi usarne?");
			scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			if (choice < 0 || choice > numberOfServants){
			System.out.println("Input errato. Scegliere nuovamente");
			}
			else {
				return choice;
			}
		}	
	}

	@Override
	public int askForInformation(Player[] players) {
		while (true){
			System.out.println("Di quale giocatore vuoi vedere le statistiche?");
			for (int counter = 1; counter <= players.length; counter++){
				String message = counter + ") " + players[counter - 1].getName();
				System.out.println(message);
			}
			int lastChoice = players.length + 1;
			String lastMessage = lastChoice + ") Torna indietro";
			System.out.println(lastMessage);
			scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			if (choice < 1 || choice > lastChoice){
				System.out.println("Input errato. Scegliere nuovamente");
			}
			else {
				return choice;
			}
		}
	}

	@Override
	public void showPersonalBoard(PersonalBoard personalBoard) {
		System.out.println(personalBoard.getDescription());
	}

}
