package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class TowerFloor extends Position {
	
	
	private DevelopmentCard developmentCard;
	
	public TowerFloor(ArrayList<ImmediateBonus> boardBonus, int diceRequirement) {
		super(boardBonus, diceRequirement);
		this.developmentCard = null;
	}
	
	public DevelopmentCard getDevelopmentCard() {
		return this.developmentCard;
	}
	
	public void setDevelopmentCard(DevelopmentCard developmentCard) {
		this.developmentCard = developmentCard;
	}
	
	@Override
	public void addFamilyMember(FamilyMember newFamilyMember) throws MyException, IOException{
		
		super.addFamilyMember(newFamilyMember); //aggiunge il familiare all'arraylist di position
		
		Player cardPlayer = newFamilyMember.getPlayer(); //variabile temp del giocatore associato al familymember
		cardPlayer.getPersonalBoard().putDevelopmentCard(this.developmentCard); //aggiunge la carta alla personal board del player
		ArrayList<ImmediateBonus> cardImmediateBonus = developmentCard.immediateEffect;
		ArrayList<Bonus> secondaryEffect = developmentCard.secondaryEffect;
		DevelopmentCardType developmentCardType = developmentCard.developmentCardType;
		this.developmentCard = null;
		if(cardImmediateBonus!=null){
			for(ImmediateBonus immediateBonus : cardImmediateBonus){ //attiva il metodo immediate bonus per ogni primary effect 
				giveImmediateBonus(cardPlayer,immediateBonus);
			}
		}	
		if (developmentCardType.equals(DevelopmentCardType.character)){
			if (secondaryEffect != null){
				for (Bonus bonus : secondaryEffect) {
					((PermanentBonus) bonus).getPermanentBonus(cardPlayer);
				}
			}
		}
	}
	
	@Override
	public String getDescription() {
		String description = super.getDescription();
		if(developmentCard==null){
			description = description + "No Cards in this position \n";
		}
		else{
		description = description + developmentCard.getDescription();
		}
		return description;
	}
}

