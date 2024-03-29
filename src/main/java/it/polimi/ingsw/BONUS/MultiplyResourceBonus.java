package it.polimi.ingsw.BONUS;

import java.util.ArrayList;

import it.polimi.ingsw.RESOURCE.Resource;

//this bonus multiplies the amount of a player's resource
public class MultiplyResourceBonus extends ResourceBonus {
		
		
		public MultiplyResourceBonus(ArrayList<Resource> resources) {
		super("multiplyResourceBonus", resources);
	}

		@Override
		protected void modify(Resource resource1, Resource resource2) {
			resource1.multAmount(resource2.getAmount());	
		}
		
		@Override
		public String getDescription() {
			String description = "Moltiply your resources for a factor of: \n";
			description = description + super.getDescription();
			return description;
		}
		
		@Override
		public ResourceBonus createClone() {
		MultiplyResourceBonus newBonus = new MultiplyResourceBonus(this.resources);
		return newBonus;
		}

	}
