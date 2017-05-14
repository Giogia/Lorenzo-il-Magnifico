package it.polimi.ingsw.BOARD;

import it.polimi.ingsw.GC_15.FamilyMember;

public abstract class Zone{
	private Position[] position;
	
	
	public Position getPosition(int position) {
		return this.position[position];
	}
	
	public void setPosition(Position[] position) {
		this.position = position;
	}
	
	public void setFamilyMemberPosition(FamilyMember familyMember,Position position){
		position.addFamilyMember(familyMember);
	}

	public boolean setFamilyMemberPosition(FamilyMember familyMember,Position position,int index){
		if(position.addFamilyMember(familyMember, index)) {
			return true;
		}
		return false;
	}
	
	public void deleteAllFamilyMember(){
		for(Position position: this.position) {
			position.deleteAllFamilyMember();
		}
	}
}
