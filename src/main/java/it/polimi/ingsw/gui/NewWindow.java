package it.polimi.ingsw.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewWindow implements Runnable{
	
	@FXML
	private TextField nameChosen;

    @FXML
    private Button okButton;
    
    @FXML
    void btnClicked(ActionEvent event) {
    	//TODO il namechosen rimane a null 
    	String string = "username-11";
    	Node  source = (Node) event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
    	stage.close();
    }
    
    
    
	@Override
	public void run(){
		try {
			Parent login = FXMLLoader.load(getClass().getResource("NameAndColor.fxml"));
			Scene sceneLogin = new Scene(login,800,600);
			
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
