package it.polimi.ingsw.HANDLER.GAME;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import it.polimi.ingsw.BOARD.*;
import it.polimi.ingsw.BONUS.*;
import it.polimi.ingsw.BONUS.ADVANCED.ActivationZoneBonus;
import it.polimi.ingsw.BONUS.ADVANCED.AddCardCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.CanFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.CopyBonus;
import it.polimi.ingsw.BONUS.ADVANCED.EndGameCardBonus;
import it.polimi.ingsw.BONUS.ADVANCED.LoseVictoryPointsPerCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.LoseVictoryPointsPerResourceBonus;
import it.polimi.ingsw.BONUS.ADVANCED.MultiplyCardCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.OccupiedTowerCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.OrderBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentAddFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentAddResourceBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentMultFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentMultResourceBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentValueFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PositionFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.ResourcePerMissedExcommunicationBonus;
import it.polimi.ingsw.BONUS.ADVANCED.TerritoryCardRequirementBonus;
import it.polimi.ingsw.CARD.*;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.GC_15.*;
import it.polimi.ingsw.RESOURCE.*;

public class Create {
	private static Scanner in = new Scanner(System.in);
	private static Scanner in2 = new Scanner(System.in);
	
	public static int[] genericArray(){
		System.out.println("which size should the array have?");
		int answer = in.nextInt();
		int[] generic = new int[answer];
		for(int i=0; i < answer; i++){
			System.out.println("Insert the size number (int): ");
			generic[i]= in.nextInt();
		}
		return generic;
	}
	
	public static ArrayList<ArrayList<ImmediateBonus>> genericArrayListArrayList(int numOfPosition){
		ArrayList<ArrayList<ImmediateBonus>> a = new ArrayList<ArrayList<ImmediateBonus>>();
		for (int i = 0; i< numOfPosition; i++){
			System.out.println("------------nuova posizione--------------");
			ArrayList<ImmediateBonus> a2 = createArrayImmediateBonus();
			a.add(a2);
		}
		return a;
	}
	
	public static ArrayList<ResourceBonus> createCouncilPrivileges(){
		boolean vuoleUnAltroBonus=true;
		String risposta;
		ArrayList<ResourceBonus> councilPrivileges = new ArrayList<>();
		while (vuoleUnAltroBonus){
			councilPrivileges.add(createResourceBonus());
			System.out.println("Want to add another bonus?(y/n)");
			risposta = in2.nextLine();
			if (!("y".equals(risposta))) vuoleUnAltroBonus=false;
		}
		return councilPrivileges;
	}
	
	public static PersonalBonusTile createPersonalBonusTile(){
		System.out.println("Insert the harvest activation condition: ");
		int harvestActivationCondition = in.nextInt();
		System.out.println("Insert the production activation condition: ");
		int productionActivationCondition = in.nextInt();
		System.out.println("Insert the harvest bonus: ");
		ImmediateBonus harvestBonus = createImmediateBonus();
		System.out.println("Insert the production bonus: ");
		ImmediateBonus productionBonus = createImmediateBonus();
		return new PersonalBonusTile(harvestBonus, productionBonus, harvestActivationCondition, productionActivationCondition);
	}
		
	public static Territory createTerritory(){
		System.out.println("Insert name: ");
		String nome = in2.nextLine();
		System.out.println("Insert period: ");
		int periodo = in.nextInt();
		System.out.println("Insert the harvest activation condition: ");
		int condizioneAttivazione= in.nextInt();
		ArrayList<ImmediateBonus> immediateEffects = createArrayImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createArrayBonus();
		return new Territory(nome, condizioneAttivazione, periodo, immediateEffects, secondaryEffects);
	}
	
