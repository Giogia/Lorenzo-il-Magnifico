package it.polimi.ingsw.minigame;

import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player.Color;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class FamilyMemberProxy {
	
	private Color color;
	private DiceColour diceColour;
	transient private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
	
	public Color getColor() {
		return color;
	}
	
	public DiceColour getDiceColour() {
		return diceColour;
	}
	
	public ObjectProperty<Image> getImageProperty(){
		return imageProperty;
	}
	
	public void setImageProperty(Image image){
		imageProperty.set(image);
	}
	
	public FamilyMemberProxy(FamilyMember familyMember) {
		this.color = familyMember.getPlayer().getColor();
		this.diceColour = familyMember.getDice().getDiceColour();
		String imagePath = "it/polimi/ingsw/gui/resources/FamilyMembers/" + familyMember.getPlayer().getColor()+"-"+familyMember.getDice().getDiceColour()+".png";
		//imageProperty.set(new Image(imagePath));
	}
	
}


