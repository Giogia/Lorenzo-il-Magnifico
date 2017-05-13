package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.CardType;
import it.polimi.ingsw.GC_15.Player;

public class CopyBonus implements ImmediateBonus{
	private ArrayList<CardType> cards;
	
	public CopyBonus(ArrayList<CardType> cards ){
		this.cards = cards;
	}
	
	public void getImmediateBonus(Player player){
		player.chooseCardToCopy(cards); //TODO bisogna modificare chooseCardToCopy
	}

}

