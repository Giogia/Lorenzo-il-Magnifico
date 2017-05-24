package it.polimi.ingsw.CONTROLLER;

import java.util.ArrayList;

import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class ActivateCardsController implements Controller {

	public static boolean check(ArrayList<DevelopmentCard> cardsToActivate, Player player){
		ArrayList<Resource> copiedResources = player.getPersonalBoard().getResources();
		/*crea copia delle risorse e attiva le carte modificando quelle
		 * attiva una carta e fa tutti i controlli per tutte le risorse maggiori di zero
		 * se va bene va avanti senno ritorna falso
		 * quando ha finito le carte copia le risorse sul player
		 * ritorna true
		 */
		
	}


} 