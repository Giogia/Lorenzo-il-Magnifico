package it.polimi.ingsw.CARD;

import java.util.ArrayList;

public class ContainerBuildingCard implements ContainerCard {
	private ArrayList<DevelopmentCard> buildings;
	private DevelopmentCardType type= DevelopmentCardType.BUILDING;
	
	public DevelopmentCardType getType() {
		return type;
	}
	
	public void add(DevelopmentCard building){
		buildings.add(building);
	}
}
