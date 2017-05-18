package it.polimi.ingsw.HANDLER;

import java.io.File;
import org.codehaus.jackson.map.ObjectMapper;

import it.polimi.ingsw.CARD.Territory;

public class ConfigurationFileHandler {
	public static void main(String[] args){
		try {
			//file contiene il riferimento al file che contiene la prova
			File file= new File("prova.json");
			ObjectMapper map= new ObjectMapper();
			//viene creata la carta territorio leggendo il file in json 
			Territory territorio = map.readValue(file, Territory.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
