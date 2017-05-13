package it.polimi.ingsw.CARD;

import java.util.ArrayList;

public class ContainerCharacterCard implements ContainerCard {
	private ArrayList<DevelopmentCard> characters;
	public final DevelopmentCardType developmentCardType= DevelopmentCardType.CHARACTER;
	
	public void add(DevelopmentCard character){
		characters.add(character);
	}
}
