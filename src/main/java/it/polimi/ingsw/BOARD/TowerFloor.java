package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.MyException;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.TimeExpiredException;
import it.polimi.ingsw.minigame.DevelopmentCardProxy;
import it.polimi.ingsw.minigame.Update;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

//model class of towers' positions
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
	
	
	//this method add the family member, give the card and the bonuses of the position
	@Override
	public void addFamilyMember(FamilyMember newFamilyMember) throws MyException, IOException, TimeExpiredException{
		
		super.addFamilyMember(newFamilyMember); //add family member to arraylist of positions
		
		Player cardPlayer = newFamilyMember.getPlayer(); //temp variable of the family member's player
		cardPlayer.getPersonalBoard().putDevelopmentCard(this.developmentCard); //add the card to the player's personal board
		ArrayList<ImmediateBonus> cardImmediateBonus = developmentCard.immediateEffect;
		ArrayList<Bonus> secondaryEffect = developmentCard.secondaryEffect;
		DevelopmentCardType developmentCardType = developmentCard.developmentCardType;

		//updating clients
		DevelopmentCardProxy developmentCardProxy = new DevelopmentCardProxy(developmentCard);
		Update.getInstance().TowerFloorOccupied(this, cardPlayer.getBoard().getTower(developmentCardType), developmentCardProxy);
		
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

