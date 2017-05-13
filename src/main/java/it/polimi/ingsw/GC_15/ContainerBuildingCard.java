package it.polimi.ingsw.GC_15;

import java.util.ArrayList;

public class ContainerBuildingCard implements ContainerCard {
	private ArrayList<DevelopmentCard> buildings;
	public final DevelopmentCardType developmentCardType= DevelopmentCardType.BUILDING;
	
	public void add(DevelopmentCard building){
		buildings.add(building);
	}
}
