package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.CardType;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class MultiplyCardCostBonus extends CardCostBonus{

	public MultiplyCardCostBonus(DevelopmentCardType cardType, ArrayList<Resource> resources) {
		super(cardType, resources, "MultiplyCardCostBonus");
	}
	
	@Override
	public void getPermanentBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		for (PermanentBonus permanentBonus : playerBonus) {
			if (permanentBonus instanceof MultiplyCardCostBonus){
				if (((MultiplyCardCostBonus) permanentBonus).getCardType().equals(cardType)){
					((MultiplyCardCostBonus) permanentBonus).addBonus(this);
					return;
				}
			}
		}
		super.getPermanentBonus(player);
		return;
	}

	public String getDescription(){
		String description ="Multiply "+getCardType()+" cards' cost for: \n";
		description = description + super.getDescription();
		return description;
	}
}