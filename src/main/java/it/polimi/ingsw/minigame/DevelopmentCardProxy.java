package it.polimi.ingsw.minigame;

import it.polimi.ingsw.CARD.DevelopmentCard;
import javafx.scene.image.Image;

public class DevelopmentCardProxy {

	private Image image;
	
	public Image getImage() {
		return image;
	}
	
	public DevelopmentCardProxy(DevelopmentCard developmentCard) {
		String imagePath = developmentCard.getName()+".png";
		this.image = new Image(imagePath);
	}
}
