package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.CardType;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

//this bonus increases for a player the resources needed to take a card
public class AddCardCostBonus extends CardCostBonus{

	public AddCardCostBonus(DevelopmentCardType cardType, ArrayList<Resource> resources) {
		super(cardType, resources, "AddCardCostBonus");
	}
	
	@Override
	public void getPermanentBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		for (PermanentBonus permanentBonus : playerBonus) {
			if (permanentBonus instanceof AddCardCostBonus){
				if (((AddCardCostBonus) permanentBonus).getCardType().equals(cardType)){
					((AddCardCostBonus) permanentBonus).addBonus(this);
					return;
				}
			}
		}
		super.getPermanentBonus(player);
		return;
	}
	
	public String getDescription(){
		String description ="Add to "+getCardType()+"cards' cost : \n";
		description = description + super.getDescription();
		return description;
	}

}
