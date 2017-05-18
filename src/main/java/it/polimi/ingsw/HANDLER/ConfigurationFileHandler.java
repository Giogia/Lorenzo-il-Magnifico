package it.polimi.ingsw.HANDLER;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import it.polimi.ingsw.CARD.Territory;

public class ConfigurationFileHandler {
	public static void main(String[] args){
		try {
			Territory territorio1 = new Territory(1, 7, new ArrayList<>(), new ArrayList<>());
			Territory territorio2 = new Territory(4, 9, new ArrayList<>(), new ArrayList<>());
			ArrayList<Territory> prova= new ArrayList<>();
			prova.add(territorio1);
			prova.add(territorio2);
			
			Gson gson= new Gson();
			
			String inJson = gson.toJson(prova);
			System.out.println(inJson);
			
			Type list= new TypeToken<ArrayList<Territory>>(){}.getType();
			ArrayList<Territory> territori = gson.fromJson(inJson, list);
			System.out.println(territori.get(0).period);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
