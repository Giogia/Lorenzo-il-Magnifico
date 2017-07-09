package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player;

//this bonus sets the ability of a player to own territories without military points amount requirement
public class TerritoryCardRequirementBonus extends PermanentBonus{
	private boolean needRequirement; //If false don't need Military Points to activate a territory card
	
	public TerritoryCardRequirementBonus(boolean needRequirement) {
		super("TerritoryCardRequirementBonus");
		this.needRequirement = needRequirement;
	}
	
	public boolean getNeedRequirement(){
		return needRequirement;
	}
	
	public void setNeedRequirement(boolean needRequirement) {
		this.needRequirement = needRequirement;
	}
	
	@Override
	public void getPermanentBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof TerritoryCardRequirementBonus){
					boolean newValue = ((TerritoryCardRequirementBonus) permanentBonus).getNeedRequirement() && this.needRequirement;
					((TerritoryCardRequirementBonus) permanentBonus).setNeedRequirement(newValue);
					return;
				}
			}
		}
		super.getPermanentBonus(player);
	}

	public String getDescription(){
		String description = "you need military points to activate a territory card ";
		if(needRequirement==false){
			description = "You don't need Military Points to activate a territory card \n";
		}
		return description;
	}
}
