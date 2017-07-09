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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//window that appears to ask username in gui 
public class UsernameWindow implements Runnable{
	private boolean isRmiClient; //boolean setted or from GuiSocketInView or from GuiSocketInView 
	private final static Logger LOGGER = Logger.getLogger(UsernameWindow.class.getName());
	Scene sceneLogin;
	private FXMLLoader loader;
	
	public UsernameWindow(boolean isRmiClient, FXMLLoader loader) {
		this.isRmiClient = isRmiClient;
		this.loader = loader;
	}
	
	@FXML
	private TextField nameChosen;

    @FXML
    private Button okButton;
    
    @FXML
    void UsernameConfirm (MouseEvent event) throws RemoteException {
    	String username = nameChosen.getText();
    	if(isRmiClient){
    		synchronized (GuiRmiCallback.getLock()) {//wait when server send a request (only then I can send answer)
    			GuiRmiCallback.setServerPass(true);
    			GuiRmiCallback.getLock().notifyAll();
    		}
        	
        	GuiRmiView.getCallback().answer(username);
    	}else{
    		GuiSocketView.getCallback().setToSend(username);
    	}
    	//after sending username close window
    	Node  source = (Node) event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
    	stage.close();
    }
    
    
    
	@Override
	public void run(){
		try {
			sceneLogin = new Scene(loader.load());
			
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

