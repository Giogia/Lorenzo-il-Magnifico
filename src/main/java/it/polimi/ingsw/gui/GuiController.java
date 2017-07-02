package it.polimi.ingsw.gui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.minigame.BoardProxy;
import it.polimi.ingsw.minigame.CardContainerProxy;
import it.polimi.ingsw.minigame.DevelopmentCardProxy;
import it.polimi.ingsw.minigame.ExcommunicationTileProxy;
import it.polimi.ingsw.minigame.GameProxy;
import it.polimi.ingsw.minigame.LeaderCardProxy;
import it.polimi.ingsw.minigame.OrderPawn;
import it.polimi.ingsw.minigame.PersonalBoardProxy;
import it.polimi.ingsw.minigame.PlayerProxy;
import it.polimi.ingsw.minigame.PositionProxy;
import it.polimi.ingsw.minigame.TowerFloorProxy;
import it.polimi.ingsw.minigame.TowerProxy;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class GuiController implements Initializable {
	private GuiSocketView guiSocketView;
	private FXMLLoader loader;
	private GameProxy game;
	
	public GuiController(GameProxy game) {
		this.game = game;
	}
	
    @FXML
    private TextField answerChat;
	
	@FXML
    private Tab tabPlayer1;

	@FXML
    private Tab tabPlayer2;
	
	@FXML
    private Tab tabPlayer3;
	
	@FXML
    private Tab tabPlayer4;
	
	@FXML
	private Label chatLabel;
	
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
    private ImageView excommunicationTile1;

    @FXML
    private ImageView excommunicationTile2;

    @FXML
    private ImageView excommunicationTile3;
    
    @FXML
    private Button action1;

    @FXML
    private Button action2;
    
    @FXML
    private Label labelAction;
    
    @FXML
    private TextArea chatText;

    @FXML
    private ImageView neutralFamilyMember;

    @FXML
    private ImageView blackFamilyMember;

    @FXML
    private ImageView whiteFamilyMember;

    @FXML
    private ImageView orangeFamilyMember;
    
    public void setChatLabel(String textToAdd){
    	chatText.setText(chatText.getText() + "\n"+"Lorenzo: " + textToAdd);
    }
    
    @FXML
    void towerFloorCkd(MouseEvent event) {
    	ImageView positionClicked = (ImageView) event.getPickResult().getIntersectedNode();
    	if (positionClicked.getImage() != null){//in this position there is a card
    		imageZoomed.setImage(positionClicked.getImage());
    	}else{//there isn't a card
			Image image = new Image("it/polimi/ingsw/gui/resources/towerFloor.jpeg");
    		imageZoomed.setImage(image);
    		
    		getDescription.setText("In this position there isn't a card.");
    	}
    }
    
    @FXML
    void passTurnCkd(MouseEvent event) throws RemoteException {
    	GuiRmiView.getCallback().answer("5");
    	action1.setDisable(true);
    	action2.setDisable(true);
    }

    @FXML
    void placeFamilyMemberCkd(MouseEvent event) throws RemoteException {
    	GuiRmiView.getCallback().answer("1$1$1");//first 1 means place family member
    }
    
    public void disableButtons(boolean bool){
    	action1.setDisable(bool);
    	action2.setDisable(bool);
    }
  
    @FXML
    void enterPressed(KeyEvent event) throws RemoteException {
    	if (event.getCode() == KeyCode.ENTER)  {
            sendFunction();
       }
    }
    
    @FXML
    void sendCkd(MouseEvent event) throws RemoteException{
    	sendFunction();
    }
    
	private void sendFunction() throws RemoteException {
		String text = answerChat.getText();
		chatText.setText(chatText.getText() + "\n"+"Michele: " + text);//adding input of user in chat
		answerChat.setText("");//clear answer field 
		GuiRmiView.getCallback().answer(text);
	}

	public void setMain(GuiSocketView guiSocketView) {
		this.guiSocketView = guiSocketView;
	}
	
	public void updateTowerFloor(TowerFloorProxy newTowerFloorProxy){
		DevelopmentCardType towerType = newTowerFloorProxy.getTowerType();
		int numberOfTowerFloor = newTowerFloorProxy.getNumberOfFloor();
		TowerFloorProxy towerFloorProxy = game.getBoardProxy().getTowerProxy(towerType).getTowerFloorProxy(numberOfTowerFloor);
		
		//setting to null the image of card on towerFloor where player setted.
		towerFloorProxy.getDevelopmentCardProxy().setImageProperty("it/polimi/ingsw/gui/resources/towerFloor.jpeg");
		
		//setting the image of family member on tower floor
		towerFloorProxy.getFamilyMemberProxy().setImageProperty(newTowerFloorProxy.getFamilyMemberProxy().getImagePath());
	}
	
	public void setCards(ArrayList<DevelopmentCardProxy> cards){
		BoardProxy board = game.getBoardProxy();
		for(int i = 0; i < 15; i++){
			int toowerType = i/4;
			String imagePath = cards.get(i).getImagePath();
			for(int numberOfFloor = 0; numberOfFloor < 4; numberOfFloor ++){
				board.getTowerProxyByInt(toowerType).getTowerFloorProxies().get(numberOfFloor).getDevelopmentCardProxy().setImageProperty(imagePath);
			}
		}
	}
	
	public void setImages(){
		BoardProxy board = game.getBoardProxy();
		for(OrderPawn orderPawn : board.getRoundOrderProxy().getOrderPawns()){
			orderPawn.setImageProperty();
		}
		for(TowerProxy towerProxy : board.getTowerProxies()){
			for(TowerFloorProxy towerFloorProxy : towerProxy.getTowerFloorProxies()){
				towerFloorProxy.getDevelopmentCardProxy().setImageProperty();
			}
		}
		/*for(ExcommunicationTileProxy excommunicationTileProxy : board.getExcommunicationTileProxies()){
			excommunicationTileProxy.setImageProperty();
		}*/
		/*for(PlayerProxy playerProxy : game.getPlayerProxies()){
			PersonalBoardProxy personalBoardProxy = playerProxy.getPersonalBoardProxy();
			for(CardContainerProxy cardContainerProxy : personalBoardProxy.getCardContainers()){
				for(DevelopmentCardProxy developmentCardProxy : cardContainerProxy.getDevelopmentCardProxies()){
					developmentCardProxy.setImageProperty();
				}
			}
			for(LeaderCardProxy leaderCardProxy : personalBoardProxy.getLeaderCardProxies()){
				leaderCardProxy.setImageProperty();
			}
			for(LeaderCardProxy leaderCardProxy : playerProxy.getLeaderCardInHandProxies()){
				leaderCardProxy.setImageProperty();
			}
		}*/
		
	}
	
	public PlayerProxy getCurrentPlayer(GameProxy gameProxy) {
		for (PlayerProxy playerProxy : gameProxy.getPlayerProxies()) {
			if(playerProxy.getCurrentPlayer().equals(true))
				return playerProxy;
		}
		return null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		disableButtons(true);//place family member and pass the turn are disabled.
		
		//setting name players
		ArrayList<PlayerProxy> players = game.getPlayerProxies();
		tabPlayer1.setText(players.get(0).getName());
		tabPlayer2.setText(players.get(1).getName());
		
		if(players.size() >2){
			tabPlayer3.setText(players.get(2).getName());
		}else tabPlayer3.setDisable(true);
		
		if(players.size() >3){
			tabPlayer4.setText(players.get(3).getName());
		}else tabPlayer4.setDisable(true);
		
		//setting cards binding
		TowerProxy towerProxy = game.getBoardProxy().getTowerProxy(DevelopmentCardType.territory);
		String imagePath = towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().getImagePath();
		try{
			towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().setImageProperty(imagePath);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		setImages();
		
		territory1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().getImageProperty());
		territory2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getDevelopmentCardProxy().getImageProperty());
		territory3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getDevelopmentCardProxy().getImageProperty());
		territory4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getDevelopmentCardProxy().getImageProperty());
		
		towerProxy = game.getBoardProxy().getTowerProxy(DevelopmentCardType.building);
		building1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().getImageProperty());
		building2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getDevelopmentCardProxy().getImageProperty());
		building3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getDevelopmentCardProxy().getImageProperty());
		building4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getDevelopmentCardProxy().getImageProperty());
		
		towerProxy = game.getBoardProxy().getTowerProxy(DevelopmentCardType.character);
		character1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().getImageProperty());
		character2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getDevelopmentCardProxy().getImageProperty());
		character3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getDevelopmentCardProxy().getImageProperty());
		character4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getDevelopmentCardProxy().getImageProperty());
		
		towerProxy = game.getBoardProxy().getTowerProxy(DevelopmentCardType.venture);
		venture1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().getImageProperty());
		venture2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getDevelopmentCardProxy().getImageProperty());
		venture3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getDevelopmentCardProxy().getImageProperty());
		venture4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getDevelopmentCardProxy().getImageProperty());

		PlayerProxy player = getCurrentPlayer(game);
		
		neutralFamilyMember.imageProperty().bind(player.getFamilyMemberProxy(DiceColour.Neutral).getImageProperty());
		blackFamilyMember.imageProperty().bind(player.getFamilyMemberProxy(DiceColour.Black).getImageProperty());
		whiteFamilyMember.imageProperty().bind(player.getFamilyMemberProxy(DiceColour.White).getImageProperty());
		orangeFamilyMember.imageProperty().bind(player.getFamilyMemberProxy(DiceColour.Orange).getImageProperty());
	}
}