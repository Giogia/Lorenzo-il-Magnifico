package it.polimi.ingsw.HANDLER.GAME;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.BONUS.*;
import it.polimi.ingsw.CARD.*;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.GC_15.*;
import it.polimi.ingsw.RESOURCE.*;

public class Create {
	private static Scanner in = new Scanner(System.in);
	private static Scanner in2 = new Scanner(System.in);
	
	public static int[] genericArray(){
		System.out.println("Che dimensione deve avere l'array?");
		int answer = in.nextInt();
		int[] generic = new int[answer];
		for(int i=0; i < answer; i++){
			System.out.println("Inserisci l'intero: ");
			generic[i]= in.nextInt();
		}
		return generic;
	}
	
	public static ArrayList<ResourceBonus> createCouncilPrivileges(){
		boolean vuoleUnAltroBonus=true;
		String risposta;
		ArrayList<ResourceBonus> councilPrivileges = new ArrayList<>();
		while (vuoleUnAltroBonus){
			councilPrivileges.add(createResourceBonus());
			System.out.println("vuoi aggiungere un altro bonus?(si/no)");
			risposta = in2.nextLine();
			if (!("si".equals(risposta))) vuoleUnAltroBonus=false;
		}
		return councilPrivileges;
	}
	
	public static PersonalBonusTile createPersonalBonusTile(){
		System.out.println("Inserisci la condizione di attivazione del raccolto: ");
		int harvestActivationCondition = in.nextInt();
		System.out.println("Inserisci la condizione di attivazione della produzione: ");
		int productionActivationCondition = in.nextInt();
		System.out.println("Inserisci il bonus per il raccolto: ");
		ImmediateBonus harvestBonus = createImmediateBonus();
		System.out.println("Inserisci il bonus per la produzione: ");
		ImmediateBonus productionBonus = createImmediateBonus();
		return new PersonalBonusTile(harvestBonus, productionBonus, harvestActivationCondition, productionActivationCondition);
	}
		
	public static Territory createTerritory(){
		System.out.println("Inserisci il nome: ");
		String nome = in2.nextLine();
		System.out.println("Inserisci il periodo: ");
		int periodo = in.nextInt();
		System.out.println("Inserisci la condizione di attivazione del raccolto: ");
		int condizioneAttivazione= in.nextInt();
		ArrayList<ImmediateBonus> immediateEffects = createArrayImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createArrayBonus();
		return new Territory(nome, condizioneAttivazione, periodo, immediateEffects, secondaryEffects);
	}
	
	public static Building createBuilding(){
		System.out.println("Inserisci il nome: ");
		String nome = in2.nextLine();
		System.out.println("Inserisci il periodo: ");
		int periodo = in.nextInt();
		System.out.println("Inserisci la condizione di attivazione della produzione: ");
		int condizioneDiAttivazione = in.nextInt();
		System.out.println("la prossima cosa da inserire sono i costi della carta");
		ArrayList<Resource> costs = createArrayResource();
		ArrayList<ImmediateBonus> immediateEffects = createArrayImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createArrayBonus();
		return new Building(nome, condizioneDiAttivazione, costs, periodo, immediateEffects, secondaryEffects);
	}
		
	public static Character createCharacter(){
		System.out.println("Inserisci il nome: ");
		String nome = in2.nextLine();
		Coins cost = createCoins();
		System.out.println("Inserisci il periodo: ");
		int periodo = in.nextInt();
		ArrayList<ImmediateBonus> immediateEffects = createArrayImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createArrayBonus();
		return new Character(nome, cost, periodo, immediateEffects, secondaryEffects);
	}
	
	public static Venture createVenture(){
		System.out.println("Inserisci il nome: ");
		String nome = in2.nextLine();
		System.out.println("Inserisci il periodo: ");
		int periodo = in.nextInt();
		ArrayList<ImmediateBonus> immediateEffects = createArrayImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createArrayBonus();
		System.out.println("Inserire il requisito di punti militari (se non lo tiene, inserire 0)");
		int requirement = in.nextInt();
		System.out.println("La prossima cosa da inserire sono i costi della carta");
		ArrayList<Resource> costs = createArrayResource();
		System.out.println("La carta ha un costo alternativo?");
		String risposta = in2.nextLine();
		if ("si".equals(risposta)){
			MilitaryPoints alternativeCosts = createMilitaryPoints();
			return new Venture(nome, requirement, costs, alternativeCosts, periodo, immediateEffects, secondaryEffects);
		}
		return new Venture(nome, requirement, costs, null, periodo, immediateEffects, secondaryEffects);
	}
	
	public static LeaderCard createLeader(){
		return null; //TODO REGOLE AVANZATE
	}
	
	public static ExcommunicationTile createExcommunicationTile(){
		System.out.println("Inserisci il periodo: ");
		int periodo = in.nextInt();
		//Bonus[] malus = createArrayMalus();TODO REGOLE AVANZATE
		Bonus[] malus = null;
		return new ExcommunicationTile(periodo, malus);
	}
	
