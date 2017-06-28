package it.polimi.ingsw.gui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UsernameWindow implements Runnable{
	
	private final static Logger LOGGER = Logger.getLogger(UsernameWindow.class.getName());
	
	@FXML
	private TextField nameChosen;

    @FXML
    private Button okButton;
    
    @FXML
    void UsernameConfirm (ActionEvent event) throws RemoteException {
    	String username = nameChosen.getText();
    	GuiRmiView.getCallback().answer(username);
    	//GuiSocketView.getCallback().answer(username);
    	Node  source = (Node) event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
    	stage.close();
    }
    
    
    
	@Override
	public void run(){
		try {
			FXMLLoader login = new FXMLLoader(getClass().getResource("Username.fxml"));
			Scene sceneLogin = new Scene(login.load());
			
			sceneLogin.getStylesheets().add(getClass().getResource("styleGame.css").toExternalForm());
			
			Stage stage = new Stage();
			
			stage.setTitle("Lorenzo Il Magnifico");
			stage.setScene(sceneLogin);
			stage.show();
			
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(),e);
		}
	}
}

