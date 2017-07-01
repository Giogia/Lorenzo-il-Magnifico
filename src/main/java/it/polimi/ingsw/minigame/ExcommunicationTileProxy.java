package it.polimi.ingsw.minigame;

import it.polimi.ingsw.GC_15.ExcommunicationTile;
import javafx.scene.image.Image;

public class ExcommunicationTileProxy {

	private Image image;
	
	public Image getImage() {
		return image;
	}
	
	public ExcommunicationTileProxy(ExcommunicationTile excommunicationTile) {
		//String imagePath = excommunicationTile.getPeriod()+"-"+excommunicationTile.getId()+".png";
		//this.image = new Image(imagePath);
	}
}
