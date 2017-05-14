package it.polimi.ingsw.CARD;

import java.util.ArrayList;

public class ContainerTerritoryCard implements ContainerCard{
	private ArrayList<DevelopmentCard> territories;
	private DevelopmentCardType type= DevelopmentCardType.TERRITORY;
	
	public DevelopmentCardType getType(){
		return type;
	}
	
	public void add(DevelopmentCard territory){
		territories.add(territory);
	}
}
