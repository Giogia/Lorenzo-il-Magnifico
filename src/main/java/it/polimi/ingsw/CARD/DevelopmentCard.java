package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;

public abstract class DevelopmentCard extends Card{
	public final int period;
	public final DevelopmentCardType developmentCardType;
	public final ArrayList<ImmediateBonus> immediateEffect;
	public final ArrayList<Bonus> secondaryEffect;
	
	public DevelopmentCard(String name, int period, DevelopmentCardType developmentCardType, ArrayList<ImmediateBonus> immediateEffect, ArrayList<Bonus> secondaryEffect) {
		super(name);
		this.period=period;
		this.developmentCardType=developmentCardType;
		this.immediateEffect=immediateEffect;
		this.secondaryEffect=secondaryEffect;
	}

	public String getDescription(){
		StringBuilder description = new StringBuilder();
		description.append("Name of the card: " + super.getName() + "\n");
		if (immediateEffect != null){
			description.append("Bonus from the immediate effect of the card: \n");
			
			for (int i=0; i < immediateEffect.size(); i++){
				
				description.append(immediateEffect.get(i).getDescription() + "\n");
			}
		}
		if (secondaryEffect != null){
			description.append("Bonus from secondary effect: \n");
			
			for (int i=0; i < secondaryEffect.size(); i++){
				
				description.append(secondaryEffect.get(i).getDescription() + "\n");
			}
		}
		return description.toString();
	}
}
