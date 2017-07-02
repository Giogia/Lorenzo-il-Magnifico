package it.polimi.ingsw.minigame;

import it.polimi.ingsw.GC_15.Dice;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class DiceProxy {

	private String imagePath;
	private ObjectProperty<Image> imageProperty;
	
	public DiceProxy(Dice dice) {
		imagePath = dice.getDiceColour()+"-"+dice.getValue()+".png";
	}

	public String getImagePath() {
		return imagePath;
	}
	
	public ObjectProperty<Image> getImageProperty() {
		return imageProperty;
	}
	
	public void setImageProperty() {
		imageProperty = new SimpleObjectProperty<>();
		imageProperty.set(new Image(imagePath));
	}
	
	
}
