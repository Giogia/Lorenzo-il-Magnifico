package it.polimi.ingsw.CARD;

import java.util.ArrayList;

public class ContainerVentureCard implements ContainerCard {
	private ArrayList<DevelopmentCard> ventures;
	private DevelopmentCardType type= DevelopmentCardType.VENTURE;
	
	public DevelopmentCardType getType() {
		return type;
	}
	
	public void add(DevelopmentCard venture){
		ventures.add(venture);
	}
}
