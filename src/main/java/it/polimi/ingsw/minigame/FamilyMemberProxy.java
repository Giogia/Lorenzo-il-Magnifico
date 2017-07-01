package it.polimi.ingsw.minigame;

import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player.Color;
import javafx.scene.image.Image;

public class FamilyMemberProxy {
	
	private Color color;
	private DiceColour diceColour;
	private Image image;

	
	public Color getColor() {
		return color;
	}
	
	public DiceColour getDiceColour() {
		return diceColour;
	}
	
	public Image getImage() {
		return image;
	}
	
	public FamilyMemberProxy(FamilyMember familyMember) {
		this.color = familyMember.getPlayer().getColor();
		this.diceColour = familyMember.getDice().getDiceColour();
		String imagePath = familyMember.getPlayer().getColor()+"-"+familyMember.getDice().getDiceColour()+".png";
		this.image = new Image(imagePath);
	}
	
}


