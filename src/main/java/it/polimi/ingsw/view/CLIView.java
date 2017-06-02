package it.polimi.ingsw.view;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.BOARD.Board;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class CLIView implements View{
	Scanner scanner;
	
	public CLIView() throws Exception {
		hereIAm();
	}
	
	private void hereIAm() throws Exception{
		connectionManager.acceptUser(this);
	}
	

	@Override
	public void startTurn(Player player) {
		System.out.println(player.getName() + " è il tuo turno!");
	}

	@Override
	public int turnChoice() {
		System.out.println("Che azione vuoi effettuare?\n");
		System.out.println("1) Posiziona familiare \n2) Attiva carta leader \n3) Scarta carta leader \n4) Attiva "
				+ "l'effetto di una carta leader \n5) Statistiche \n6) Passa il turno\n");
		return checkInputError(1, 6);
	}

	@Override
	public void moveAlreadyDone() {
		System.out.println("Hai già posizionato in questo turno un familiare. Scegli un'altra opzione: ");
	}

	@Override
	public int chooseZone(Board board) {
		System.out.println("Scegli la zona in cui vuoi posizionare il familiare:\n");
		System.out.println("1) Torre Territori \n2) Torre Personaggi \n3) Torre Edifici \n4) Torre Imprese \n" + 
			"5) Palazzo del Consiglio \n6) Zona Raccolto \n7) Zona Produzione \n8) Mercato \n9) Torna indietro");
		return checkInputError(1, 9);
	}

	@Override
	public int choosePosition(Position[] positions) {
		System.out.println("Scegli la posizione in cui vuoi posizionare il familiare: ");
		for (int counter = 1; counter <= positions.length; counter ++) {
			String message = counter + ") " + positions[counter - 1].getDescription();
			System.out.println(message);
		}
		int lastChoice = positions.length + 1;
		String lastMessage = lastChoice + ") Torna indietro";
		System.out.println(lastMessage);
		return checkInputError(1, lastChoice);
	}

	@Override
	public int chooseFamilyMember(ArrayList<FamilyMember> familyMembers) {
		System.out.println("Scegli il familiare che vuoi utilizzare per effettare l'azione: ");
		for (int counter = 1; counter <= familyMembers.size(); counter++){
			String message = counter + ") " + familyMembers.get(counter - 1).getDescription();
			System.out.println(message);
		}
		int lastChoice = familyMembers.size() + 1;
		String lastMessage =  lastChoice + ") Torna indietro";
		System.out.println(lastMessage);
		return checkInputError(1, lastChoice);
	}

	@Override
	public int askForAlternativeCost(ArrayList<Resource> cost, ArrayList<Resource> alternativeCost) {
		System.out.println("La carta che hai scelto ha 2 costi. Scegli uno dei due: ");
		System.out.println("1) Primo costo");
		for (Resource resource : cost) {
			System.out.println(resource.getDescription());
		}
		System.out.println("2) Secondo costo");
		for (Resource resource : alternativeCost) {
			System.out.println(resource.getDescription());
		}
		System.out.println("Scegli un costo");
		return checkInputError(1, 2);
	}

	@Override
	public int askForCouncilPrivilege(ArrayList<ResourceBonus> councilPrivileges) {
		System.out.println("Scegli il bonus che vuoi avere dal Privilegio del Consiglio: ");
		for (int counter = 1; counter <= councilPrivileges.size(); counter++){
			String message = counter + ") " + councilPrivileges.get(counter - 1).getDescription();
			System.out.println(message);
		}
		return checkInputError(1, councilPrivileges.size());
	}

	@Override
	public int askForServants(int numberOfServants) {
		System.out.println("Hai " + numberOfServants + " servitori. Quanti vuoi usarne?");
		return checkInputError(0, numberOfServants);	
	}

	@Override
	public int askForInformation(Player[] players) {
		System.out.println("Di quale giocatore vuoi vedere le statistiche?");
		for (int counter = 1; counter <= players.length; counter++){
			String message = counter + ") " + players[counter - 1].getName();
			System.out.println(message);
		}
		int lastChoice = players.length + 1;
		String lastMessage = lastChoice + ") Torna indietro";
		System.out.println(lastMessage);
		return checkInputError(1, lastChoice);
	}

	@Override
	public void showPersonalBoard(PersonalBoard personalBoard) {
		System.out.println(personalBoard.getDescription());
	}
	
	@Override
	public void cantPassTurn() {
		System.out.println("Non puoi passare il turno poichè devi posizionare almeno un familiare.\n");
		
	}

	private int checkInputError(int min, int max){
		while (true){
			scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			if ( choice < min || choice > max){
				System.out.println("Input errato. Scegli di nuovo: ");
			}else{
				return choice;
			}
		}
	}
	
	public void giveInitialInformations(String toSend){
		System.out.println(toSend);
	}
	
}