	public static Building createBuilding(){
		System.out.println("Inserisci name: ");
		String nome = in2.nextLine();
		System.out.println("Insert period: ");
		int periodo = in.nextInt();
		System.out.println("Insert the production activation condition: ");
		int condizioneDiAttivazione = in.nextInt();
		System.out.println("Insert card costs");
		ArrayList<Resource> costs = createArrayResource();
		ArrayList<ImmediateBonus> immediateEffects = createArrayImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createArrayBonus();
		return new Building(nome, condizioneDiAttivazione, costs, periodo, immediateEffects, secondaryEffects);
	}
		
	public static Character createCharacter(){
		System.out.println("Insert name: ");
		String nome = in2.nextLine();
		Coins cost = createCoins();
		System.out.println("Insert period: ");
		int periodo = in.nextInt();
		ArrayList<ImmediateBonus> immediateEffects = createArrayImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createArrayBonus();
		return new Character(nome, cost, periodo, immediateEffects, secondaryEffects);
	}
	
	private static ArrayList<Bonus> createArrayBonus() {
		System.out.println("La carta ha bonus permanenti?(si/ no)");
		String r = in2.nextLine();
		if (r.equals("no")){
			return null;
		}
		boolean bool= true;
		String risposta;
		ArrayList<Bonus> bonuses = new ArrayList<>();
		while(bool){
			bonuses.add(createBonus());
			System.out.println("vuoi aggiungere un altro bonus? (si/no)");
			risposta = in2.nextLine();
			if(risposta.equals("no")){
				bool = false;
			}
		}
		return bonuses;
	}

	public static Venture createVenture(){
		System.out.println("Insert name: ");
		String nome = in2.nextLine();
		System.out.println("Insert period: ");
		int periodo = in.nextInt();
		ArrayList<ImmediateBonus> immediateEffects = createArrayImmediateBonus();
		ArrayList<Bonus> secondaryEffects = createArrayBonus();
		System.out.println("Insert military points requirement (if null insert 0)");
		int requirement = in.nextInt();
		System.out.println("Insert card costs");
		ArrayList<Resource> costs = createArrayResource();
		System.out.println("Does the card have an alternative cost?( si / no)");
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
		System.out.println("Insert period: ");
		int periodo = in.nextInt();
		//Bonus[] malus = createArrayMalus();TODO REGOLE AVANZATE
		Bonus[] malus = null;
		return new ExcommunicationTile(periodo, malus);
	}
	
	public static ArrayList<ImmediateBonus> createArrayImmediateBonus(){
		boolean vuoleUnAltroBonus=true;
		String risposta;
		ArrayList<ImmediateBonus> bonusScelti=new ArrayList<>();
		System.out.println("Does the card have an immediate bonus? (y/n)");
		String risp = in2.nextLine();
		if ("n".equals(risp)){//se la carta non ha un bonus immediato ritorno un null
			return null;
		}
		while (vuoleUnAltroBonus){
			ImmediateBonus bonus= createImmediateBonus();
			bonusScelti.add(bonus);
			System.out.println("Do you want to add another bonus? (y/n)");
			risposta = in2.nextLine();
			if (!("y".equals(risposta))) vuoleUnAltroBonus=false;
		}
		return bonusScelti;
	}
	
