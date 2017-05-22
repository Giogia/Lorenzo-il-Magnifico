package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;

public class ZoneOccupiedBySameColorController implements Controller{
	
	/* Per prima cosa controlla che il familyMember in ingresso non sia di colore neutro, 
	 * se lo è fine.
	 * Se non è neutro, controlla con un ciclo tutte le posizioni della zona.
	 * In ogni posizione controlla, se ci sono familiari, che siano dello stesso player.
	 * Se sono dello stesso player, controlla che quello nella posizione sia neutro.
	 * Se non è neutro ritorna falso
	 * Dopo tutto il ciclo, siamo sicuri che non ci siano familiari dello stesso colore, ritorna vero
	 */
	public boolean check(Zone zone, FamilyMember familyMember){
		if (isNeutral(familyMember)){
			return true;
		}
		else{
			Position[] positions = zone.getPositions();
			for (int i = 0; i < positions.length; i++){
				ArrayList<FamilyMember> positionFamilyMembers = positions[i].getFamilyMembers();
				for (FamilyMember positionFamilyMember : positionFamilyMembers) {
					if (samePlayer(familyMember, positionFamilyMember)){
						if (!isNeutral(positionFamilyMember))
							return false;
					}
				}
			}
			return true;
		}
	}
	
	private boolean samePlayer(FamilyMember familyMember1, FamilyMember familyMember2){
		return familyMember1.getPlayer().equals(familyMember2.getPlayer());
	}
	
	private boolean isNeutral(FamilyMember familyMember){
		return (familyMember.getDice().getDiceColour().equals(DiceColour.Neutral));
	}

}
