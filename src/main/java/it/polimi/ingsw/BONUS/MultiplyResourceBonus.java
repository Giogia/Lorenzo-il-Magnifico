package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.RESOURCE.Resource;

public class MultiplyResourceBonus extends ResourceBonus {
		
		
		public MultiplyResourceBonus(ArrayList<Resource> resources) {
		super(resources);
	}

		//moltiplica il valore
		@Override
		protected void modify(Resource resource1, Resource resource2) {
			resource1.multAmount(resource2.getAmount());	
		}

	}
