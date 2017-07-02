package it.polimi.ingsw.minigame;

import java.io.Serializable;

import it.polimi.ingsw.CARD.DevelopmentCard;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class DevelopmentCardProxy implements Serializable {
	transient private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
	private String pathImage = "";
	
	public String getPathImage() {
		return pathImage;
	}
	
	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	
	public DevelopmentCardProxy(DevelopmentCard card){
		if(card == null){
			pathImage = "it/polimi/ingsw/gui/resources/towerFloor.jpeg";
		}else{
			pathImage = "it/polimi/ingsw/gui/resources/DevelopmentCards/" + card.getName() + ".png";
		}
	}
	
	public ObjectProperty<Image> getImageProperty(){
		return imageProperty;
	}
	
	
	
	public void setImageProperty(Image image){
		System.out.println(image);
		imageProperty = new SimpleObjectProperty<>();
		imageProperty.set(image);
	}
}
