package it.polimi.ingsw.HANDLER.GAME;

import java.io.File;
import java.util.Scanner;

import com.google.gson.*;

public class ConfigurationFileHandler {
	public static void main(String[] args){
		try {
			Gson gson = new Gson();
			
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
			}*/
			File file = new File("config.json");
			Scanner scanner= new Scanner(file);
			String inJson="";
			try{
				while (scanner.hasNextLine()){
					inJson = inJson + scanner.nextLine();
				}
			}
			finally{
				scanner.close();
			}
			DataFromFile data = gson.fromJson(inJson, DataFromFile.class);
			
			System.out.println("il numero di territori:"+data.getTerritories().size());
			System.out.println("il periodo della prima carta territorio: "+data.getTerritories().get(0).period);
			System.out.println("il periodo della prima character card: "+data.getCharacters().get(0).period);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