	private static Bonus createBonus(){
		Bonus bonus = null;
		System.out.println("Which typer of bonus should it be?");
		System.out.println("1) Action Bonus");
		System.out.println("2) Add Family Member Bonus");
		System.out.println("3) Multiply Family Member Bonus");
		System.out.println("4) Family Member Value Bonus");
		System.out.println("5) Resource Per DevelopmentCard Bonus");
		System.out.println("6) Copy Bonus");
		System.out.println("7) Add Resource Bonus");
		System.out.println("8) Multiply Resource Bonus");
		System.out.println("9) Council Privilege Bonus");
		System.out.println("10) Resource Per Resource");
		
		System.out.println("----PERMA------");
		System.out.println("11) ActivationZoneBonus");
		System.out.println("12) Add CardCostBonus");
		System.out.println("13) Can Family Member Bonus");
		System.out.println("14) End Game Card Bonus");
		System.out.println("15) Lose Victory Points per cost bonus");
		System.out.println("16) Lose victory points per resource bonus");
		System.out.println("17) multiply card cost bonus");
		System.out.println("18) occupied tower cost bonus");
		System.out.println("19) order bonus");
		System.out.println("20) permanent add family member bonus");
		System.out.println("21) permanent add resource bonus");
		System.out.println("22) permanent mult family member bonus");
		System.out.println("23) permanent mult resource bonus");
		System.out.println("24) permanent value family member bonus");
		System.out.println("25) position family member bonus");
		System.out.println("26) resource per missed excommunication bonus");
		System.out.println("27) territory card requirement bonus");
		System.out.println("28) ResourceValueBonus");
		
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
			bonus = createCopyBonus();
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
			break;
		
		case 11:
			bonus = createActivationZoneBonus();
			break;
			
		case 12:
			bonus = createAddCardCostBonus();
			break;
			
		case 13:
			bonus = createCanFamilyMemberBonus();
			break;
			
		case 14:
			bonus= createEdGameCardBonus();
			break;
			
		case 15:
			bonus= createLoseVictoryPointsPerCostBonus();
			break;
		
		case 16:
			bonus= createLoseVictoryPointsPerResourceBonus();
			break;
			
		case 17:
			bonus= createMultiplyCardCostBonus();
			break;
			
		case 18:
			bonus= createOccupiedTowerCostBonus();
			break;
			
		case 19:
			bonus= createOrderBonus();
			break;
			
		case 20:
			bonus= createPermanentAddFamilyMemberBonus();
			break;
			
		case 21:
			bonus = createPermanentAddResourceBonus();
			break;
			
		case 22:
			bonus = createPermanentMultFamilyMemberBonus();
			break;
			
		case 23:
			bonus = createPermanentMultResourceBonus();
			break;
			
		case 24:
			bonus = createPermanentValueFamilyMemberBonus();
			break;
			
		case 25:
			bonus = createPositionFamilyMemberBonus();
			break;
			
		case 26:
			bonus= createResourcePerMissedExcommunicationBonus();
			break;
			
		case 27:
			bonus= createTerritoryCardRequirementBonus();
			break;
			
		case 28:
			bonus= createResourceValueBonus();
		}
		
