package it.polimi.ingsw.minigame;

import java.io.Serializable;

import it.polimi.ingsw.GC_15.Player.Color;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class OrderPawn implements Serializable {

	private Color color;
	transient private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
	
	public Color getColor() {
		return color;
	}
	
	public ObjectProperty<Image> getImageProperty() {
		return imageProperty;
	}
	
	public void setImageProperty(ObjectProperty<Image> imageProperty) {
		this.imageProperty = imageProperty;
	}
	
	public OrderPawn(Color color) {
		this.color = color;
		String imagePath = "it/polimi/ingsw/gui/resources/OrderPawns/" + color.name() + ".png";
		System.out.println(imagePath);
		//imageProperty.set(new Image(imagePath));
		
		
		System.out.println("creato l'immagine");
	}
}
