package it.polimi.ingsw.minigame;

import java.io.Serializable;

import it.polimi.ingsw.CARD.DevelopmentCard;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class DevelopmentCardProxy implements Serializable {
	transient private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
	private String path = "";
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	
	public DevelopmentCardProxy() {
		System.out.println("arrivo qui");
		path = "it/polimi/ingsw/gui/resources/towerFloor.jpeg";
	}
	
	public ObjectProperty<Image> getImageProperty(){
		return imageProperty;
	}
	
	public void setImageProperty(Image image){
		//String imagePath = "it/polimi/ingsw/gui/resources/developmentCards/" + cardName + ".png";
		System.out.println(image);
		imageProperty.set(image);
	}
}
