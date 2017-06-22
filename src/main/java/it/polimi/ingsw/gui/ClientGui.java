package it.polimi.ingsw.gui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.view.CliRmi;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class ClientGui /*implements CliRmi*/ {

	public void startTurn(String playerName) throws RemoteException {
		System.out.println("inizia il turno");
		
	}

	public void askName() throws RemoteException {
		NewWindow newWindow = new NewWindow();
		Thread thread = new Thread(newWindow);
		//thread.start();
	
		Platform.runLater(thread);
		//name = newWindow.getNome();
		Risposta risposta = Risposta.getRisposta();
		String name = risposta.getNome();
		
		System.out.println("name choosen: " + name);
		
		//return name;
	}
	
	public void turnChoice() throws RemoteException {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
           	 System.out.println("-------------sono arrivato qui------------------");
				/*action1.setText("See leader cards in your hand");
				action2.setText("Activate effect of a leader card");
				action3.setText("Pass the turn");
				action4.setVisible(false);
				action5.setVisible(false);*/
            }
        });
		//return 2;
	}

}
