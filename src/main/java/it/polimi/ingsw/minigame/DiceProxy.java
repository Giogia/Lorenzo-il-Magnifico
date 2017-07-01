package it.polimi.ingsw.minigame;

import it.polimi.ingsw.GC_15.Dice;
import javafx.scene.image.Image;

public class DiceProxy {

	private Image image;
	
	public Image getImage() {
		return image;
	}
	
	public DiceProxy(Dice dice) {
		String imagePath = dice.getDiceColour()+"-"+dice.getValue()+".png";
		this.image = new Image(imagePath);
	}
}
