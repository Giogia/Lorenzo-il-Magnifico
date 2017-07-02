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
	private String imagePath;
	private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
	
	public FamilyMemberProxy(FamilyMember familyMember) {
		this.color = familyMember.getPlayer().getColor();
		this.diceColour = familyMember.getDice().getDiceColour();
		this.imagePath = "it/polimi/ingsw/gui/resources/FamilyMembers/" + familyMember.getPlayer().getColor()+"-"+familyMember.getDice().getDiceColour()+".png";
	}
	
	public Color getColor() {
		return color;
	}
	
	public DiceColour getDiceColour() {
		return diceColour;
	}
	
	public String getImagePath() {
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


