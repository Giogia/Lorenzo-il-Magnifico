package it.polimi.ingsw.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	Scene sceneLogin;
	Scene sceneGame;
	Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("Game.fxml"));
			sceneLogin = new Scene(login,1280,800);
			
			sceneLogin.getStylesheets().add(getClass().getResource("styleGame.css").toExternalForm());
			
			primaryStage.setTitle("Lorenzo Il Magnifico");
			primaryStage.setScene(sceneLogin);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Application.launch(Main.class , args);
	}
}
