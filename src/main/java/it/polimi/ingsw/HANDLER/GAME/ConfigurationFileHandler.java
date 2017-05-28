package it.polimi.ingsw.HANDLER.GAME;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import it.polimi.ingsw.BONUS.ActionBonus;
import it.polimi.ingsw.BONUS.AddFamilyMemberBonus;
import it.polimi.ingsw.BONUS.AddResourceBonus;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.CouncilPrivilegeBonus;
import it.polimi.ingsw.BONUS.FamilyMemberBonus;
import it.polimi.ingsw.BONUS.FamilyMemberValueBonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.MultiplyFamilyMemberBonus;
import it.polimi.ingsw.BONUS.MultiplyResourceBonus;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.BONUS.ResourcePerDevelopmentCardBonus;
import it.polimi.ingsw.BONUS.ResourceValueBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.Card;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.CARD.Venture;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.FaithPoints;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.Servants;
import it.polimi.ingsw.RESOURCE.Stones;
import it.polimi.ingsw.RESOURCE.VictoryPoints;
import it.polimi.ingsw.RESOURCE.Wood;

public class ConfigurationFileHandler {
	public static void main(String[] args) throws FileNotFoundException{
		/*TO READ FROM FILE
		File file = new File("config.json");
		Scanner scanner= new Scanner(file);
		String inJson="";
		//In this way I can use spaces between characters
		try{
			while (scanner.hasNextLine()){
				inJson = inJson + scanner.nextLine();
			}
		}
		finally{
			scanner.close();
		}
		DataFromFile data = toDeserialize(inJson);*/
		
		ArrayList<Territory> territories = new ArrayList<>();
		ArrayList<Character> characters = new ArrayList<>();
		ArrayList<Venture> ventures = new ArrayList<>();
		ArrayList<Building> buildings = new ArrayList<>();
		ArrayList<ExcommunicationTile> excommunicationTiles = new ArrayList<>();
		ArrayList<PersonalBonusTile> personalBonusTiles = new ArrayList<>();
		ArrayList<ResourceBonus> councilPrivileges = new ArrayList<>();
		int[] fromFaithPointsToVictoryPoints = new int[15]; //tracciato punti fede
		//in first position: tot victoryPoints to first player, second position tot to second player and so on
		int[] fromMilitaryPointsToVictoryPoints= new int[2];  
		//punti vittoria in base al numero di carte di un certo tipo
		int[] victoryPointsForTerritoryCard= new int[6];
		int[] victoryPointsForMilitaryCard = new int[6];
		//military points requirement for territory card
		int[] militaryRequirement = new int[6];	
		
		for(int i= 0; i < 2/*21*/; i++){
			System.out.println("inserire una carta scomunica: ");
			excommunicationTiles.add(Create.createExcommunicationTile());
		}
		
		for(int i=0; i < 2/*5*/; i++){
			System.out.println(
					"inserire le personal bonus tile (NB: ti verrà chiesto 5 volte (chiedi a michele))");
			personalBonusTiles.add(Create.createPersonalBonusTile());
		}
		
		for(int i=0; i<5; i++){
			System.out.println("inserisci il bonus che ricevi dal privilegio del consiglio: ");
			councilPrivileges.add(Create.createResourceBonus());
		}
		
		System.out.println("valore dei punti vittoria per tot punti fede:");
		fromFaithPointsToVictoryPoints = Create.genericArray();
		
		System.out.println("valore dei punti vittoria per tot punti militari:");
		fromMilitaryPointsToVictoryPoints = Create.genericArray();
		
		System.out.println("punti vittoria per tot carte territorio:");
		victoryPointsForTerritoryCard = Create.genericArray();
		
		System.out.println("valore dei punti vittoria per tot carte militari:");
		victoryPointsForMilitaryCard = Create.genericArray();
		
		System.out.println("quanti punti militari per carta territorio:");
		militaryRequirement = Create.genericArray();
		
		for(int i= 0; i < 2/*24*/; i++){
			System.out.println("creare la prossima carta territorio: ");
			territories.add(Create.createTerritory());
		}
		for(int i= 0; i < 2/*24*/; i++){
			System.out.println("creare la prossima carta edificio: ");
			buildings.add(Create.createBuilding());
		}
		for(int i= 0; i < 2/*24*/; i++){
			System.out.println("creare la prossima carta azione: ");
			ventures.add(Create.createVenture());
		}
		for(int i= 0; i < 2/*24*/; i++){
			System.out.println("creare la prossima carta personaggio: ");
			characters.add(Create.createCharacter());
		}
		
		DataFromFile data = new DataFromFile(territories, characters, ventures, buildings, excommunicationTiles, personalBonusTiles, councilPrivileges, fromFaithPointsToVictoryPoints, fromMilitaryPointsToVictoryPoints, victoryPointsForTerritoryCard, victoryPointsForMilitaryCard, militaryRequirement);
		System.out.println(toSerialize(data));
		
		//scrivi su file!!!
		FileOutputStream prova;
		try {
			prova = new FileOutputStream("config.json");
			PrintStream scrivi = new PrintStream(prova);
			scrivi.print(toSerialize(data));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String toSerialize(Object object){
		try{
			Gson gsonToSerialize = new Gson();
			return gsonToSerialize.toJson(object);
		} catch(Exception e) {
			e.printStackTrace();
			return "error"; //TODO da vedere cosa ritornare
		}
	}
	
	private static DataFromFile toDeserialize(String inJson){
		try{
			//Type requestListTypeToken = new TypeToken<List<Territory>>() {}.getType();
			
			final RuntimeTypeAdapterFactory<ResourceBonus> t1 = RuntimeTypeAdapterFactory
					.of(ResourceBonus.class, "subtype")
			        .registerSubtype(AddResourceBonus.class, "addResourceBonus")
			        .registerSubtype(MultiplyResourceBonus.class, "multiplyResourceBonus")
			        .registerSubtype(ResourcePerDevelopmentCardBonus.class, "ATTENTION")//TODO
			        .registerSubtype(ResourceValueBonus.class, "resourceValueBonus");
			
			final RuntimeTypeAdapterFactory<Resource> t2 = RuntimeTypeAdapterFactory
					.of(Resource.class, "resourceType")
			        .registerSubtype(Coins.class, "coins")
			        .registerSubtype(Wood.class, "wood")
			        .registerSubtype(FaithPoints.class, "faithPoints")
			        .registerSubtype(MilitaryPoints.class, "militaryPoints")
			        .registerSubtype(Servants.class, "servants")
			        .registerSubtype(Stones.class, "stones")
			        .registerSubtype(VictoryPoints.class, "victoryPoints");
			        
			final RuntimeTypeAdapterFactory<ImmediateBonus> t3 = RuntimeTypeAdapterFactory
					.of(ImmediateBonus.class, "type")
			        .registerSubtype(ResourceBonus.class, "resourceBonus")
			        .registerSubtype(ActionBonus.class, "actionBonus")
			        .registerSubtype(CouncilPrivilegeBonus.class, "councilPrivilegeBonus")
			        .registerSubtype(FamilyMemberBonus.class, "familyMemberBonus");
			
			final RuntimeTypeAdapterFactory<FamilyMemberBonus> t4 = RuntimeTypeAdapterFactory
					.of(FamilyMemberBonus.class, "subtype")
			        .registerSubtype(AddFamilyMemberBonus.class, "addFamilyMemberBonus")
			        .registerSubtype(MultiplyFamilyMemberBonus.class, "multiplyFamilyMemberBonus")
			        .registerSubtype(FamilyMemberValueBonus.class, "familyMemberValueBonus");
			
			final RuntimeTypeAdapterFactory<Bonus> t5 = RuntimeTypeAdapterFactory
					.of(Bonus.class, "type1")
			        .registerSubtype(ImmediateBonus.class, "immediateBonus");
			
			Gson gsonToDeserialize = new GsonBuilder().registerTypeAdapterFactory(t1)
					.registerTypeAdapterFactory(t2)
					.registerTypeAdapterFactory(t3)
					.registerTypeAdapterFactory(t4)
					.registerTypeAdapterFactory(t5)
					.create();
			
			return gsonToDeserialize.fromJson(inJson, DataFromFile.class);
			
		} catch (Exception e){
			e.printStackTrace();
			return new DataFromFile(null, null, null, null, null, null, null, null, null, null, null, null);
		}
	}
}
