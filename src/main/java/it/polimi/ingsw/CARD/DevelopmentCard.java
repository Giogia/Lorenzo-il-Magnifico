package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;

public abstract class DevelopmentCard {
	public final int period;
	public final DevelopmentCardType developmentCardType;
	public final ArrayList<ImmediateBonus> immediateEffect;
	public final ArrayList<Bonus> secondaryEffect;
	
	public DevelopmentCard(int period,DevelopmentCardType developmentCardType, ArrayList<ImmediateBonus> immediateEffect, ArrayList<Bonus> secondaryEffect) {
		this.period=period;
		this.developmentCardType=developmentCardType;
		this.immediateEffect=immediateEffect;
		this.secondaryEffect=secondaryEffect;
	}
}
