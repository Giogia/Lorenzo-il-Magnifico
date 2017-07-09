package it.polimi.ingsw.BONUS.ADVANCED;

import java.io.IOException;
import java.util.ArrayList;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.CARD.OncePerRoundLeaderCard;
import it.polimi.ingsw.CARD.PermanentLeaderCard;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.manager.ConnectionManager;
import it.polimi.ingsw.manager.ConnectionManagerImpl;
import it.polimi.ingsw.manager.Manager;

//specific bonus of Lorenzo il magnifico leader card
public class CopyBonus extends ImmediateBonus{
	
	
	public CopyBonus() {
		super("CopyBonus");
	}

	public void getImmediateBonus(Player player) throws IOException, MyException, TimeExpiredException{
		LeaderCard activatedLeaderCard = findCopyBonus(player);
		LeaderCard chosenLeaderCard = Manager.choiceLeaderCardToCopy(player, activatedLeaderCard);
		if (chosenLeaderCard == null){
			return;
		}
		LeaderCard copiedCard;
		if (chosenLeaderCard instanceof OncePerRoundLeaderCard){
			copiedCard = new OncePerRoundLeaderCard(chosenLeaderCard.getName(), chosenLeaderCard.bonus, null, null, 0);
			for (Bonus bonus : copiedCard.bonus) {
				((ImmediateBonus) bonus).getImmediateBonus(player);
			}
		}
		else {
			copiedCard = new PermanentLeaderCard(chosenLeaderCard.getName(), null, null, chosenLeaderCard.bonus, 0);
			for (Bonus bonus : copiedCard.bonus) {
				((PermanentBonus) bonus).getPermanentBonus(player);
			}
		}
		player.getPersonalBoard().getActivatedLeaderCards().remove(activatedLeaderCard);
		player.getPersonalBoard().getActivatedLeaderCards().add(copiedCard);
	}

	private LeaderCard findCopyBonus(Player player) {
		ArrayList<LeaderCard> playerLeaderCards = player.getPersonalBoard().getActivatedLeaderCards();
		for (LeaderCard leaderCard : playerLeaderCards) {
			ArrayList<Bonus> bonuses = leaderCard.bonus;
			for (Bonus bonus : bonuses) {
				if (bonus instanceof CopyBonus){
					return leaderCard;
				}
			}
		}
		return null;
	}

	@Override
	public String getDescription() {
		String description = "You can copy the effect of a Leader Card";
		return description;
	}
	
		

	
}