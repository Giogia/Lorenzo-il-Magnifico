package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;

public class EndGameCardBonus extends PermanentBonus {
	private HashMap<DevelopmentCardType, Boolean> developmentCardTypes;
	//If a developmentCardType is false you can't have the endGame bonus for that cardType
	
	public EndGameCardBonus(HashMap<DevelopmentCardType, Boolean> developmentcardType){
		super("EndGameCardBonus");
		this.developmentCardTypes = new HashMap<>();
		this.developmentCardTypes.putAll(developmentcardType);
	}

	public HashMap<DevelopmentCardType, Boolean> getDevelopmentCardType() {
		return developmentCardTypes;
	}
	
	@Override
	public void getPermanentBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof EndGameCardBonus){
					
				}
			}
		}
		super.getPermanentBonus(player);
	}
	
	public void addBonus(EndGameCardBonus newBonus){
		Set<DevelopmentCardType> newBonusDevelopmentCardTypes = newBonus.getDevelopmentCardType().keySet();
		Set<DevelopmentCardType> thisDevelopmentCardTypes = this.developmentCardTypes.keySet();
		for (DevelopmentCardType newDevelopmentCardType : newBonusDevelopmentCardTypes) {
			if (thisDevelopmentCardTypes.contains(newDevelopmentCardType)){
				boolean oldValue = this.developmentCardTypes.get(newDevelopmentCardType);
				boolean newValue = oldValue && newBonus.getDevelopmentCardType().get(newDevelopmentCardType);
				this.developmentCardTypes.replace(newDevelopmentCardType, oldValue, newValue);
			}
			else {
				boolean newValue = newBonus.getDevelopmentCardType().get(newDevelopmentCardType);
				this.developmentCardTypes.put(newDevelopmentCardType, newValue);
			}
		}
	}
	
	public boolean getDevelopmentCardBoolean(DevelopmentCardType developmentCardType){
		Set<DevelopmentCardType> bonusDevelopmentCardTypes = this.developmentCardTypes.keySet();
		if (bonusDevelopmentCardTypes.contains(developmentCardType)){
			return this.developmentCardTypes.get(developmentCardType);
		}
		return true;
	}
	
	public String getDescription(){
		String description = "You cannot have the end game bonus for :\n";
		for(DevelopmentCardType developmentCardType : developmentCardTypes.keySet()){
			if(developmentCardTypes.get(developmentCardType)==false)
				description = description + developmentCardType +"cards \n";
		}
		return description;
	}
}
