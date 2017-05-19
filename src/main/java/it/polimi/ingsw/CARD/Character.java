package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.RESOURCE.Coins;

public class Character extends DevelopmentCard{
	public final Coins cost;
	
	public Character(Coins cost, int period, ArrayList<ImmediateBonus> immediateEffect, ArrayList<Bonus> secondaryEffect) {
		super(period, DevelopmentCardType.CHARACTER, immediateEffect, secondaryEffect);
		this.cost=cost;
	}
}
