package it.polimi.ingsw.gui;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.Position;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.view.CliRmi;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GuiController extends Application implements ActionListener {
	
	private GuiSocketView guiSocketView;
	private FXMLLoader loader;
	
	@FXML
	private Label getDescription;
	
	@FXML
    private ImageView imageZoomed;

    @FXML
    private ImageView territory4;

    @FXML
    private ImageView territory3;

    @FXML
    private ImageView territory2;

    @FXML
    private ImageView territory1;

    @FXML
    private ImageView character1;

    @FXML
    private ImageView character2;

    @FXML
    private ImageView character3;

    @FXML
    private ImageView character4;

    @FXML
    private ImageView building4;

    @FXML
    private ImageView building3;

    @FXML
    private ImageView building2;

    @FXML
    private ImageView building1;

    @FXML
    private ImageView venture4;

    @FXML
    private ImageView venture3;

    @FXML
    private ImageView venture2;

    @FXML
    private ImageView venture1;

    @FXML
    private Ellipse councilPalace;

    @FXML
    private Circle production1;

    @FXML
    private Circle harvest1;

    @FXML
    private Circle market1;

    @FXML
    private Circle market2;

    @FXML
    private Circle market3;

    @FXML
    private Circle market4;

    @FXML
    private Rectangle production2;

    @FXML
    private Rectangle harvest2;

    @FXML
    private Rectangle dice1;

    @FXML
    private Rectangle dice2;

    @FXML
    private Rectangle dice3;

    @FXML
    private Rectangle excommunicationTile1;

    @FXML
    private Rectangle excommunicationTile2;

    @FXML
    private Rectangle excommunicationTile3;
    

    @FXML
    private Button action2;

    @FXML
    private Button action4;

    @FXML
    private Button action1;
    
    @FXML
    private Button action5;

    @FXML
    private Label labelAction;

    @FXML
    private Button action3;

    public void setLoader(FXMLLoader loader){
    	this.loader = loader;
    }
    
    @Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
			Scene scene = new Scene(loader.load());
			scene.getStylesheets().add(getClass().getResource("styleGame.css").toExternalForm());
			
			GuiController controller = new GuiController();
			loader.setController(controller);
			controller.setLoader(loader);
			
			primaryStage.setTitle("Lorenzo Il Magnifico");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    @FXML
    void towerFloorCkd(MouseEvent event) {
    	System.out.println(event.getPickResult().getIntersectedNode().getId());
    	
    	ImageView positionClicked = (ImageView) event.getPickResult().getIntersectedNode();
    	if (positionClicked.getImage() != null){//in this position there is a card
    		imageZoomed.setImage(positionClicked.getImage());
    		
    		getDescription.setText("TODO");
    	}else{//there isn't a card
			Image image = new Image("it/polimi/ingsw/gui/resources/towerFloor.jpeg");
    		imageZoomed.setImage(image);
    		
    		getDescription.setText("In this position there isn't a card.");
    	}
    }
    
    @FXML
    void actionCkd(MouseEvent event) {
    	
    }

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void setMain(GuiSocketView guiSocketView) {
		this.guiSocketView = guiSocketView;
		
	}
}