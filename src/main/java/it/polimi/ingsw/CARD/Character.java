package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.RESOURCE.Coins;

public class Character extends DevelopmentCard{
	public final Coins cost;
	
	public Character(String name, Coins cost, int period, ArrayList<ImmediateBonus> immediateEffect, ArrayList<Bonus> secondaryEffect) {
		super(name, period, DevelopmentCardType.character, immediateEffect, secondaryEffect);
		this.cost=cost;
	}
}
