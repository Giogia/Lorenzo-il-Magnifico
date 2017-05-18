package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.CARD.DevelopmentCard;

public class TowerFloor extends Position {
	
	
	private DevelopmentCard developmentCard;
	
	public TowerFloor(Bonus[] boardBonus, int diceRequirement, DevelopmentCard developmentCard) {
		super(boardBonus, diceRequirement);
		this.developmentCard = developmentCard;
	}
	
	public DevelopmentCard getDevelopmentCard() {
		return this.developmentCard;
	}
	
	public void setDevelopmentCard(DevelopmentCard developmentCard) {
		this.developmentCard = developmentCard;
	}
}
