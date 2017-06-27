package it.polimi.ingsw.gui;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ColorWindow implements Runnable{
	
	private FXMLLoader loader;
	
    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;
	
    @FXML
    void btn1Clicked(MouseEvent event) throws RemoteException {
    	GuiRmiView.getCallback().answer("1");
    	closeWindow(event);
    }

    @FXML
    void btn2Clicked(MouseEvent event) throws RemoteException {
    	GuiRmiView.getCallback().answer("2");
    	closeWindow(event);
    }

    @FXML
    void btn3Clicked(MouseEvent event) throws RemoteException{
    	GuiRmiView.getCallback().answer("3");
    	closeWindow(event);
    }

    @FXML
    void btn4Clicked(MouseEvent event) throws RemoteException{
    	GuiRmiView.getCallback().answer("4");
    	closeWindow(event);
    }
    
    private void closeWindow(MouseEvent event){
    	Node  source = (Node) event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
    	stage.close();
    }
	
	public void setButton(String[] colors){
		btn1.setText(colors[0]);
		
		if(colors.length >1) {
			btn2.setText(colors[1]);
		}else btn2.setVisible(false);
		
		if(colors.length >2){
			btn3.setText(colors[2]);
		}else btn3.setVisible(false);
		
		if(colors.length >3){
			btn4.setText(colors[3]);
		}else btn4.setVisible(false);
	}
	
	public void setLoader(FXMLLoader loader) {
		this.loader = loader;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


