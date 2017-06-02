package it.polimi.ingsw.view;

public class Main {
	public static void main(String[] args) {
		try{
			CLIView view1 = new CLIView();
			CLIView view2 = new CLIView();
		}
		catch(Exception exc){
			System.out.println(exc);
		}
	}
}
