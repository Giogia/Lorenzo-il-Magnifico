package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;

public abstract class DevelopmentCard {
	public final int period;
	public final ArrayList<ImmediateBonus> immediateEffect;
	public final ArrayList<Bonus> secondaryEffect;
	public final DevelopmentCardType type;
	
	public DevelopmentCard(int period, ArrayList<ImmediateBonus> immediateEffect, ArrayList<Bonus> secondaryEffect, DevelopmentCardType type) {
		this.period=period;
		this.immediateEffect=immediateEffect;
		this.secondaryEffect=secondaryEffect;
		this.type=type;
	}
}
