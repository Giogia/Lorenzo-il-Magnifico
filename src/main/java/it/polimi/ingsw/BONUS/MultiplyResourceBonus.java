package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.RESOURCE.Resource;

public class MultiplyResourceBonus extends ResourceBonus {
		
		
		public MultiplyResourceBonus(ArrayList<Resource> resources) {
		super("multiplyResourceBonus", resources);
	}

		//moltiplica il valore
		@Override
		protected void modify(Resource resource1, Resource resource2) {
			resource1.multAmount(resource2.getAmount());	
		}
		
		@Override
		public String getDescription() {
			String description = "Moltiplica le tue risorse per: \n";
			description = description + super.getDescription();
			return description;
		}
		
		@Override
		public ResourceBonus clone() {
		MultiplyResourceBonus newBonus = new MultiplyResourceBonus(this.resources);
		return newBonus;
		}

	}
