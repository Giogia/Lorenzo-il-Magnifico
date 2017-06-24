package it.polimi.ingsw.gui;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.manager.ConnectionManagerRmiServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewWindow implements Runnable{
	private static GuiRmiView guiRmiView;
	
	
	public void setGuiRmiView(GuiRmiView guiRmiView) {
		this.guiRmiView = guiRmiView;
	}
	
	
	private String nome;
	@FXML
	private TextField nameChoosen;

    @FXML
    private Button okButton;
    
    @FXML
    void btnClicked(MouseEvent event) {
    	String name = nameChoosen.getText();
    	System.out.println(guiRmiView);
    	System.out.println(name);

    	Stage stage = (Stage) event.getPickResult().getIntersectedNode().getScene().getWindow();
    	stage.close();
    	try {
			guiRmiView.reply(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
	@Override
	public void run(){
		try {
			FXMLLoader login = new FXMLLoader(getClass().getResource("NameAndColor.fxml"));
			Scene sceneLogin = new Scene(login.load());
			
			sceneLogin.getStylesheets().add(getClass().getResource("styleGame.css").toExternalForm());
			
			Stage stage = new Stage();
			
			stage.setTitle("Lorenzo Il Magnifico");
			stage.setScene(sceneLogin);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TextField getNameChoosen() {
		return nameChoosen;
	}
}
