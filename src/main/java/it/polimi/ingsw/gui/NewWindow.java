package it.polimi.ingsw.gui;

import java.io.IOException;

import it.polimi.ingsw.GC_15.Player;
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
	private String nome;
	@FXML
	private TextField nameChoosen;

    @FXML
    private Button okButton;
    
    @FXML
    void btnClicked(MouseEvent event) {
    	String name = nameChoosen.getText();
    	nome = name;
    	Risposta risposta = Risposta.getRisposta();
    	risposta.setNome(name);
    	
    	Stage stage = (Stage) event.getPickResult().getIntersectedNode().getScene().getWindow();
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
