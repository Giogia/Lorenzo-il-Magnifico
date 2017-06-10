package it.polimi.ingsw.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Set;

public class ClientInHandler implements Runnable{
private ObjectInputStream socketIn;
	
	public ClientInHandler(ObjectInputStream socketIn) {
		this.socketIn=socketIn;
	}
	
	@Override
	public void run() {


		while(true){

			// handles input messages coming from the server, just showing them to the user
			try {
				Object object=socketIn.readObject();
				if(object instanceof Set<?>){
					System.out.println(object);
					//Set<Prigioniero> prigionieri=(Set<Prigioniero>)object;
					//prigionieri.iterator().next().setName("pippo");
				}
				
				System.out.println(object);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
