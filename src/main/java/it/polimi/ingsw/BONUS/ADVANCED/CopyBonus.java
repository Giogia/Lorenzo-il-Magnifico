package it.polimi.ingsw.BONUS.ADVANCED;

import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.CARD.OncePerRoundLeaderCard;
import it.polimi.ingsw.CARD.PermanentLeaderCard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.manager.Manager;

public class CopyBonus extends ImmediateBonus{
	
	
	public CopyBonus() {
		super("CopyBonus");
	}

	public void getImmediateBonus(Player player){
		LeaderCard chosenLeaderCard = Manager.choiceLeaderCardToCopy();
		LeaderCard copiedCard;
		if (chosenLeaderCard instanceof OncePerRoundLeaderCard){
			copiedCard = new OncePerRoundLeaderCard(chosenLeaderCard.getName(), chosenLeaderCard.bonus, null, null, 0);
		}
		else {
			copiedCard = new PermanentLeaderCard(chosenLeaderCard.getName(), null, null, chosenLeaderCard.bonus, 0);
		}
		player.getPersonalBoard().putLeaderCard(copiedCard);
	}

	@Override
	public String getDescription() {
		String description = "You can copy the effect of a Leader Card";
		return description;
	}
	
		

	
}