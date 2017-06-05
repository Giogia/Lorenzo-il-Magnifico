package it.polimi.ingsw.BONUS.ADVANCED;

public class TerritoryCardRequirementBonus extends PermanentBonus{
	public final boolean needRequirement;
	
	public TerritoryCardRequirementBonus(boolean needRequirement) {
		super("TerritoryCardRequirementBonus");
		this.needRequirement = needRequirement;
	}

}
