package it.polimi.ingsw.gui;

import java.io.IOException;
import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NameWindow implements Runnable{

	@FXML
	private TextField nameChosen;

    @FXML
    private Button okButton;
    
    @FXML
    void UsernameConfirm (ActionEvent event) throws RemoteException {
    	String username = nameChosen.getText();
    	GuiRmiView.getCallback().answer(username);
    	Node  source = (Node) event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
    	stage.close();
    }
    
    
    
	@Override
	public void run(){
		try {
			FXMLLoader login = new FXMLLoader(getClass().getResource("Name.fxml"));
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
