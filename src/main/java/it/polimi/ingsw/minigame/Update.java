package it.polimi.ingsw.minigame;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.CARD.CardContainer;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.RESOURCE.Resource;
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
	
	public void TowerFloorOccupied(TowerFloor towerFloor, Tower tower, DevelopmentCardProxy developmentCardProxy) throws RemoteException{
		int numberOfFloor = getNumberOfFloor(towerFloor, tower);
		DevelopmentCardType towerType = tower.getDevelopmentCardType();
		TowerFloorProxy towerFloorProxy = new TowerFloorProxy(towerFloor, towerType, numberOfFloor);

		for (User observer : users) {
			if(observer.getConnectionType() == true){ //user is a rmi client	
					
				observer.getCliRmi().updateDueTowerFloorOccupied(towerFloorProxy, developmentCardProxy);
			
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

	public void positionOccupied(Position position, ZoneProxy zoneProxy, int numberOfPosition) throws RemoteException {
		PositionProxy positionProxy = new PositionProxy(position, zoneProxy, numberOfPosition);
		
		for (User observer : users) {
			if(observer.getConnectionType() == true){ //user is a rmi client	
					
				observer.getCliRmi().updateDuePositionOccupied(positionProxy);
			
			}else{//user is a socket client
				
				//observer.getConnectionManagerSocketServer().getSocketOutClient().writeObject();
				
			}
		}
	}

	public void updatePlayerResources(Color playerColor, ArrayList<Resource> resources) throws RemoteException {
		
		ArrayList<ResourceProxy> res = new ArrayList<>();
		for (Resource resource : resources) {
			res.add(new ResourceProxy(resource.getResourceType(), resource.getAmount()));
		}
		
		for (User user : users) {
			if(user.getConnectionType() == true){ //user is a rmi client	
				
				user.getCliRmi().updatePlayerResources(playerColor, res);
			
			}else{//user is a socket client
				
				//observer.getConnectionManagerSocketServer().getSocketOutClient().writeObject();
				
			}
		}
	}
}