	public static ArrayList<ImmediateBonus> createArrayImmediateBonus(){
		boolean vuoleUnAltroBonus=true;
		String risposta;
		ArrayList<ImmediateBonus> bonusScelti=new ArrayList<>();
		System.out.println("La carta ha un bonus immediato?(si/no) ");
		String risp = in2.nextLine();
		if ("no".equals(risp)){//se la carta non ha un bonus immediato ritorno un null
			return null;
		}
		while (vuoleUnAltroBonus){
			ImmediateBonus bonus= createImmediateBonus();
			bonusScelti.add(bonus);
			System.out.println("vuoi aggiungere un altro bonus?(si/no)");
			risposta = in2.nextLine();
			if (!("si".equals(risposta))) vuoleUnAltroBonus=false;
		}
		return bonusScelti;
	}
	
	private static Bonus[] createArrayMalus(){
		return null; //TODO!!!
	}
	
	private static ArrayList<Bonus> createArrayBonus(){
		return null; //TODO!!!
	}
	
	public static ImmediateBonus createImmediateBonus(){
		ImmediateBonus bonus = null;
		System.out.println("Che tipo di bonus deve essere?");
		System.out.println("1) ActionBonus");
		System.out.println("2) AddFamilyMemberBonus");
		System.out.println("3) MultiplyFamilyMemberBonus");
		System.out.println("4) FamilyMemberValueBonus");
		System.out.println("5) ResourcePerDevelopmentCardBonus");
		System.out.println("6) ResourceValueBonus");
		System.out.println("7) AddResourceBonus");
		System.out.println("8) MultiplyResourceBonus");
		System.out.println("9) CouncilPrivilegeBonus");
		System.out.println("10) ResourcePerResource");
		
		int answer = in.nextInt();
		switch (answer) {
		case 1:	
			bonus = createActionBonus();
			break;

		case 2:
			bonus = createAddFamilyMemberBonus();
			break;
		
		case 3:
			bonus = createMultiplyFamilyMemberBonus();
			break;
			
		case 4:
			bonus = createFamilyMemberValueBonus();
			break;
		
		case 5:
			bonus = createResourcePerDevelopmentCardBonus();
			break;
			
		case 6:
			bonus = createResourceValueBonus();
			break;
		
		case 7:
			bonus = createAddResourceBonus();
			break;
			
		case 8:
			bonus = createMultiplyResourceBonus();
			break;
			
		case 9:
			bonus = createCouncilPrivilegeBonus();
			break;
		
		case 10:
			bonus = createResourcePerResourceBonus();
		}
		return bonus;
		
	}
	
	private static ResourcePerResourceBonus createResourcePerResourceBonus(){
		System.out.println("Inserire le risorse che il bonus ti dà: ");
		ArrayList<Resource> resources = createArrayResource();
		System.out.println("Inserire quale risorsa ha come requisito il bonus: ");
		Resource requirement = createResource();
		return new ResourcePerResourceBonus(resources, requirement);
	}
	
	private static CouncilPrivilegeBonus createCouncilPrivilegeBonus(){
		System.out.println("Quanti privilegi del consiglio diversi deve avere il bonus? ");
		int value = in.nextInt();
		
		return new CouncilPrivilegeBonus(value);
	}
	
	private static ArrayList<ResourceBonus> createArrayResourceBonus(){
		ArrayList<ResourceBonus> arrayResourceBonus = new ArrayList<>();
		String risposta;
		boolean vuoleUnAltroBonus = true;
		
		while (vuoleUnAltroBonus){
			arrayResourceBonus.add(createResourceBonus());
			System.out.println("vuoi aggiungere un altro bonus?(si/no)");
			risposta = in2.nextLine();
			if (!("si".equals(risposta))) vuoleUnAltroBonus=false;
		}
		return arrayResourceBonus;
	}
	
	public static ResourceBonus createResourceBonus(){
		ResourceBonus resourceBonus = null;
		
		System.out.println("Quale bonus vuoi creare? ");
		System.out.println("1) AddResourceBonus");
		System.out.println("2) MultiplyResourceBonus");
		System.out.println("3) ResourceValueBonus");
		System.out.println("4) ResourcePerDevelopmentCardBonus");
		
		int answer= in.nextInt();
		switch (answer) {
		case 1:
			resourceBonus = createAddResourceBonus();
			break;

		case 2:
			resourceBonus = createMultiplyResourceBonus();
			break;
		
		case 3:
			resourceBonus = createResourceValueBonus();
			break;
			
		case 4: 
			resourceBonus = createResourcePerDevelopmentCardBonus();
			break;
		}
		return resourceBonus;
	}
	
	private static AddResourceBonus createAddResourceBonus(){
		return new AddResourceBonus(createArrayResource());
	}
	
