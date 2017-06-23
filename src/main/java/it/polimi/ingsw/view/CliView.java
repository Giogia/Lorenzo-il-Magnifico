package it.polimi.ingsw.view;

import java.util.InputMismatchException;
import java.util.Scanner;

//main class that will start the game in cli view!
public class CliView {
	private static int choice;
	
	public static void main(String[] args) {
		System.out.println("Welcome in Lorenzo Il Magnifico. What kind of connection do you want to use?");
		System.out.println("1) Rmi");
		System.out.println("2) Socket");
		choice = checkInputError();
		System.out.println(choice);
		if(choice == 1){
			//fai partire RMI
		}else{
			//fai partire socket
		}
	}
	
	private static int checkInputError(){
		while(true){
			Scanner scanner = new Scanner(System.in);
			if (scanner.hasNext()){
				try{
					int choice = scanner.nextInt();
					if (choice < 1 || choice >2 ) {
						System.out.println("The input must be an integer between 1 and 2, try again!");
					}else{
						scanner.close();
						return choice;
					}
				}catch (InputMismatchException e) {
					System.out.println("The input must be an integer, try again!");
				}
			}
		}
	}
}
