package it.polimi.ingsw.HANDLER.GAME;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.*;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.Resource;

public class Create {
	private Scanner in = new Scanner(System.in);
	
	public void createCard(){
		//PER ORA NON HA ALCUN CONTROLLO SULLA CORRETTEZZA DELL'INPUT! ATTENZIONE! 
		System.out.println("Che carta vuoi creare?");
		System.out.println("1) Territory");
		System.out.println("2) Building");
		System.out.println("3) Character");
		System.out.println("4) Venture");
		System.out.println("5) Leader");
		System.out.println("6) Excommunication Tile");
		
		int answer = in.nextInt();
		switch (answer) {
		case 1:	
			createTerritory();
			break;

		case 2:
			createBuilding();
			break;
		
		case 3:
			createCharacter();
			break;
			
		case 4:
			createVenture();
			break;
		
		case 5:
			createLeader();
			break;
			
		case 6:
			createExcommunicationTile();
			break;
		}
	}
		
	private Territory createTerritory(){
		System.out.println("Inserisci il nome: ");
		String nome = in.nextLine();
		System.out.println("Inserisci il periodo: ");
		int periodo = in.nextInt();
		ArrayList<ImmediateBonus> immediateEffects = createImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createBonus();
		return new Territory(nome, periodo, immediateEffects, secondaryEffects);
	}
	
	private Building createBuilding(){
		System.out.println("Inserisci il nome: ");
		String nome = in.nextLine();
		System.out.println("Inserisci la condizione di attivazione: ");
		int condizioneDiAttivazione = in.nextInt();
		ArrayList<Resource> costs = createCosts();
		System.out.println("Inserisci il periodo: ");
		int periodo = in.nextInt();
		ArrayList<ImmediateBonus> immediateEffects = createImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createBonus();
		return new Building(nome, condizioneDiAttivazione, costs, periodo, immediateEffects, secondaryEffects);
	}
		
	private Character createCharacter(){
		System.out.println("Inserisci il nome: ");
		String nome = in.nextLine();
		Coins cost = createCost();
		System.out.println("Inserisci il periodo: ");
		int periodo = in.nextInt();
		ArrayList<ImmediateBonus> immediateEffects = createImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createBonus();
		return new Character(nome, cost, periodo, immediateEffects, secondaryEffects);
	}
	
	private Venture createVenture(){
		System.out.println("Inserisci il nome: ");
		String nome = in.nextLine();
		ArrayList<Resource> costs = createCosts();
		ArrayList<Resource> alternativeCosts = createCosts();
		System.out.println("Inserisci il periodo: ");
		int periodo = in.nextInt();
		ArrayList<ImmediateBonus> immediateEffects = createImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createBonus();
		return new Venture(nome, costs, alternativeCosts, periodo, immediateEffects, secondaryEffects);
	}
	
	private void createLeader(){
		//TODO
	}
	
	private ExcommunicationTile createExcommunicationTile(){
		System.out.println("Inserisci il periodo: ");
		int periodo = in.nextInt();
		Bonus[] malus = createMalus();
		return new ExcommunicationTile(periodo, malus);
	}
	
	private ArrayList<ImmediateBonus> createImmediateBonus(){
		boolean vuoleUnAltroBonus=true;
		ArrayList<ImmediateBonus> bonusScelti=new ArrayList<>();
		while (vuoleUnAltroBonus){
			
			
			System.out.println("vuoi aggiungere un altro bonus?(si/no)");
			if (!(in.nextLine()=="si")) vuoleUnAltroBonus=false;
		}
	}
}
