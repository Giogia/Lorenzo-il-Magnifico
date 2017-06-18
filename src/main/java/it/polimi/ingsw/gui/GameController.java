package it.polimi.ingsw.gui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameController implements ClientRMICallbackRemote{
	@FXML
	private Label getDescription;
	
	@FXML
    private ImageView imageZoomed;

    @FXML
    private ImageView territory4;

    @FXML
    private ImageView territory3;

    @FXML
    private Rectangle territory2;

    @FXML
    private Rectangle territory1;

    @FXML
    private Rectangle character1;

    @FXML
    private Rectangle character2;

    @FXML
    private Rectangle character3;

    @FXML
    private Rectangle character4;

    @FXML
    private Rectangle building4;

    @FXML
    private Rectangle building3;

    @FXML
    private Rectangle building2;

    @FXML
    private Rectangle building1;

    @FXML
    private Rectangle venture4;

    @FXML
    private Rectangle venture3;

    @FXML
    private Rectangle venture2;

    @FXML
    private Rectangle venture1;

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

	@Override
	public int askForLeaderCardAction() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void startTurn(String playerName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String askName() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int askColor(String[] availableColors) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int turnChoice() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public int askForLeaderCard(ArrayList<LeaderCard> leaderCards) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int askForPersonalBonusTile(ArrayList<PersonalBonusTile> personalBonusTiles) throws RemoteException {
		// TODO Auto-generated method stub
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
    
    
}
