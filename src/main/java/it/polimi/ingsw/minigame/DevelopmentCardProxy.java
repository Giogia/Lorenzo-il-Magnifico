package it.polimi.ingsw.minigame;

import java.io.Serializable;

import it.polimi.ingsw.CARD.DevelopmentCard;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class DevelopmentCardProxy implements Serializable {
	
	private String imagePath;
	private ObjectProperty<Image> imageProperty;
	
	public DevelopmentCardProxy(DevelopmentCard card){
		if(card == null){
			imagePath = "it/polimi/ingsw/gui/resources/blank.png";
		}else{
			imagePath = "it/polimi/ingsw/gui/resources/DevelopmentCards/" + card.getName() + ".png";
		}
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public ObjectProperty<Image> getImageProperty(){
		return imageProperty;
	}
	
	public void setImageProperty(){
		imageProperty = new SimpleObjectProperty<>();
		imageProperty.set(new Image(imagePath));
	}
	
	public void setImageProperty(String url){
		if(imageProperty==null)
			imageProperty = new SimpleObjectProperty<>();
		imageProperty.set(new Image(url));
	}
	


}
