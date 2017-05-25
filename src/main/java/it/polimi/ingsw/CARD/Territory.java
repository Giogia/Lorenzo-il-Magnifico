package it.polimi.ingsw.CARD;

import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.GC_15.FamilyMember;

public class Territory extends DevelopmentCard {
	
	public Territory(String name, int period, ArrayList<ImmediateBonus> immediateEffect, ArrayList<Bonus> secondaryEffect) {
		super(name, period, DevelopmentCardType.TERRITORY, immediateEffect, secondaryEffect);
	}
	
	public void getHarvestBonus(FamilyMember familyMember){
		//TODO
	}
}
