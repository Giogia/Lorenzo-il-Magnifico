package it.polimi.ingsw.gui;

import java.io.IOException;
import java.rmi.RemoteException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ColorWindow implements Runnable{
	
	ObservableList<String> colorsList = FXCollections.observableArrayList("culo");

    @FXML
    private ChoiceBox<String> Colors = new ChoiceBox<>();

    @FXML
    private Button okButton;
	
    
    @FXML
    void UsernameConfirm (ActionEvent event) throws RemoteException {
    	String color = Colors.getValue();
    	GuiRmiView.getCallback().answer(color);
    	Node  source = (Node) event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    public void setList(String[] availableColors){
    	//colorsList.clear();
    	for(String string : availableColors){
    		colorsList.add(string);
    	}
    }
    
    @FXML
    void show(){
    	Colors.setItems(colorsList);
    }
    
	@Override
	public void run(){
		try {
			FXMLLoader login = new FXMLLoader(getClass().getResource("Color.fxml"));
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


