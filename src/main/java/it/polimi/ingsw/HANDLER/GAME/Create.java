package it.polimi.ingsw.HANDLER.GAME;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.HarvestArea;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BONUS.*;
import it.polimi.ingsw.CARD.*;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.Resource;

public class Create {
	private static Scanner in = new Scanner(System.in);
	
	public static void createCard(){
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
		
	private static Territory createTerritory(){
		System.out.println("Inserisci il nome: ");
		String nome = in.nextLine();
		System.out.println("Inserisci il periodo: ");
		int periodo = in.nextInt();
		ArrayList<ImmediateBonus> immediateEffects = createArrayImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createArrayBonus();
		return new Territory(nome, periodo, immediateEffects, secondaryEffects);
	}
	
	private static Building createBuilding(){
		System.out.println("Inserisci il nome: ");
		String nome = in.nextLine();
		System.out.println("Inserisci la condizione di attivazione: ");
		int condizioneDiAttivazione = in.nextInt();
		ArrayList<Resource> costs = createCosts();
		System.out.println("Inserisci il periodo: ");
		int periodo = in.nextInt();
		ArrayList<ImmediateBonus> immediateEffects = createImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createArrayBonus();
		return new Building(nome, condizioneDiAttivazione, costs, periodo, immediateEffects, secondaryEffects);
	}
		
	private static Character createCharacter(){
		System.out.println("Inserisci il nome: ");
		String nome = in.nextLine();
		Coins cost = createCost();
		System.out.println("Inserisci il periodo: ");
		int periodo = in.nextInt();
		ArrayList<ImmediateBonus> immediateEffects = createImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createArrayBonus();
		return new Character(nome, cost, periodo, immediateEffects, secondaryEffects);
	}
	
	private static Venture createVenture(){
		System.out.println("Inserisci il nome: ");
		String nome = in.nextLine();
		ArrayList<Resource> costs = createCosts();
		ArrayList<Resource> alternativeCosts = createCosts();
		System.out.println("Inserisci il periodo: ");
		int periodo = in.nextInt();
		ArrayList<ImmediateBonus> immediateEffects = createImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createArrayBonus();
		return new Venture(nome, costs, alternativeCosts, periodo, immediateEffects, secondaryEffects);
	}
	
	private static void createLeader(){
		//TODO
	}
	
	private static ExcommunicationTile createExcommunicationTile(){
		System.out.println("Inserisci il periodo: ");
		int periodo = in.nextInt();
		Bonus[] malus = createArrayBonus();
		return new ExcommunicationTile(periodo, malus);
	}
	
	private static ArrayList<ImmediateBonus> createArrayImmediateBonus(){
		boolean vuoleUnAltroBonus=true;
		ArrayList<ImmediateBonus> bonusScelti=new ArrayList<>();
		while (vuoleUnAltroBonus){
			ImmediateBonus bonus= createImmediateBonus();
			bonusScelti.add(bonus);
			System.out.println("vuoi aggiungere un altro bonus?(si/no)");
			if (!(in.nextLine()=="si")) vuoleUnAltroBonus=false;
		}
		return bonusScelti;
	}
	
	private static ImmediateBonus createImmediateBonus(){
		System.out.println("Che tipo di bonus deve essere?");
		System.out.println("1) ActionBonus");
		System.out.println("2) AddFamilyMemberBonus");
		System.out.println("3) MultiplyFamilyMemberBonus");
		System.out.println("4) FamilyMemberValueBonus");
		System.out.println("5) ResourcPerDevelopmentCardeBonus");
		System.out.println("6) ResourceValueBonus");
		System.out.println("7) AddResourceBonus");
		System.out.println("8) MultiplyResourceBonus");
		System.out.println("9) CouncilPrivilegeBonus");
		
		int answer = in.nextInt();
		switch (answer) {
		case 1:	
			createActionBonus();
			break;

		case 2:
			createAddFamilyMemberBonus();
			break;
		
		case 3:
			createMultiplyFamilyMemberBonus();
			break;
			
		case 4:
			createFamilyMemberValueBonus();
			break;
		
		case 5:
			createResourcPerDevelopmentCardBonus();
			break;
			
		case 6:
			createResourceValueBonus();
			break;
		
		case 7:
			createAddResourceBonus();
			break;
			
		case 8:
			createMultiplyResourceBonus();
			break;
			
		case 9:
			createCouncilPrivilegeBonus();
			break;
		}
	}
	
	private static CouncilPrivilegeBonus createCouncilPrivilegeBonus(){
		System.out.println("Quanti privilegi del consiglio diversi deve avere il bonus? ");
		int value = in.nextInt();
		
		return new CouncilPrivilegeBonus(value, createArrayResourceBonus());
	}
	
	private static ArrayList<ResourceBonus> createArrayResourceBonus(){
		ArrayList<ResourceBonus> arrayResourceBonus = new ArrayList<>();
		boolean vuoleUnAltroBonus = true;
		
		while (vuoleUnAltroBonus){
			arrayResourceBonus.add(createResourceBonus());
			System.out.println("vuoi aggiungere un altro bonus?(si/no)");
			if (!(in.nextLine()=="si")) vuoleUnAltroBonus=false;
		}
		return arrayResourceBonus;
	}
	
	private static ResourceBonus createResourceBonus(){
		ResourceBonus resourceBonus = null;
		
		System.out.println("Quale bonus vuoi creare? ");
		System.out.println("1) AddResourceBonus");
		System.out.println("2) MultiplyResourceBonus");
		System.out.println("3) ResourceValueBonus");
		System.out.println("4) ResourcePerDevelopmentCardBonus");
		
		int answer= in.nextInt();
		switch (answer) {
		case 1:
			return createAddResourceBonus();
			break;

		case 2:
			return createMultiplyResourceBonus();
			break;
		
		case 3:
			return createResourceValueBonus();
			break;
			
		case 4: 
			return createResourcePerDevelopmentCardBonus();
			break;
		}
	}
	
	private static AddResourceBonus createAddResourceBonus(){
		return new AddResourceBonus(createArrayResource());
	}
	
	private static ArrayList<Resource> createArrayResource(){
		//prova
	}
	
	private static ActionBonus createActionBonus(){
		HashMap<ActionZone, Integer> action = new HashMap<>();
		boolean vuoleAltraZona = true;
		int answer;
		ActionZone zone = null;
		while(vuoleAltraZona){
			System.out.println("Il bonus che zona deve attivare?");
			System.out.println("1) Torre verde");
			System.out.println("2) Torre gialla");
			System.out.println("3) Torre blu");
			System.out.println("4) Torre viola");
			System.out.println("5) HarvestArea");
			System.out.println("6) ProductionArea");
			
			answer = in.nextInt();
			switch (answer){
			case 1:	
				zone = new Tower(0, false, DevelopmentCardType.TERRITORY);//first 2 parameters are random
				break;
	
			case 2:
				zone = new Tower(0, false, DevelopmentCardType.BUILDING);//first 2 parameters are random
				break;
			
			case 3:
				zone = new Tower(0, false, DevelopmentCardType.CHARACTER);
				break;
				
			case 4:
				zone = new Tower(0, false, DevelopmentCardType.VENTURE);
				break;
			
			case 5:
				zone = new HarvestArea();
				break;
				
			case 6:
				zone = new ProductionArea();
				break;
			}
			
			System.out.println("Quale deve essere il valore dell'azione?");
			answer = in.nextInt();
			
			action.put(zone, answer);
			System.out.println("vuoi aggiungere un'altra zona?(si/no)");
			if (!(in.nextLine()=="si")) vuoleAltraZona=false;
		}
		return new ActionBonus(action);
	}
	
	private static AddFamilyMemberBonus createAddFamilyMemberBonus(){
		ArrayList<FamilyMember> familyMembers= createFamilyMembers();
		return new AddFamilyMemberBonus(familyMembers);
	}
	
	private static MultiplyFamilyMemberBonus createMultiplyFamilyMemberBonus(){
		ArrayList<FamilyMember> familyMembers= createFamilyMembers();
		return new MultiplyFamilyMemberBonus(familyMembers);
	}
	
	private static FamilyMemberValueBonus createFamilyMemberValueBonus(){
		ArrayList<FamilyMember> familyMembers= createFamilyMembers();
		return new FamilyMemberValueBonus(familyMembers);
	}
	
	private static ArrayList<FamilyMember> createFamilyMembers(){
		boolean vuoleCreareAltroFamilyMember = true;
		ArrayList<FamilyMember> familyMembers = new ArrayList<>();
		while (vuoleCreareAltroFamilyMember){
			familyMembers.add(createFamilyMember());
			System.out.println("vuoi aggiungere un altro family member?(si/no)");
			if (!(in.nextLine()=="si")) vuoleCreareAltroFamilyMember=false;
		}
		return familyMembers;
	}
	
	private static FamilyMember createFamilyMember(){
		int answer;
		Dice dice = null;;
		
		System.out.println("Quale family member vuoi creare ?");
		System.out.println("1) Arancione");
		System.out.println("2) Bianco");
		System.out.println("3) Nero");
		System.out.println("4) Neutro");
		System.out.println("5) Fasullo");
		
		answer = in.nextInt();
		switch (answer){
		case 1:	
			dice = new Dice(DiceColour.Orange);
			break;

		case 2:
			dice = new Dice(DiceColour.White);
			break;
		
		case 3:
			dice = new Dice(DiceColour.Black);
			break;
			
		case 4:
			dice = new Dice(DiceColour.Neutral);
			break;
			
		case 5:
			dice = new Dice(DiceColour.Fake);
			break;
		}
		
		System.out.println("Che valore deve avere il family member?");
		int value= in.nextInt();
		dice.setValue(value);
		return new FamilyMember(dice, null);
	}
}
