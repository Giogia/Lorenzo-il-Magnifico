package it.polimi.ingsw.BONUS;

import java.util.HashMap;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;

public class ActionBonus implements ImmediateBonus {
	private HashMap<ActionZone, Integer> action;
	
	public ActionBonus(HashMap<ActionZone, Integer> actionMap){
		action = new HashMap<>();
		action.putAll(actionMap);
	}
	
	public void getImmediateBonus(Player player){
<<<<<<< HEAD
		//TODO  utilizzare un familymember fasullo e crere un dado fasullo e poi cancellarlo a fine azione
=======
		//TODO Bisogna decidere se usare un controller o un familymember fasullo
		
		/*MICHELE: secondo me bisogna usarli entrambi, poichè questo metodo (getImmediateBonus) deve chiamare questo ipotetico 
		 controller (o handler, insomma qualcosa) il quale avrà il metodo che permetterà di chiedere all'utente se vuole usare 
		 i serventi per aumentare il valore del dado fasullo e per chiedere la posizione della carta da prendere. 
		 Inoltre sarà questa classe a chiamare set family member position del family member (fasullo)  */
>>>>>>> branch 'master' of https://github.com/Giogia/Lorenzo-il-Magnifico
	}

}
