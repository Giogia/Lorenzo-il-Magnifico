package it.polimi.ingsw.minigame;

import it.polimi.ingsw.CARD.LeaderCard;
import javafx.scene.image.Image;

public class LeaderCardProxy {

	Image image;
	
	public Image getImage() {
		return image;
	}
	
	public LeaderCardProxy(LeaderCard leaderCard) {
		String imagePath = leaderCard.getName()+".png";
		this.image = new Image(imagePath);
	}
}
