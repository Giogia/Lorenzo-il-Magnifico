package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.HashMap;

import it.polimi.ingsw.CARD.DevelopmentCardType;

public class EndGameCardBonus extends PermanentBonus {
	private HashMap<DevelopmentCardType, Boolean> developmentCardType;
	
	public EndGameCardBonus(HashMap<DevelopmentCardType, Boolean> developmentcardType){
		super("EndGameCardBonus");
		this.developmentCardType = new HashMap<>();
		this.developmentCardType.putAll(developmentcardType);
	}

	public HashMap<DevelopmentCardType, Boolean> getDevelopmentCardType() {
		return developmentCardType;
	}
}
