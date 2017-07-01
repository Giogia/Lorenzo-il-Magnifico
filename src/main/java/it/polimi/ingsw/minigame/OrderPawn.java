package it.polimi.ingsw.minigame;

import it.polimi.ingsw.GC_15.Player.Color;
import javafx.scene.image.Image;

public class OrderPawn {

	private Color color;
	private Image image;
	
	public Color getColor() {
		return color;
	}
	
	public Image getImage() {
		return image;
	}
	
	public OrderPawn(Color color) {
		this.color = color;
		String imagePath = color.name()+".png";
		this.image = new Image(imagePath);
	}
}
