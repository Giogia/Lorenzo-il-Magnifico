package it.polimi.ingsw.GC_15;

import java.util.ArrayList;

public class ContainerVentureCard implements ContainerCard {
	private ArrayList<DevelopmentCard> ventures;
	public final DevelopmentCardType developmentCardType= DevelopmentCardType.VENTURE;
	
	public void add(DevelopmentCard venture){
		ventures.add(venture);
	}
}
