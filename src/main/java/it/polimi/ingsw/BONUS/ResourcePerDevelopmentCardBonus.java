package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.RESOURCE.Resource;

public class ResourcePerDevelopmentCardBonus extends AddResourceBonus {
	private DevelopmentCardType developmentCardType;
	
	public ResourcePerDevelopmentCardBonus(ArrayList<Resource> resources, DevelopmentCardType developmentCardType) {
		super(resources);
		this.developmentCardType = developmentCardType;
	}
	
	
	//TODO forse estende le classi addresourcebonus e multiplyresourcebonus
	
	/*Bombs: Penso che debba estendere addresourcebonus, perchè questo bonus serve ad aggiungere
	 * alle risorse che già hai un tot di risorse per ogni carta di un certo tipo
	 */
	
}
