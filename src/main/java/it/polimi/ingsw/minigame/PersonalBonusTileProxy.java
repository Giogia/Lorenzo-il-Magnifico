package it.polimi.ingsw.minigame;

import java.io.Serializable;

import it.polimi.ingsw.GC_15.PersonalBonusTile;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class PersonalBonusTileProxy implements Serializable{

	private String imagePath;
	private ObjectProperty<Image> imageProperty;
	
	public PersonalBonusTileProxy(PersonalBonusTile personalBonusTile) {
		//String imagePath = "personalBonusTile-"+personalBonusTile.getId()+".png"
	}
	
	public String getPathImage() {
		return imagePath;
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
