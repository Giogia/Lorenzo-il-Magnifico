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
}//TODO metodo per dopo il  family dare la carta alla personal board , la personal board da il permanent bonus , da l'immediate bonus della carta, cancella la carta dall floor