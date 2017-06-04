package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
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
	public void addFamilyMember(FamilyMember newFamilyMember) throws Exception{
		
		super.addFamilyMember(newFamilyMember); //aggiunge il familiare all'arraylist di position
		
		Player cardPlayer = newFamilyMember.getPlayer(); //variabile temp del giocatore associato al familymember
		cardPlayer.getPersonalBoard().putDevelopmentCard(this.developmentCard); //aggiunge la carta alla personal board del player
		ArrayList<ImmediateBonus> cardImmediateBonus = developmentCard.immediateEffect;
		this.developmentCard = null;
		try{
			for(ImmediateBonus immediateBonus : cardImmediateBonus){ //attiva il metodo immediate bonus per ogni primary effect 
				giveImmediateBonus(cardPlayer,immediateBonus);
			}
		}catch(Exception e){
		}
		/*TODO: permanent bonus
		if(this.developmentCard.developmentCardType != DevelopmentCardType.CHARACTER){
			for(Bonus immediateBonus : this.developmentCard.secondaryEffect){
				giveImmediateBonus(cardPlayer, immediateBonus); 
			}//da testare assolutamente 
		} */
		 //cancella carta sul piano della torre
	}
	
	@Override
	public String getDescription() {
		String description = super.getDescription();
		try{
			description = description + developmentCard.getDescription();
		} catch (Exception e){
			description = description + "No Cards in this position \n";
		}
		return description;
	}
}

