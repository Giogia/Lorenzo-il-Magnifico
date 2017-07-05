package it.polimi.ingsw.minigame;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.GC_15.FamilyMember;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class PositionProxy implements Serializable {
	
	protected ArrayList<FamilyMemberProxy> familyMemberProxies = new ArrayList<>();
	protected ObjectProperty<Image> imageProperty;
	protected int numberOfPosition;
	protected ZoneProxy zoneProxy;
	
	public PositionProxy(Position position, ZoneProxy zoneProxy, int numberOfPosition) {
		/*for (FamilyMember familyMember : position.getFamilyMembers()) {
			familyMemberProxies.add(new FamilyMemberProxy(familyMember));
		}*/
		if(!position.getFamilyMembers().isEmpty()){
			familyMemberProxies.add(new FamilyMemberProxy(position.getFamilyMember(0)));
		}else{
			familyMemberProxies.add(new FamilyMemberProxy());
		}
		//familyMemberProxies.add(new FamilyMemberProxy());
		this.zoneProxy = zoneProxy;
		this.numberOfPosition = numberOfPosition;
	}

	public ArrayList<FamilyMemberProxy> getFamilyMemberProxies() {
		return familyMemberProxies;
	}
	
	public ZoneProxy getZoneProxy() {
		return zoneProxy;
	}
	
	public void setImageProperty(){
		if(imageProperty==null)
			imageProperty = new SimpleObjectProperty<>();
		imageProperty.set(new Image("it/polimi/ingsw/gui/resources/blank.png"));
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
