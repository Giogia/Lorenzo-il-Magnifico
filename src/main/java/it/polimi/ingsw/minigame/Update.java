package it.polimi.ingsw.minigame;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.manager.User;

public class Update {
	//class which all clients register themself.
	private List<User> users = new ArrayList<>();
	
	private static Update instance = new Update();
	private Update() { } //singleton 
	
	public static Update getInstance() {
		return instance;
	}
	
	public void addUser(User observer) {
		users.add(observer);
	}
	
	public void addUsers(List<User> users){
		this.users.addAll(users);
	}
	
	public void removeUser(User observer){
		users.remove(observer);
	}
	
	public void TowerFloorOccupied(TowerFloor towerFloor, Tower tower) throws RemoteException{
		System.out.println("sono arrivato nel metodo towerFloorOccupied");
		int numberOfFloor = getNumberOfFloor(towerFloor, tower);
		DevelopmentCardType towerType = tower.getDevelopmentCardType();
		TowerFloorProxy towerFloorProxy = new TowerFloorProxy(towerFloor, towerType, numberOfFloor);

		for (User observer : users) {
			if(observer.getConnectionType() == true){ //user is a rmi client	
					
				observer.getCliRmi().updateDueTowerFloorOccupied(towerFloorProxy);
			
			}else{//user is a socket client
				
				//observer.getConnectionManagerSocketServer().getSocketOutClient().writeObject();
				
			}
		}
	}


	private int getNumberOfFloor(TowerFloor newTowerFloor, Tower tower) {
		for (int i = 0; i < 4; i++) {
			if(newTowerFloor.equals((TowerFloor) tower.getPosition(i))){
				return i;
			}
		}
		System.out.println("NON HO TROVATO IL TOWER FLOOR IN GET TOWER FLOOR UPDATE");
		return 0;
	}
}
