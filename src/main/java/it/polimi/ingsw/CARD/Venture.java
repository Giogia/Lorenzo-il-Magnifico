package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.ResourceType;

public class Venture extends DevelopmentCard{
	public final int militaryPointRequirement; //if = 0 this requirement doesn't exists 
	public final ArrayList<Resource> cost;
	public final MilitaryPoints alternativeCost; //IT'S ONLY MILITARYPOINTS
	
	public Venture(String name,int requirement, ArrayList<Resource> cost, MilitaryPoints alternativeCost, int period, ArrayList<ImmediateBonus> immediateEffect, ArrayList<Bonus> secondaryEffect) {
		super(name, period, DevelopmentCardType.venture, immediateEffect, secondaryEffect);
		if (cost.size() == 1 && cost.get(0).getClass().equals(MilitaryPoints.class)){
			this.alternativeCost = (MilitaryPoints) cost.get(0);
			this.cost = null;
			this.militaryPointRequirement = requirement;
		}
		else {
			militaryPointRequirement=requirement;
			this.cost=cost;
			this.alternativeCost=alternativeCost;
		}
	}
		
	
	@Override
	public String getDescription() {
		String description = "";
		if (cost != null){
			description = description + "Cost: \n";
			for (Resource resource : cost) {
				description = description + resource.getDescription() + "\n";
			}
			if (alternativeCost != null){
				description = description + "Alternative cost: \n You need " + militaryPointRequirement + " military points \n"
						+ "Costo: " + alternativeCost.getDescription() + "\n";
			}
			
		}
		else {
			description = description + "Cost: \n You need " + militaryPointRequirement + " military points \n"
						+ "Cost: " + alternativeCost.getDescription() + "\n";
		}
		description = description + super.getDescription();
		return description;
	}
	
	public ArrayList<Resource> getCost() {
		return cost;
	}
	
	public MilitaryPoints getAlternativeCost() {
		return alternativeCost;
	}
}
