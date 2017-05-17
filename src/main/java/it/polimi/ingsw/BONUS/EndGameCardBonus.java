package it.polimi.ingsw.BONUS;

import java.util.HashMap;

import it.polimi.ingsw.CARD.DevelopmentCardType;

public class EndGameCardBonus extends PermanentBonus {
	private HashMap<DevelopmentCardType, Boolean> developmentCardType;
	
	//TODO: Da verificare il metodo putAll
	public EndGameCardBonus(HashMap<DevelopmentCardType, Boolean> developmentcardType){
		this.developmentCardType = new HashMap<>();
		this.developmentCardType.putAll(developmentcardType);
	}

	public HashMap<DevelopmentCardType, Boolean> getDevelopmentCardType() {
		return developmentCardType;
	}
}
