package it.polimi.ingsw.GC_15;

import java.util.ArrayList;

public class ContainerTerritoryCard implements ContainerCard{
	private ArrayList<DevelopmentCard> territories;
	public final DevelopmentCardType developmentCardType= DevelopmentCardType.TERRITORY;
	
	public void add(DevelopmentCard territory){
		territories.add(territory);
	}
}
