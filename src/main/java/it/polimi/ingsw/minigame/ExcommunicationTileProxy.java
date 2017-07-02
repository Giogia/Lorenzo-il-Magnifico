package it.polimi.ingsw.minigame;

import java.io.Serializable;

import it.polimi.ingsw.GC_15.ExcommunicationTile;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class ExcommunicationTileProxy implements Serializable{

	private String imagePath;
	private ObjectProperty<Image> imageProperty;
	
	
	public ExcommunicationTileProxy(ExcommunicationTile excommunicationTile) {
		//String imagePath = excommunicationTile.getPeriod()+"-"+excommunicationTile.getId()+".png";
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public ObjectProperty<Image> getImageProperty() {
		return imageProperty;
	}
	
	public void setImageProperty(ObjectProperty<Image> imageProperty) {
		imageProperty = new SimpleObjectProperty<>();
		imageProperty.set(new Image(imagePath));
	}
}
