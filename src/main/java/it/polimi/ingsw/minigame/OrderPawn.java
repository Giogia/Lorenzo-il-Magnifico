package it.polimi.ingsw.minigame;

import java.io.Serializable;

import it.polimi.ingsw.GC_15.Player.Color;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class OrderPawn implements Serializable {

	private Color color;
	private String imagePath;
	private ObjectProperty<Image> imageProperty;
	
	public OrderPawn(Color color) {
		this.color = color;
		this.imagePath = "it/polimi/ingsw/gui/resources/OrderPawns/" + color.name() + ".png";
	}
	
	public Color getColor() {
		return color;
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
