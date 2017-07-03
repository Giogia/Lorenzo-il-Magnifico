package it.polimi.ingsw.minigame;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.GC_15.FamilyMember;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class PositionProxy implements Serializable {
	
	protected ArrayList<FamilyMemberProxy> familyMemberProxies = new ArrayList<>();
	protected ObjectProperty<Image> imageProperty;
	protected int numberOfPosition;
	
	public PositionProxy(Position position, int numberOfPosition) {
		for (FamilyMember familyMember : position.getFamilyMembers()) {
			familyMemberProxies.add(new FamilyMemberProxy(familyMember));
		}
		this.numberOfPosition = numberOfPosition;
	}

	public ArrayList<FamilyMemberProxy> getFamilyMemberProxies() {
		return familyMemberProxies;
	}
	
	public void setImageProperty(String url){
		if(imageProperty==null)
			imageProperty = new SimpleObjectProperty<>();
		imageProperty.set(new Image(url));
	}

	public int getNumberOfPosition() {
		return numberOfPosition;
	}
}
