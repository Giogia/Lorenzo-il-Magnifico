package it.polimi.ingsw.GC_15;

import java.util.ArrayList;

public class CopyBonus implements ImmediateBonus{
	private ArrayList<CardType> cards;
	
	public CopyBonus(ArrayList<CardType> cards ){
		this.cards = cards;
	}
	
	public void getImmediateBonus(Player player){
		player.chooseCardToCopy(cards); //TODO bisogna modificare chooseCardToCopy
	}

}

