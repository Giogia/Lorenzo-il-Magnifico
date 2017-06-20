package it.polimi.ingsw.view;

import java.util.Scanner;

public class CliRmiViewAlternative implements Runnable {
	Scanner scanner = new Scanner(System.in);
	
	@Override
	public void run() {
		while(true){
			if (scanner.hasNext()){
				scanner.next();
			}
		}
	}

}