		return bonus;
	}
	
	private static Bonus createCopyBonus() {
		return new CopyBonus();
	}

	private static Bonus createTerritoryCardRequirementBonus() {
		boolean needRequirement = false;
		return new TerritoryCardRequirementBonus(needRequirement);
	}

	private static Bonus createResourcePerMissedExcommunicationBonus() {
		return new ResourcePerMissedExcommunicationBonus(createArrayResource());
	}

	private static Bonus createPositionFamilyMemberBonus() {
		return new PositionFamilyMemberBonus(createHashmapActionZoneInteger());
	}
	
	public static HashMap<ActionZone, Integer> createHashmapActionZoneInteger(){
		HashMap<ActionZone, Integer> hashmap = new HashMap<>();
		String risp;
		boolean bool = true;
		while(bool){
			ActionZone zona = null;
			Integer i;
			System.out.println("in quale zona deve esserci il bonus?");
			System.out.println("1) Torre verde");
			System.out.println("2) Torre blu");
			System.out.println("3) Torre gialla");
			System.out.println("4) Torre viola");
			System.out.println("5) Harvest");
			System.out.println("6) Production");
			
			int risposta = in.nextInt();
			switch (risposta) {
			case 1:
				zona = new Tower(DevelopmentCardType.territory);
				break;

			case 2:
				zona = new Tower(DevelopmentCardType.character);
				break;
				
			case 3:
				zona = new Tower(DevelopmentCardType.building);
				break;
			
			case 4:
				zona = new Tower(DevelopmentCardType.venture);
				break;
				
			case 5:
				zona = new HarvestArea();
				break;
			
			case 6: 
				zona = new ProductionArea();
				break;
			}
			System.out.println("Di quanto vuoi aumentare il familiare?");
			i = in.nextInt();
			hashmap.put(zona, i);
			System.out.println("vuoi inserire un'altra zona?(si / no)");
			risp = in2.nextLine();
			if (risp.equals("si")){
				bool=true;
			}else{
				bool=false;
			}
		}
		return hashmap;
	}

	private static Bonus createPermanentValueFamilyMemberBonus() {
		return new PermanentValueFamilyMemberBonus(createFamilyMembers());
	}

	private static Bonus createPermanentMultResourceBonus() {
		return new PermanentMultResourceBonus(createArrayResource());
	}

	private static Bonus createPermanentMultFamilyMemberBonus() {
		return new PermanentMultFamilyMemberBonus(createFamilyMembers());
	}

	private static Bonus createPermanentAddResourceBonus() {
		return new PermanentAddResourceBonus(createArrayResource());
	}

	private static Bonus createPermanentAddFamilyMemberBonus() {
		return new PermanentAddFamilyMemberBonus(createFamilyMembers());
	}

	private static Bonus createOrderBonus() {
		boolean[] skipAction = new boolean[4];
		String risposta = in2.nextLine();
		for (int i=0 ; i< 4; i++){
			System.out.println("vuoi saltare "+ i+" turno? si/no");
			if (risposta.equals("si")){
				skipAction[i]=true;
			}else{
				skipAction[i]=false;
			}
		}
		return new OrderBonus(skipAction);
	}

	private static Bonus createOccupiedTowerCostBonus() {
		int addOccupiedCost;
		int multOccupiedCost;
		System.out.println("Di quanto vuoi aumentare il costo della torre occupata?");
		addOccupiedCost= in.nextInt();
		System.out.println("Di quanto vuoi moltiplicare il costo della torre occupata?");
		multOccupiedCost = in.nextInt();
		return new OccupiedTowerCostBonus(addOccupiedCost, multOccupiedCost);
	}

	private static Bonus createMultiplyCardCostBonus() {
		return new MultiplyCardCostBonus(createDevelopmentCardType(), createArrayResource());
		
	}

	private static DevelopmentCardType createDevelopmentCardType() {
		System.out.println("Quale development card type scegli? ");
		System.out.println("1) territorio");
		System.out.println("2) personaggio");
		System.out.println("3) edeifici");
		System.out.println("4) imprese");
		int risposta = in.nextInt();
		
		switch (risposta) {
		case 1:
			return DevelopmentCardType.territory;

		case 2:
			return DevelopmentCardType.character;
			
		case 3:
			return DevelopmentCardType.building;
			
		case 4:
			return DevelopmentCardType.venture;
		}
		return null;
	}

	private static Bonus createLoseVictoryPointsPerResourceBonus() {
		return new LoseVictoryPointsPerResourceBonus(createArrayResource());
	}

	private static Bonus createLoseVictoryPointsPerCostBonus() {
		return new LoseVictoryPointsPerCostBonus(createDevelopmentCardType(), createArrayResource());
	}

	private static Bonus createEdGameCardBonus() {
		return new EndGameCardBonus(createHashmapDevelopmentCardTypeBoolean());
	}

	private static HashMap<DevelopmentCardType, Boolean> createHashmapDevelopmentCardTypeBoolean() {
		HashMap<DevelopmentCardType, Boolean> hashmap = new HashMap<>();
		String risp;
		boolean bool = true;
		while(bool){
			DevelopmentCardType type = null;
			String i;
			System.out.println("Quale development Card vuoi??");
			System.out.println("1) Territorio");
			System.out.println("2) Personaggio");
			System.out.println("3) Edificio");
			System.out.println("4) Impresa");
			
			int risposta = in.nextInt();
			switch (risposta) {
			case 1:
				type = DevelopmentCardType.territory;
				break;

			case 2:
				type = DevelopmentCardType.character;
				break;
				
			case 3:
				type = DevelopmentCardType.building;
				break;
			
			case 4:
				type = DevelopmentCardType.venture;
				break;
			}
			System.out.println("puoi prendere il bonus della posizione?");
			i = in2.nextLine();
			Boolean bolean = false;
			if (i.equals("si")){
				bolean = true;
			}
			hashmap.put(type, bolean);
			System.out.println("vuoi inserire un'altra development card type?(si / no)");
			risp = in2.nextLine();
			if (risp.equals("si")){
				bool=true;
			}else{
				bool=false;
			}
		}
		return hashmap;
	}

	private static Bonus createCanFamilyMemberBonus() {
		String risposta = in2.nextLine();
		boolean occupiedYet;
		System.out.println("vuoi andare in un posto occupato?");
		if (risposta.equals("si")){
			occupiedYet = true;
		}else{
			occupiedYet =false;
		}
		HashMap<Zone, Boolean> canGoTo = createHashmapZoneBoolean();
		return new CanFamilyMemberBonus(occupiedYet, canGoTo);
	}

	private static HashMap<Zone, Boolean> createHashmapZoneBoolean() {
		HashMap<Zone, Boolean> hashmap = new HashMap<>();
		String risp;
		boolean bool = true;
		while(bool){
			Zone zona = null;
			String i;
			System.out.println("in quale zona deve esserci il bonus?");
			System.out.println("1) Torre verde");
			System.out.println("2) Torre blu");
			System.out.println("3) Torre gialla");
			System.out.println("4) Torre viola");
			System.out.println("5) Harvest");
			System.out.println("6) Production");
			System.out.println("7) Council Palace");
			System.out.println("8) Market");
			
			int risposta = in.nextInt();
			switch (risposta) {
			case 1:
				zona = new Tower(DevelopmentCardType.territory);
				break;

			case 2:
				zona = new Tower(DevelopmentCardType.character);
				break;
				
			case 3:
				zona = new Tower(DevelopmentCardType.building);
				break;
			
			case 4:
				zona = new Tower(DevelopmentCardType.venture);
				break;
				
			case 5:
				zona = new HarvestArea();
				break;
			
			case 6: 
				zona = new ProductionArea();
				break;
				
			case 7:
				zona = new CouncilPalace();
				break;
				
			case 8:
				zona = new Market();
				break;
			}
			System.out.println("Il familiare può andarci?");
			i = in2.nextLine();
			Boolean bolean = false;
			if (i.equals("si")){
				bolean = true;
			}
			hashmap.put(zona, bolean);
			System.out.println("vuoi inserire un'altra zona?(si / no)");
			risp = in2.nextLine();
			if (risp.equals("si")){
				bool=true;
			}else{
				bool=false;
			}
		}
		return hashmap;
	}

	private static Bonus createAddCardCostBonus() {
		return new AddCardCostBonus(createDevelopmentCardType(), createArrayResource());
	}

	private static Bonus createActivationZoneBonus() {
		String risposta = in2.nextLine();
		boolean councilPalace;
		System.out.println("vuoi prendere i bonus del council palace??");
		if (risposta.equals("si")){
			councilPalace = true;
		}else{
			councilPalace =false;
		}
		return new ActivationZoneBonus(createHashmapTowerBoolean(), councilPalace);
	}

	
	
	private static HashMap<Tower, Boolean> createHashmapTowerBoolean() {
		HashMap<Tower, Boolean> hashmap = new HashMap<>();
		String risp;
		boolean bool = true;
		while(bool){
			Tower tower = null;
			String i;
			System.out.println("in quale zona deve esserci il bonus?");
			System.out.println("1) Torre verde");
			System.out.println("2) Torre blu");
			System.out.println("3) Torre gialla");
			System.out.println("4) Torre viola");
			
			int risposta = in.nextInt();
			switch (risposta) {
			case 1:
				tower = new Tower(DevelopmentCardType.territory);
				break;

			case 2:
				tower = new Tower(DevelopmentCardType.character);
				break;
				
			case 3:
				tower = new Tower(DevelopmentCardType.building);
				break;
			
			case 4:
				tower = new Tower(DevelopmentCardType.venture);
				break;
			
			}
			System.out.println("Il familiare può andarci?");
			i = in2.nextLine();
			Boolean bolean = false;
			if (i.equals("si")){
				bolean = true;
			}
			hashmap.put(tower, bolean);
			System.out.println("vuoi inserire un'altra zona?(si / no)");
			risp = in2.nextLine();
			if (risp.equals("si")){
				bool=true;
			}else{
				bool=false;
			}
		}
		return hashmap;
	}

	public static ImmediateBonus createImmediateBonus(){
		ImmediateBonus bonus = null;
		System.out.println("Which typer of bonus should it be?");
		System.out.println("1) Action Bonus");
		System.out.println("2) Add Family Member Bonus");
		System.out.println("3) Multiply Family Member Bonus");
		System.out.println("4) Family Member Value Bonus");
		System.out.println("5) Resource Per DevelopmentCard Bonus");
		System.out.println("6) Resource Value Bonus");
		System.out.println("7) Add Resource Bonus");
		System.out.println("8) Multiply Resource Bonus");
		System.out.println("9) Council Privilege Bonus");
		System.out.println("10) Resource Per Resource");
		
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
			
//		case 6:
	//		bonus = createResourceValueBonus();
		//	break;
		
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
			break;
		}
		return bonus;
		
	}
	
	private static ResourcePerResourceBonus createResourcePerResourceBonus(){
		System.out.println("Insert the resource that this bonus gives you: ");
		ArrayList<Resource> resources = createArrayResource();
		System.out.println("Insert which resourse does the bonus require: ");
		Resource requirement = createResource();
		return new ResourcePerResourceBonus(resources, requirement);
	}
	
	private static CouncilPrivilegeBonus createCouncilPrivilegeBonus(){
		System.out.println("How many different council privileges should the bonus have? ");
		int value = in.nextInt();
		
		return new CouncilPrivilegeBonus(value);
	}
	
	public static ArrayList<ResourceBonus> createArrayResourceBonus(){
		ArrayList<ResourceBonus> arrayResourceBonus = new ArrayList<>();
		String risposta;
		boolean vuoleUnAltroBonus = true;
		
		while (vuoleUnAltroBonus){
			arrayResourceBonus.add(createResourceBonus());
			System.out.println("Do you want to add another bonus? (y/n)");
			risposta = in2.nextLine();
			if (!("si".equals(risposta))) vuoleUnAltroBonus=false;
		}
		return arrayResourceBonus;
	}
	
	public static ResourceBonus createResourceBonus(){
		ResourceBonus resourceBonus = null;
		
		System.out.println("Which bonus do you want to create? ");
		System.out.println("1) Add Resource Bonus");
		System.out.println("2) Multiply Resource Bonus");
		System.out.println("3) Resource Value Bonus");
		System.out.println("4) Resource Per Development CardBonus");
		
		int answer= in.nextInt();
		switch (answer) {
		case 1:
			resourceBonus = createAddResourceBonus();
			break;

		case 2:
			resourceBonus = createMultiplyResourceBonus();
			break;
		
//		case 3:
	//		resourceBonus = createResourceValueBonus();
		//	break;
			
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
		
		System.out.println("Choos the card type: ");
		System.out.println("1) territory");
		System.out.println("2) builing");
		System.out.println("3) character");
		System.out.println("4) venture");
		
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
			System.out.println("Do you want to add another resource? (y/n)");
			risposta = in2.nextLine();
			if (!("y".equals(risposta))) vuoleCreareUnAltraRisorsa=false;
		}
		return resources;
	}
	
	private static Resource createResource(){
		Resource risorsa = null;
		System.out.println("Which resource do you want to create? ");
		System.out.println("1) Coins");
		System.out.println("2) Faith Points");
		System.out.println("3) Military Points");
		System.out.println("4) Servants");
		System.out.println("5) Stones");
		System.out.println("6) Victory Points");
		System.out.println("7) Wood");
		
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
		
		System.out.println("Which is the amount?");
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
