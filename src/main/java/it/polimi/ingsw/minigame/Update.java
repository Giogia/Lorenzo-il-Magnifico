package it.polimi.ingsw.minigame;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.manager.User;

public class Update {
	//class which all clients register themself.
	private List<User> observers = new ArrayList<>();
	
	private static Update instance = new Update();
	private Update() { } //singleton 
	
	public static Update getInstance() {
		return instance;
	}
	
	public void addUsers(User observer) {
		observers.add(observer);
	}
	
	public void removeObservers(User observer){
		observers.remove(observer);
	}
	
	public void TowerFloorOccupied(TowerFloor towerFloor){
		
		TowerFloorProxy towerFloorProxy = new TowerFloorProxy(towerFloor);

		
		for (User observer : observers) {
			if(observer.getConnectionType() == true){ //user is a rmi client	
					
				//observer.getCliRmi().updateDueTowerFloorOccupied(towerFloorProxy);
			
			}else{//user is a socket client
				
				//observer.getConnectionManagerSocketServer().getSocketOutClient().writeObject();
				
			}
		}
	}


	private int getTowerFloor(TowerFloor newTowerFloor, Tower tower) {
		for (int i = 0; i < 4; i++) {
			if(newTowerFloor.equals((TowerFloor) tower.getPosition(i))){
				return i;
			}
		}
		System.out.println("NON HO TROVATO IL TOWER FLOOR IN GET TOWER FLOOR UPDATE");
		return 0;
	}
}
