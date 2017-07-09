package it.polimi.ingsw.minigame;

import java.io.Serializable;

import it.polimi.ingsw.CARD.LeaderCard;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class LeaderCardProxy implements Serializable{

	private String imagePath;
	private ObjectProperty<Image> imageProperty;
	
	public LeaderCardProxy(LeaderCard leaderCard) {
		imagePath = "it/polimi/ingsw/gui/resources/LeaderCards/" + leaderCard.getName() + ".png";
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
	
	public void setImageProperty(String url){
		if(imageProperty==null)
			imageProperty = new SimpleObjectProperty<>();
		imageProperty.set(new Image(url));
	}
	
	
}
