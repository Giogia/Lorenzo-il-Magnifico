package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;

//this bonus sets the development card types a player can not take the bonus from at the end of the game
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
					((EndGameCardBonus) permanentBonus).addBonus(this);
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
		StringBuilder description = new StringBuilder();
		description.append("You cannot have the end game bonus for :\n");
		for(Entry<DevelopmentCardType, Boolean> entry : developmentCardTypes.entrySet()){
			if(entry.getValue()==false)
				description.append(entry.getKey() +"cards \n");
		}
		return description.toString();
	}
}
