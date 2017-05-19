package it.polimi.ingsw.HANDLER;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.RESOURCE.Coins;

public class ConfigurationFileHandler {
	public static void main(String[] args){
		try {
			Gson gson = new Gson();
			Scanner scanner= new Scanner(System.in);
			final ArrayList<Territory> territories = new ArrayList<>();
			final ArrayList<Character> characters= new ArrayList<>();
			
			
			
			/*
			//PER CREARE I TERRITORI
			for (int i=0; i < 5; i++){
				System.out.println("Inserisci la condizione di attivazione: ");
				int condAttivazione= scanner.nextInt();
				System.out.println("Inserisci il periodo: ");
				int periodo= scanner.nextInt();
				Territory ter = new Territory(condAttivazione, periodo, new ArrayList<>(), new ArrayList<>());
				territories.add(ter);
			}
			
			//PER CREARE I PERSONAGGI
			for (int i=0; i < 5; i++){
				System.out.println("Inserisci il periodo: ");
				int periodo= scanner.nextInt();
				Character character = new Character(new Coins(), periodo, new ArrayList<>(), new ArrayList<>());
				characters.add(character);
			}
			
			//--------------------------------------------------------------------
			String territoriInJson = gson.toJson(territories);
			System.out.println(territoriInJson);
			
			String personaggiInJson= gson.toJson(characters);
			System.out.println(personaggiInJson);*/
			//--------------------------------------------------------------------
			/*FileOutputStream prova = new FileOutputStream("config.json");
	        PrintStream scrivi = new PrintStream(prova);
	        scrivi.print(territoriInJson);
	        scrivi.print(personaggiInJson);*/
			//--------------------------------------------------------------------
			FileReader prova1 = new FileReader("config.json");
			BufferedReader b= new BufferedReader(prova1);
			String territoriInJson;
			while(true){
				territoriInJson=b.readLine();
				if(territoriInJson==null) break;
				System.out.println(territoriInJson);
			}
			Type list= new TypeToken<ArrayList<Territory>>(){}.getType();
			ArrayList<Territory> territori = gson.fromJson(territoriInJson, list);
			
			/*Type list2= new TypeToken<ArrayList<Character>>(){}.getType();
			ArrayList<Character> personaggi = gson.fromJson(personaggiInJson, list2);*/
			for (int i=0; i<territori.size(); i++){
				System.out.println(territori.get(i).activationCondition);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
