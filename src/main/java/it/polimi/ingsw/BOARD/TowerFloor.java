package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.BONUS.PermanentBonus;

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
	
	@Override
	public void addFamilyMember(FamilyMember newFamilyMember){
		
		super.addFamilyMember(newFamilyMember); //aggiunge il familiare all'arraylist di position
		
		Player cardPlayer = newFamilyMember.getPlayer(); //variabile temp del giocatore associato al familymember
		cardPlayer.getPersonalBoard().putDevelopmentCard(this.developmentCard); //aggiunge la carta alla personal board del player
			
		//TODO dare permanent bonus
		
		for(ImmediateBonus immediateBonus : this.developmentCard.immediateEffect){ //attiva il metodo immediate bonus per ogni primary effect 
			giveImmediateBonus(cardPlayer,immediateBonus);
		}
		if(this.developmentCard.developmentCardType != DevelopmentCardType.CHARACTER){ //se non sono carte character Sattiva il metodo immediate bonus per ogni secondary effect
			for(ImmediateBonus immediateBonus : this.developmentCard.secondaryEffect){
				giveImmediateBonus(cardPlayer, immediateBonus); 
			}
		}
		
		this.developmentCard = null; //cancella carta sul piano della torre
	}//commento commento
}