	private static MultiplyResourceBonus createMultiplyResourceBonus(){
		return new MultiplyResourceBonus(createArrayResource());
	}
	
	private static ResourceValueBonus createResourceValueBonus(){
		return new ResourceValueBonus(createArrayResource());
	}
	
	private static ResourcePerDevelopmentCardBonus createResourcePerDevelopmentCardBonus(){
		ArrayList<Resource> resources = createArrayResource();
		DevelopmentCardType tipo = null;
		
		System.out.println("Scegli il tipo della carta: ");
		System.out.println("1) verde");
		System.out.println("2) giallo");
		System.out.println("3) blu");
		System.out.println("4) viola");
		
		int answer = in.nextInt();
		switch (answer) {
		case 1:
			tipo= DevelopmentCardType.territory;
			break;

		case 2:
			tipo= DevelopmentCardType.building;
			break;
			
		case 3:
			tipo= DevelopmentCardType.character;
			break;
		
		case 4:
			tipo= DevelopmentCardType.venture;
			break;
		}
		return new ResourcePerDevelopmentCardBonus(resources, tipo);
	}
	
	private static ArrayList<Resource> createArrayResource(){
		boolean vuoleCreareUnAltraRisorsa = true;
		String risposta;
		ArrayList<Resource> resources = new ArrayList<>();
		
		while(vuoleCreareUnAltraRisorsa){
			resources.add(createResource());
			System.out.println("vuoi aggiungere un'altra risorsa?(si/no)");
			risposta = in2.nextLine();
			if (!("si".equals(risposta))) vuoleCreareUnAltraRisorsa=false;
		}
		return resources;
	}
	
	private static Resource createResource(){
		Resource risorsa = null;
		System.out.println("Quale risorsa vuoi creare? ");
		System.out.println("1) Soldi");
		System.out.println("2) Punti fede");
		System.out.println("3) Punti militari");
		System.out.println("4) Serventi");
		System.out.println("5) Pietre");
		System.out.println("6) Punti Vittoria");
		System.out.println("7) Legno");
		
		int answer = in.nextInt();
		switch (answer) {
		case 1:
			risorsa = createCoins();
			break;
		
		case 2:
			risorsa = createFaithPoints();
			break;
			
		case 3:
			risorsa = createMilitaryPoints();
			break; 
			
		case 4:
			risorsa = createServants();
			break;
			
		case 5:
			risorsa = createStones();
			break;
			
		case 6:
			risorsa = createVictoryPoints();
			break;
			
		case 7:
			risorsa = createWood();
			break;
		}
		return risorsa;
	}
	
	private static int createResourceValues(){
		int values;
		
		System.out.println("Quale è l'amount?");
		values = in.nextInt();
		return values;
	}
	
	private static Coins createCoins(){
		return new Coins(createResourceValues(), 1);
	}
	
	private static FaithPoints createFaithPoints(){
		return new FaithPoints(createResourceValues(), 1);
	}
	
	private static MilitaryPoints createMilitaryPoints(){
		return new MilitaryPoints(createResourceValues(), 1);
	}
	
	private static Servants createServants(){
		return new Servants(createResourceValues(), 1);
	}
	
	private static Stones createStones(){
		return new Stones(createResourceValues(), 1);
	}
	
	private static VictoryPoints createVictoryPoints(){
		return new VictoryPoints(createResourceValues(), 1);
	}
	
	private static Wood createWood(){
		return new Wood(createResourceValues(), 1);
	}
	
	public static ActionBonus createActionBonus(){
		HashMap<ActionZone, Integer> action = new HashMap<>();
		ArrayList<Resource> resources = new ArrayList<>();
		boolean vuoleAltraZona = true;
		String risposta;
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
				zone = new Tower(DevelopmentCardType.territory);
				break;
	
			case 2:
				zone = new Tower(DevelopmentCardType.building);//first 2 parameters are random
				break;
			
			case 3:
				zone = new Tower(DevelopmentCardType.character);
				break;
				
			case 4:
				zone = new Tower(DevelopmentCardType.venture);
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
			risposta = in2.nextLine();
			if (!("si".equals(risposta))) vuoleAltraZona=false;
		}
		
		System.out.println("Vi è un bonus di risorse associato all'action bonus?");
		String risp = in2.nextLine();
		
		if("si".equals(risp)){
			resources= createArrayResource();
		}else{
			resources = null;
		}
		
		return new ActionBonus(action, resources);
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
		String risposta;
		ArrayList<FamilyMember> familyMembers = new ArrayList<>();
		while (vuoleCreareAltroFamilyMember){
			familyMembers.add(createFamilyMember());
			System.out.println("vuoi aggiungere un altro family member?(si/no)");
			risposta = in2.nextLine();
			if (!("si".equals(risposta))) vuoleCreareAltroFamilyMember=false;
		}
		return familyMembers;
	}
	
	private static FamilyMember createFamilyMember(){
		int answer;
		Dice dice = null;
		
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
