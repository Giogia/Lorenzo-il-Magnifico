package it.polimi.ingsw.CARD;

import java.util.ArrayList;

public class ContainerCharacterCard implements ContainerCard {
	private ArrayList<DevelopmentCard> characters;
	private DevelopmentCardType type= DevelopmentCardType.CHARACTER;
	
	public DevelopmentCardType getType() {
		return type;
	}
	
	public void add(DevelopmentCard character){
		characters.add(character);
	}
}
