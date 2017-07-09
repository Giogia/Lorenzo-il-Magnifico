package it.polimi.ingsw.gui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//window that appears to ask name in gui 
public class NameWindow implements Runnable{
	private final static Logger LOGGER = Logger.getLogger(NameWindow.class.getName());
	private boolean isRmiClient;
	private FXMLLoader loader;
	
	public NameWindow(boolean isRmiClient, FXMLLoader loader) {
		this.isRmiClient = isRmiClient;
		this.loader = loader;
	}
	
	@FXML
	private TextField nameChosen;

    @FXML
    private Button okButton;
    
    @FXML
    void UsernameConfirm (ActionEvent event) throws RemoteException {
    	String username = nameChosen.getText();

    	if(isRmiClient){
	    	synchronized (GuiRmiCallback.getLock()) {
				GuiRmiCallback.setServerPass(true);
				GuiRmiCallback.getLock().notifyAll();
			}
	    	
	    	GuiRmiView.getCallback().answer(username);
	    }else{
	    	synchronized (GuiSocketOutView.getLock()) {
				GuiSocketOutView.setServerPass(true);
				GuiSocketOutView.getLock().notifyAll();
			}
	    	GuiSocketView.getCallback().setToSend(username);
	    }
    	Node  source = (Node) event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
    	stage.close();
    }
    
	@Override
	public void run(){
		try {
			Scene sceneLogin = new Scene(loader.load());
			
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

