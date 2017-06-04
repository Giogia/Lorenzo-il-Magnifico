package it.polimi.ingsw.HANDLER.GAME;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.CARD.*;
import it.polimi.ingsw.CARD.Character;

public class Modify {
	public static void main(String[] args){
		try {
			DataFromFile data = ConfigurationFileHandler.getData();
			ArrayList<Building> buildings = data.getBuildings();
			ArrayList<Territory> territories = data.getTerritories();
			ArrayList<Venture> ventures = data.getVentures();
			ArrayList<Character> characters = data.getCharacters();
		
			System.out.println("Inserisci il nome della carta da modificare: ");
			Scanner scanner = new Scanner(System.in);
			String nameCard = scanner.nextLine();
			
			for ( int i = 0; i < buildings.size(); i++) {
				if(buildings.get(i).getName().equals(nameCard)){
					System.out.println(buildings.get(i).getDescription());
					buildings.set(i, Create.createBuilding());
				}
			}
			for ( int i = 0; i < characters.size(); i++) {
				if(characters.get(i).getName().equals(nameCard)){
					System.out.println(characters.get(i).getDescription());
					characters.set(i, Create.createCharacter());
				}
			}
			for ( int i = 0; i < ventures.size(); i++) {
				if(ventures.get(i).getName().equals(nameCard)){
					System.out.println(ventures.get(i).getDescription());
					ventures.set(i, Create.createVenture());
				}
			}
			for ( int i = 0; i < territories.size(); i++) {
				if(territories.get(i).getName().equals(nameCard)){
					System.out.println(territories.get(i).getDescription());
					territories.set(i, Create.createTerritory());
				}
			}
			
			data.setBuildings(buildings);
			data.setCharacters(characters);
			data.setTerritories(territories);
			data.setVentures(ventures);
			
			FileOutputStream prova = new FileOutputStream("config2.json");
	        PrintStream scrivi = new PrintStream(prova);
			scrivi.print(ConfigurationFileHandler.toSerialize(data));
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
