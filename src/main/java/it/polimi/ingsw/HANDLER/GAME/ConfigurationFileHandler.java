package it.polimi.ingsw.HANDLER.GAME;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.HarvestArea;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.BOARD.Tower;
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
		File file = new File("prova.txt");
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
		Character data = toDeserialize(inJson);*/
		
		HashMap<Integer, Character> prova = new HashMap<>();
		Character ch1 = new Character("marco", null, 3, null, null);
		prova.put(3, ch1);
		System.out.println(toSerialize(prova));
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
	
	private static ActionBonus toDeserialize(String inJson){
		try{
			//Type requestListTypeToken = new TypeToken<List<Territory>>() {}.getType();
			final RuntimeTypeAdapterFactory<ActionZone> t6 = RuntimeTypeAdapterFactory
					.of(ActionZone.class, "type")
			        .registerSubtype(Tower.class, "tower")
			        .registerSubtype(ProductionArea.class, "productionArea")
			        .registerSubtype(HarvestArea.class, "harvestArea");
			
			
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
					.registerTypeAdapterFactory(t6)
					.create();
			
			return gsonToDeserialize.fromJson(inJson, ActionBonus.class);
			
		} catch (Exception e){
			e.printStackTrace();
			//return new DataFromFile(null, null, null, null, null, null, null, null, null, null, null, null);
			return new ActionBonus(null);
		}
	}
}
