package it.polimi.ingsw.minigame;

import it.polimi.ingsw.CARD.LeaderCard;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class LeaderCardProxy {

	transient private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
	
	public ObjectProperty<Image> getImageProperty() {
		return imageProperty;
	}
	
	public void setImageProperty(ObjectProperty<Image> imageProperty) {
		this.imageProperty = imageProperty;
	}
	
	public LeaderCardProxy(LeaderCard leaderCard) {
		String imagePath = "it/polimi/ingsw/gui/resources/LeaderCards/" + leaderCard.getName() + ".png";
		System.out.println(imagePath);
		//imageProperty.set(new Image(imagePath));
		System.out.println("crato immagine");
	}
}
