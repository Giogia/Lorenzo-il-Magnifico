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
		String description = "Nome della carta: " + super.getName() + "\n";
		if (immediateEffect != null){
			description = description + "Bonus derivanti dall'effetto immediato della carta: \n";
			
			for (int i=0; i < immediateEffect.size(); i++){
				if (i == immediateEffect.size()-1){
					return description + immediateEffect.get(i).getDescription();
				}
				description = description + immediateEffect.get(i).getDescription() + "\n";
			}
		}
		return description;
	}
}
