package it.polimi.ingsw.HANDLER.GAME;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import it.polimi.ingsw.BONUS.AddResourceBonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.Card;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.Wood;

public class ConfigurationFileHandler {
	public static void main(String[] args){
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
		}*/
				

		//To create a card
		ArrayList<Resource> resources = new ArrayList<>();
		Coins coins = new Coins(5, 5);
		Wood wood= new Wood(3, 2);
		resources.add(coins);
		resources.add(wood);
		ArrayList<ImmediateBonus> immediateBonus = new ArrayList<>();
		AddResourceBonus singleBonus = new AddResourceBonus(resources);
		immediateBonus.add(singleBonus);
		Territory territory = new Territory("marco", 1, immediateBonus, null);
		ArrayList<Territory> territoryCards = new ArrayList<>();
		territoryCards.add(territory);
	}
	
	private String toSerialize(Object object){
		try{
			Gson gsonToSerialize = new Gson();
			return gsonToSerialize.toJson(object);
		} catch(Exception e) {
			e.printStackTrace();
			return "error"; //TODO da vedere cosa ritornare
		}
	}
	
	private Object toDeserialize(String inJson){
		try{
			Type requestListTypeToken = new TypeToken<List<Territory>>() {}.getType();
			
			final RuntimeTypeAdapterFactory<ResourceBonus> typeFactory = RuntimeTypeAdapterFactory
					.of(ResourceBonus.class, "subtype")
			        .registerSubtype(AddResourceBonus.class, "addResourceBonus");
			
			final RuntimeTypeAdapterFactory<Resource> typeFactory1 = RuntimeTypeAdapterFactory
					.of(Resource.class, "resourceType")
			        .registerSubtype(Coins.class, "COINS")
			        .registerSubtype(Wood.class, "WOOD");
			
			final RuntimeTypeAdapterFactory<ImmediateBonus> typeFactory2 = RuntimeTypeAdapterFactory
					.of(ImmediateBonus.class, "subtype1")
			        .registerSubtype(ResourceBonus.class, "resourceBonus");
			
			Gson gsonToDeserialize = new GsonBuilder().registerTypeAdapterFactory(typeFactory)
					.registerTypeAdapterFactory(typeFactory1)
					.registerTypeAdapterFactory(typeFactory2)
					.create();
			
			return gsonToDeserialize.fromJson(inJson, requestListTypeToken);
		} catch (Exception e){
			e.printStackTrace();
			return new Object();//TODO da vedere cosa ritornare
		}
	}
}
