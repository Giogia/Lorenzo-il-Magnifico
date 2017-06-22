package it.polimi.ingsw.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewWindow implements Runnable{
	@FXML
	private TextField nameChosen;

    @FXML
    private Button okButton;
    
    @FXML
    public void btnClicked(ActionEvent event) {
    	 nameChosen.getText();
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
	
}
