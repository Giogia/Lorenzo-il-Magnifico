package it.polimi.ingsw.minigame;

import java.io.Serializable;

import it.polimi.ingsw.GC_15.PersonalBonusTile;
import javafx.scene.image.Image;

public class PersonalBonusTileProxy implements Serializable{

	private Image image;
	
	public Image getImage() {
		return image;
	}
	
	public PersonalBonusTileProxy(PersonalBonusTile personalBonusTile) {
		//String imagePath = "personalBonusTile-"+personalBonusTile.getId()+".png"
		//this.image = new Image(imagePath);
	}
}
