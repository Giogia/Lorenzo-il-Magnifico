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

import javax.swing.Action;

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
import it.polimi.ingsw.view.ClientRMICallbackRemote;
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

public class GameController implements ClientRMICallbackRemote, ActionListener{
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
	public int askForLeaderCardAction() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void startTurn(String playerName) throws RemoteException {
		System.out.println("inizia il turno");
	}
    
	@Override
	public String askName() throws RemoteException {
		NewWindow newWindow = new NewWindow();
		Thread thread = new Thread(newWindow);
		Platform.runLater(thread);
		
		return "marco";
	}

	@Override
	public int askColor(String[] availableColors) throws RemoteException {
		// TODO Auto-generated method stub
		return 1;
	}

	private int risposta = 0;

	private Main main;
	
	@Override
	public int turnChoice() throws RemoteException {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub

				System.out.println("-------------sono arrivato qui------------------");
				action1.setText("See leader cards in your hand");
				action2.setText("Activate effect of a leader card");
				action3.setText("Pass the turn");
				action4.setVisible(false);
				action5.setVisible(false);
			}
		});
		while(risposta == 0){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return risposta;
	}

	@Override
	public void moveAlreadyDone() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int chooseZone() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int choosePosition(Position[] positions) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int chooseFamilyMember(ArrayList<FamilyMember> familyMembers) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int askForAlternativeCost(ArrayList<Resource> costDescriptions,
			ArrayList<Resource> alternativeCostDescriptions) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int askForCouncilPrivilege(ArrayList<ResourceBonus> councilPrivileges) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int askForServants(int numberOfServants) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int askForInformation(String[] playersNames) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void showPersonalBoard(PersonalBoard personalBoard) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cantPassTurn() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void roundBegins() throws RemoteException {
		System.out.println("ciaovkjbgvierogiweho");
	}

	@Override
	public void hasWon(String winner) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int askForAction(ArrayList<ActionZone> zones) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int askForActionPosition(Position[] positions) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void catchException(String message) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDices(ArrayList<Dice> dices) throws RemoteException {
		System.out.println("qui andranno i dadi");
		
	}

	@Override
	public int askForLeaderCard(ArrayList<LeaderCard> leaderCards) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int askForPersonalBonusTile(ArrayList<PersonalBonusTile> personalBonusTiles) throws RemoteException {
		return 0;
	}

	@Override
	public int draftLeaderCard(ArrayList<LeaderCard> leaderCards) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int askForExcommunication(ExcommunicationTile excommunicationTile) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int askForCardEffect(DevelopmentCard developmentCard) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void getMain(Main main) {
		this.main = main;
		
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}